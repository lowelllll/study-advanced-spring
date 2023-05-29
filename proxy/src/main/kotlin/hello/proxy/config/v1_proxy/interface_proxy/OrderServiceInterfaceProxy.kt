package hello.proxy.config.v1_proxy.interface_proxy

import hello.proxy.app.v1.OrderServiceV1
import hello.proxy.trace.logtrace.LogTrace

class OrderServiceInterfaceProxy(
    private val target: OrderServiceV1,
    private val logTrace: LogTrace
) : OrderServiceV1 {
    override fun orderItem(itemId: String) {
        val status = logTrace.begin("OrderService.orderItem()")
        try {
            val result = target.orderItem(itemId)
            logTrace.end(status)
            return result
        } catch (e: Exception) {
            logTrace.exception(status, e)
            throw e
        }
    }
}