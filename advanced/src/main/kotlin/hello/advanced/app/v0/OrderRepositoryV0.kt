package hello.advanced.app.v0

import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV0 {

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