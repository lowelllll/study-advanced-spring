package hello.advanced.app.v4

import hello.advanced.trace.template.AbstractTemplate
import hello.advanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class OrderServiceV4(val orderRepositoryV4: OrderRepositoryV4, val trace: LogTrace) {

    fun orderItem(itemId: String) {
        val template = object: AbstractTemplate<Unit>(trace) {
            override fun call(): Unit {
                orderRepositoryV4.save(itemId)
            }
        }
        return template.execute("OrderService.orderItem()")
    }
}