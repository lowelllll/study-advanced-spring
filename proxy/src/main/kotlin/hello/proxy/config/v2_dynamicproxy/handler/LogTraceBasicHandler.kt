package hello.proxy.config.v2_dynamicproxy.handler

import hello.proxy.trace.logtrace.LogTrace
import org.springframework.util.PatternMatchUtils
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class LogTraceBasicHandler(
    private val target: Any,
    private val logTrace: LogTrace,
    private val pattern: Array<String>
) : InvocationHandler {

    override fun invoke(proxy: Any, method: Method, args: Array<out Any>): Any? {
        if (PatternMatchUtils.simpleMatch(pattern, method.name)) {
            return method.invoke(target, *args)
        }
        val status = logTrace.begin("${method.declaringClass.simpleName}.${method.name}()")
        try {
            val result = method.invoke(target, *args)
            logTrace.end(status)
            return result
        } catch (e: Exception) {
            logTrace.exception(status, e)
            throw e
        }
    }

}