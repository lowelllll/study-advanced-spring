package hello.proxy.app.v3

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController // Controller + ResponseBody
class OrderControllerV3(val orderServiceV3: OrderServiceV3) {

    @GetMapping("/v3/request")
    fun request(itemId: String): String {
        orderServiceV3.orderItem(itemId)
        return "ok"
    }


    @GetMapping("/v3/no-log")
    fun noLog(): String {
        return "ok"
    }
}