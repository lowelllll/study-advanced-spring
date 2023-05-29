package hello.proxy.config.v1_proxy.interface_proxy

import hello.proxy.app.v1.OrderRepositoryV1
import hello.proxy.trace.logtrace.LogTrace

class OrderRepositoryInterfaceProxy(
    private val target: OrderRepositoryV1,
    private val logTrace: LogTrace,
) : OrderRepositoryV1 {

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