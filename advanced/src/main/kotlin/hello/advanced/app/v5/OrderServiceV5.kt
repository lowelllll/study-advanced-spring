package hello.advanced.app.v5

import hello.advanced.trace.callback.TraceCallback
import hello.advanced.trace.callback.TraceTemplate
import hello.advanced.trace.template.AbstractTemplate
import hello.advanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Service

@Service
class OrderServiceV5(val orderRepositoryV5: OrderRepositoryV5, trace: LogTrace) {
    private val template = TraceTemplate(trace)

    fun orderItem(itemId: String) {
        template.execute("OrderService.orderItem()", object : TraceCallback<Unit> {
            override fun call() {
                orderRepositoryV5.save(itemId)
            }
        })
    }
}