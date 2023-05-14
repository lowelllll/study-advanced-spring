package hello.proxy.app.v1

class OrderRepositoryV1Impl : OrderRepositoryV1 {

    override fun save(itemId: String) {
        if (itemId == "ex") {
            throw IllegalStateException("예외 발생 !")
        }
        sleep(1000)
    }

    fun sleep(ms: Int) {
        Thread.sleep(1000)
    }
}