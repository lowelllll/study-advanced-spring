package hello.advanced.app.v4

import hello.advanced.trace.template.AbstractTemplate
import hello.advanced.trace.logtrace.LogTrace
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception

/**
 * 템플릿 메서드 적용
 */
@RestController // Controller + ResponseBody
class OrderControllerV4(val orderServiceV4: OrderServiceV4, val trace: LogTrace) {

    @GetMapping("/v4/request")
    fun request(itemId: String): String {
        val template = object: AbstractTemplate<String>(trace) {
            override fun call(): String {
                orderServiceV4.orderItem(itemId)
                return "ok"
            }
        }
        return template.execute("OrderController.request()")
    }
}