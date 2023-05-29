package hello.proxy.config.v1_proxy.concrete_proxy

import hello.proxy.app.v2.OrderRepositoryV2
import hello.proxy.trace.logtrace.LogTrace

class OrderRepositoryConcreteProxy(
    private val target: OrderRepositoryV2,
    private val logTrace: LogTrace
) : OrderRepositoryV2() {

    override fun save(itemId: String) {
        val status = logTrace.begin("OrderRepository.save()")
        try {
            val result = target.save(itemId)
            logTrace.end(status)
            return result
        } catch (e: Exception) {
            logTrace.exception(status, e)
            throw e
        }
    }
}