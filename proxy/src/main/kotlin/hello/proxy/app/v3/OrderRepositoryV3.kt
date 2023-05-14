package hello.proxy.app.v3

import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV3 {

    fun save(itemId: String) {
        if (itemId == "ex") {
            throw IllegalStateException("예외 발생 !")
        }
        sleep(1000)
    }

    fun sleep(ms: Int) {
        Thread.sleep(1000)
    }
}