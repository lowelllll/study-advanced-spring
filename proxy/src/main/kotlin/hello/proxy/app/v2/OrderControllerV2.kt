package hello.proxy.app.v2

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody


@RequestMapping // 스프링 컨트롤러로 인식한다
@ResponseBody // HttpMessageConverter 를 사용해 응답한다
class OrderControllerV2(private val orderServiceV2: OrderServiceV2) {

    @GetMapping("/v2/request")
    fun request(itemId: String): String {
        orderServiceV2.orderItem(itemId)
        return "ok"
    }

    @GetMapping("/v2/no-log")
    fun noLog(): String {
        return "ok"
    }

}