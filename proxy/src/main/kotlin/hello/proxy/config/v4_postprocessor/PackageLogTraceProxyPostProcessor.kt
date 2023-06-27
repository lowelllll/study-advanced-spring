package hello.proxy.config.v4_postprocessor

import org.springframework.aop.Advisor
import org.springframework.aop.framework.ProxyFactory
import org.springframework.beans.factory.config.BeanPostProcessor

class PackageLogTraceProxyPostProcessor(private val advisor: Advisor) : BeanPostProcessor {

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
        val packageName = bean::class.java.packageName
        // 프록시 적용 대상 여부 체크
        if (!packageName.startsWith(BASE_PACKAGE)) {
            return bean
        }
        return ProxyFactory(bean).apply { addAdvisor(advisor) }.proxy
    }

    companion object {
        const val BASE_PACKAGE = "hello.proxy.app"
    }
}