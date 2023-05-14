package hello.advanced.trace.logtrace

import hello.advanced.trace.TraceStatus
import java.lang.Exception

interface LogTrace {
    fun begin(message: String): TraceStatus
    fun end(status: TraceStatus)
    fun exception(status: TraceStatus, exception: Exception)
}