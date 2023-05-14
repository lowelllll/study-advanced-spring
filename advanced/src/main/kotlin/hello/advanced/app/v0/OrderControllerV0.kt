package hello.advanced.app.v0

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController // Controller + ResponseBody
class OrderControllerV0(val orderServiceV0: OrderServiceV0) {

    @GetMapping("/v0/request")
    fun request(itemId: String): String {
        orderServiceV0.orderItem(itemId)
        return "ok"
    }
}