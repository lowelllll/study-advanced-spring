package hello.advanced.app.v3

import hello.advanced.trace.hellotrace.HelloTraceV2
import hello.advanced.trace.logtrace.LogTrace
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception

@RestController // Controller + ResponseBody
class OrderControllerV3(val orderServiceV3: OrderServiceV3, val trace: LogTrace) {

    @GetMapping("/v3/request")
    fun request(itemId: String): String {
        val status = trace.begin("OrderController.request()")
        try {
            orderServiceV3.orderItem(itemId)
            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e;
        }
        return "ok"
    }
}