package hello.proxy.app.v1

open class OrderServiceV1Impl(val orderRepositoryV1: OrderRepositoryV1) : OrderServiceV1 {

    override fun orderItem(itemId: String) {
        orderRepositoryV1.save(itemId)
    }
}