package hello.proxy.app.v2

class OrderRepositoryV2 {

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