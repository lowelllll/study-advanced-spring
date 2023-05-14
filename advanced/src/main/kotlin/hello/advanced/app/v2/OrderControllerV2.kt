package hello.advanced.app.v2

import hello.advanced.trace.hellotrace.HelloTraceV2
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception

@RestController // Controller + ResponseBody
class OrderControllerV2(val orderServiceV2: OrderServiceV2, val trace: HelloTraceV2) {

    @GetMapping("/v2/request")
    fun request(itemId: String): String {
        val status = trace.begin("OrderController.request()")
        try {
            orderServiceV2.orderItem(status.traceId, itemId)
            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e;
        }
        return "ok"
    }
}