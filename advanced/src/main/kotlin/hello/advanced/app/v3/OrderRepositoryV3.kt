package hello.advanced.app.v3

import hello.advanced.trace.TraceId
import hello.advanced.trace.hellotrace.HelloTraceV2
import hello.advanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Repository
import java.lang.Exception

@Repository
class OrderRepositoryV3(private val trace: LogTrace) {

    fun save(traceId: TraceId, itemId: String) {
        val status = trace.begin("OrderRepository.save()")

        try {
            if (itemId == "ex") {
                throw IllegalStateException("예외 발생 !")
            }
            sleep(1000)
            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }

    fun sleep(ms: Int) {
        Thread.sleep(1000)
    }
}