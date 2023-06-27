package hello.proxy.config.v4_postprocessor

import hello.proxy.config.AppV1Config
import hello.proxy.config.AppV2Config
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice
import hello.proxy.trace.logtrace.LogTrace
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

@Import(AppV1Config::class, AppV2Config::class)
class BeanPostProcessorConfig {

    @Bean
    fun logTraceProxyPostProcessor(logTrace: LogTrace): PackageLogTraceProxyPostProcessor {
        return PackageLogTraceProxyPostProcessor(advisor(logTrace))
    }

    private fun advisor(logTrace: LogTrace): DefaultPointcutAdvisor {
        val pointcut = NameMatchMethodPointcut().apply { setMappedNames("request*", "order*", "save*") }
        return DefaultPointcutAdvisor(pointcut, LogTraceAdvice(logTrace))
    }


}