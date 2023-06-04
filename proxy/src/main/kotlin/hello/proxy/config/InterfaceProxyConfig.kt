package hello.proxy.config

import hello.proxy.app.v1.*
import hello.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy
import hello.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy
import hello.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy
import hello.proxy.trace.logtrace.LogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InterfaceProxyConfig {

    @Bean
    fun orderController(logTrace: LogTrace): OrderControllerV1 {
        return OrderControllerInterfaceProxy(OrderControllerV1Impl(orderService(logTrace)), logTrace)
    }

    @Bean
    fun orderService(logTrace: LogTrace): OrderServiceV1 {
        return OrderServiceInterfaceProxy(OrderServiceV1Impl(orderRepository(logTrace)), logTrace)
    }

    @Bean
    fun orderRepository(logTrace: LogTrace): OrderRepositoryV1 {
        return OrderRepositoryInterfaceProxy(OrderRepositoryV1Impl(), logTrace)
    }
}