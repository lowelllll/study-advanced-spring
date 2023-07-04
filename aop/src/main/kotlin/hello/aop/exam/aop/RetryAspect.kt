package hello.aop.exam.aop

import hello.aop.exam.annotation.Retry
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import kotlin.math.log


@Aspect
class RetryAspect {

    private val logger = mu.KotlinLogging.logger {  }

    @Around("@annotation(retry)")
    fun doRetry(joinPoint: ProceedingJoinPoint, retry: Retry): Any? {
        logger.info { "[retry] ${joinPoint.signature}, retry=${retry}" }

        var limit = retry.value

        var exceptionHolder: Exception? = null
        (1..limit).forEach {
            try {
                logger.info { "[retry] try count=$it/$limit" }
                return joinPoint.proceed()
            } catch (e: Exception) {
                exceptionHolder = e
            }
        }
        if (exceptionHolder != null) throw exceptionHolder as Exception
        return null
    }
}