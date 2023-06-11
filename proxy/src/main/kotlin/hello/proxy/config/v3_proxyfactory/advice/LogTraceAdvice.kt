package hello.proxy.config.v3_proxyfactory.advice

import hello.proxy.trace.logtrace.LogTrace
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation

class LogTraceAdvice(private val logTrace: LogTrace) : MethodInterceptor {

    override fun invoke(invocation: MethodInvocation): Any? {
        val method = invocation.method
        val status = logTrace.begin("${method.declaringClass.simpleName}.${method.name}()")
        try {
            val result = invocation.proceed()
            logTrace.end(status)
            return result
        } catch (e: Exception) {
            logTrace.exception(status, e)
            throw e
        }
    }
}