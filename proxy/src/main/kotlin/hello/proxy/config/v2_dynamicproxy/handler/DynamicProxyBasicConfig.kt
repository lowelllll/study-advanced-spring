package hello.proxy.config.v2_dynamicproxy.handler

import hello.proxy.app.v1.*
import hello.proxy.trace.logtrace.LogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.lang.reflect.Proxy

@Configuration
class DynamicProxyBasicConfig { // JDK 동적 프록시 기술로 동적 프록시 생성

    @Bean
    fun orderController(logTrace: LogTrace): OrderControllerV1 {
        val target = OrderControllerV1Impl(orderService(logTrace))
        return Proxy.newProxyInstance(
            OrderControllerV1::class.java.classLoader,
            arrayOf(OrderControllerV1::class.java),
            LogTraceBasicHandler(target, logTrace, pattern)
        ) as OrderControllerV1
    }


    @Bean
    fun orderService(logTrace: LogTrace): OrderServiceV1 {
        val target = OrderServiceV1Impl(orderRepository(logTrace))
        return Proxy.newProxyInstance(
            OrderServiceV1::class.java.classLoader,
            arrayOf(OrderServiceV1::class.java),
            LogTraceBasicHandler(target, logTrace, pattern)
        ) as OrderServiceV1
    }

    @Bean
    fun orderRepository(logTrace: LogTrace): OrderRepositoryV1 {
        val target = OrderRepositoryV1Impl()
        return Proxy.newProxyInstance(
            OrderRepositoryV1::class.java.classLoader,
            arrayOf(OrderRepositoryV1::class.java),
            LogTraceBasicHandler(target, logTrace, pattern)
        ) as OrderRepositoryV1
    }

    companion object {
        private val pattern = arrayOf("requet*", "order*", "save*")
    }
}