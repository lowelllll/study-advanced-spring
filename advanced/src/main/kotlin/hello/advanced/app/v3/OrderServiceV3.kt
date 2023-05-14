package hello.advanced.app.v3

import hello.advanced.trace.TraceId
import hello.advanced.trace.hellotrace.HelloTraceV2
import hello.advanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class OrderServiceV3(val orderRepositoryV3: OrderRepositoryV3, val trace: LogTrace) {

    fun orderItem(itemId: String) {
        val status = trace.begin("OrderService.orderItem()")

        try {
            orderRepositoryV3.save(status.traceId, itemId)
            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }
}