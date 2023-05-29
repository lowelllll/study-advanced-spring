package hello.proxy.config.v1_proxy.concrete_proxy

import hello.proxy.app.v2.OrderControllerV2
import hello.proxy.trace.logtrace.LogTrace

class OrderControllerConcreteProxy(
    private val target: OrderControllerV2,
    private val logTrace: LogTrace
) : OrderControllerV2(target.orderServiceV2) {

    override fun request(itemId: String): String {
        val status = logTrace.begin("OrderController.request()")
        try {
            val result = target.request(itemId)
            logTrace.end(status)
            return result
        } catch (e: Exception) {
            logTrace.exception(status, e)
            throw e
        }
    }

    override fun noLog(): String {
        return target.noLog()
    }
}