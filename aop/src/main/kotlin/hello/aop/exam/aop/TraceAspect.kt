package hello.aop.exam.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before

@Aspect
class TraceAspect {

    private val logger = mu.KotlinLogging.logger {  }

    @Before("@annotation(hello.aop.exam.annotation.Trace)")
    fun doTrace(joinPoint: JoinPoint) {
        logger.info { "[trace] {${joinPoint.signature}}, args={${joinPoint.args}}" }
    }
}