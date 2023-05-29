package hello.proxy.trace

import java.util.*

class TraceId(
    val id: String,
    val level: Int
) {
    constructor() : this(createId(), 0)

    fun createNextId() = TraceId(id, level + 1)
    fun createPrevId() = TraceId(id, level - 1)
    fun isFirstLeve() = level == 0

    companion object {
        private fun createId() = UUID.randomUUID().toString().take(8)
    }
}