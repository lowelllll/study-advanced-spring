package hello.proxy.config

import hello.proxy.app.v2.OrderControllerV2
import hello.proxy.app.v2.OrderRepositoryV2
import hello.proxy.app.v2.OrderServiceV2
import hello.proxy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy
import hello.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy
import hello.proxy.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy
import hello.proxy.trace.logtrace.LogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ConcreteProxyConfig {

    @Bean
    fun orderControllerV2(logTrace: LogTrace): OrderControllerV2 {
        return OrderControllerConcreteProxy(OrderControllerV2(orderServiceV2(logTrace)), logTrace)
    }

    fun orderServiceV2(logTrace: LogTrace): OrderServiceV2 {
        return OrderServiceConcreteProxy(OrderServiceV2(orderRepositoryV2(logTrace)), logTrace)
    }

    @Bean
    fun orderRepositoryV2(logTrace: LogTrace): OrderRepositoryV2 {
        return OrderRepositoryConcreteProxy(OrderRepositoryV2(), logTrace)
    }
}