package hello.advanced.app.v2

import hello.advanced.trace.TraceId
import hello.advanced.trace.hellotrace.HelloTraceV2
import org.springframework.stereotype.Repository
import java.lang.Exception

@Repository
class OrderRepositoryV2(private val trace: HelloTraceV2) {

    fun save(traceId: TraceId, itemId: String) {
        val status = trace.beginSync(traceId, "OrderRepository.save()")

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