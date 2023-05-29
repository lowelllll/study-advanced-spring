package hello.proxy.trace

class TraceStatus(
    val traceId: TraceId,
    val startMs: Long,
    val message: String
)