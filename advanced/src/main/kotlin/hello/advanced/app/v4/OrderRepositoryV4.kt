package hello.advanced.app.v4

import hello.advanced.trace.template.AbstractTemplate
import hello.advanced.trace.TraceId
import hello.advanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Repository
import java.lang.Exception

@Repository
class OrderRepositoryV4(private val trace: LogTrace) {

    fun save(itemId: String) {
        val template = object: AbstractTemplate<Unit>(trace) {
            override fun call(): Unit {
                if (itemId == "ex") {
                    throw IllegalStateException("예외 발생 !")
                }
                sleep(1000)
            }
        }
        return template.execute("OrderRepository.save()")
    }

    fun sleep(ms: Int) {
        Thread.sleep(1000)
    }
}