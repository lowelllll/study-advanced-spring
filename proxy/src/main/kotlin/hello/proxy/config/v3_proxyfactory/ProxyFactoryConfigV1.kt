package hello.proxy.config.v3_proxyfactory

import hello.proxy.app.v1.*
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice
import hello.proxy.trace.logtrace.LogTrace
import org.springframework.aop.Advisor
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProxyFactoryConfigV1 {
    val logger = mu.KotlinLogging.logger { }
    private val pointcut = NameMatchMethodPointcut().also { it.setMappedNames("request*", "order*", "save*") }

    @Bean
    fun orderControllerV1(logTrace: LogTrace, orderServiceV1: OrderServiceV1): OrderControllerV1 {
        val target = OrderControllerV1Impl(orderServiceV1)
        val factory = ProxyFactory(target).also { it.addAdvisor(getAdvisor(logTrace)) }
        val proxy = factory.proxy
        logger.info { "ProxyFactory proxy=${proxy::class.java}, target=${target::class.java}" }
        return proxy as OrderControllerV1
    }

    @Bean
    fun orderServiceV1(logTrace: LogTrace, orderRepositoryV1: OrderRepositoryV1): OrderServiceV1 {
        val target = OrderServiceV1Impl(orderRepositoryV1)
        val factory = ProxyFactory(target).also { it.addAdvisor(getAdvisor(logTrace)) }
        val proxy = factory.proxy
        logger.info { "ProxyFactory proxy=${proxy::class.java}, target=${target::class.java}" }
        return proxy as OrderServiceV1
    }


    @Bean
    fun orderRepositoryV1(logTrace: LogTrace): OrderRepositoryV1 {
        val target = OrderRepositoryV1Impl()
        val factory = ProxyFactory(target).also { it.addAdvisor(getAdvisor(logTrace)) }
        val proxy = factory.proxy
        logger.info { "ProxyFactory proxy=${proxy::class.java}, target=${target::class.java}" }
        return proxy as OrderRepositoryV1

    }

    fun getAdvisor(logTrace: LogTrace): Advisor {
        // advisor
        return DefaultPointcutAdvisor(pointcut, LogTraceAdvice(logTrace))
    }
}