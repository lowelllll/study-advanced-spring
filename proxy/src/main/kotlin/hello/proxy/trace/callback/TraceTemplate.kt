package hello.proxy.trace.callback

import hello.proxy.trace.logtrace.LogTrace


class TraceTemplate(val trace: LogTrace) {

    fun <T> execute(message: String, callback: TraceCallback<T>): T {
        val status = trace.begin(message)
        try {
            val result = callback.call()
            trace.end(status)
            return result
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e;
        }
    }
}