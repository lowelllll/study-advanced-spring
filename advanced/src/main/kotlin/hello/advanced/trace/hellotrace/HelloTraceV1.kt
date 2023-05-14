package hello.advanced.trace.hellotrace

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import org.springframework.stereotype.Component
import java.lang.Exception

@Component
class HelloTraceV1 {

    private val logger = mu.KotlinLogging.logger {  }

    fun begin(message: String): TraceStatus {
        val traceId = TraceId()
        logger.info { "[${traceId.id}] $message " }
        return TraceStatus(traceId, System.currentTimeMillis(), message)
    }

    fun end(status: TraceStatus) {
        complete(status, null)
    }
    fun exception(status: TraceStatus, exception: Exception) {
        complete(status, exception)
    }

    private fun complete(status: TraceStatus, e: Exception?) {
        val stopTimeMs = System.currentTimeMillis()
        val resultTimeMs = stopTimeMs - status.startMs
        if (e == null) {
            logger.info { "[${status.traceId.id}] ${addSpace(COMPLETE_PREFIX, status.traceId.level)} ${status.message} time=${resultTimeMs}" }
        } else {
            logger.info { "[${status.traceId.id}] ${addSpace(EX_PREFIX, status.traceId.level)} ${status.message} time=${resultTimeMs} ex=${e.toString()}" }
        }
    }

    private fun addSpace(prefix: String, level: Int): String {
        val sb = StringBuilder()
        (0 until level).forEach {
            sb.append(if(it == level - 1) "|$prefix" else "|    " )
        }
        return sb.toString()
    }

    companion object {

        const val START_PREFIX = "-->"
        const val COMPLETE_PREFIX = "<--"
        const val EX_PREFIX = "<X-"
    }
}