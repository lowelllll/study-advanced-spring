package hello.advanced.trace.logtrace

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus

class FieldLogTrace : LogTrace {

    private val logger = mu.KotlinLogging.logger { }

    private var traceIdHolder: TraceId? = null

    override fun begin(message: String): TraceStatus {
        syncTraceId()
        val traceId = traceIdHolder!!
        logger.info { "[${traceId.id}] ${addSpace(START_PREFIX, traceId.level)} $message " }
        return TraceStatus(traceId, System.currentTimeMillis(), message)
    }

    override fun end(status: TraceStatus) {
        complete(status, null)
        releaseTraceId()
    }

    override fun exception(status: TraceStatus, exception: Exception) {
        complete(status, exception)
        releaseTraceId()
    }

    private fun complete(status: TraceStatus, e: Exception?) {
        val stopTimeMs = System.currentTimeMillis()
        val resultTimeMs = stopTimeMs - status.startMs
        if (e == null) {
            logger.info {
                "[${status.traceId.id}] ${
                    addSpace(
                        COMPLETE_PREFIX,
                        status.traceId.level
                    )
                } ${status.message} time=${resultTimeMs}"
            }
        } else {
            logger.info {
                "[${status.traceId.id}] ${
                    addSpace(
                        EX_PREFIX,
                        status.traceId.level
                    )
                } ${status.message} time=${resultTimeMs} ex=${e.toString()}"
            }
        }
    }

    private fun addSpace(prefix: String, level: Int): String {
        val sb = StringBuilder()
        (0 until level).forEach {
            sb.append(if (it == level - 1) "|$prefix" else "|    ")
        }
        return sb.toString()
    }

    private fun syncTraceId() {
        traceIdHolder = traceIdHolder?.createNextId() ?: TraceId()
    }

    private fun releaseTraceId() {
        if (traceIdHolder!!.isFirstLeve()) {
            traceIdHolder = null
        } else {
            traceIdHolder = traceIdHolder!!.createPrevId()
        }
    }

    companion object {

        const val START_PREFIX = "-->"
        const val COMPLETE_PREFIX = "<--"
        const val EX_PREFIX = "<X-"
    }

}