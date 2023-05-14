package hello.advanced.app.v5

import hello.advanced.trace.callback.TraceCallback
import hello.advanced.trace.callback.TraceTemplate
import hello.advanced.trace.template.AbstractTemplate
import hello.advanced.trace.logtrace.LogTrace
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 템플릿 콜백 패턴 적용
 */
@RestController // Controller + ResponseBody
class OrderControllerV5(val orderServiceV5: OrderServiceV5, trace: LogTrace) {
    private val template = TraceTemplate(trace)

    @GetMapping("/v5/request")
    fun request(itemId: String): String {
        return template.execute("OrderController.request()", object : TraceCallback<String> {
            override fun call(): String {
                orderServiceV5.orderItem(itemId)
                return "ok"
            }
        })
    }
}