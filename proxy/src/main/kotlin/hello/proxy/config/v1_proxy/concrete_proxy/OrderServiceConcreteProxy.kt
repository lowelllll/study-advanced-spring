package hello.proxy.config.v1_proxy.concrete_proxy

import hello.proxy.app.v2.OrderServiceV2
import hello.proxy.trace.logtrace.LogTrace

class OrderServiceConcreteProxy(
    private val target: OrderServiceV2,
    private val logTrace: LogTrace,
) : OrderServiceV2(target.orderRepositoryV2) { // 여기가 좀 문제넹..

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