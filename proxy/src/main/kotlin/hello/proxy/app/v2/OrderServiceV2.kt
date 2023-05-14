package hello.proxy.app.v2

class OrderServiceV2(val orderRepositoryV2: OrderRepositoryV2) {

    fun orderItem(itemId: String) {
        orderRepositoryV2.save(itemId)
    }
}