package hello.aop.exam

import hello.aop.exam.annotation.Retry
import hello.aop.exam.aop.RetryAspect
import hello.aop.exam.aop.TraceAspect
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(TraceAspect::class, RetryAspect::class)
@SpringBootTest
class ExamTest {

    @Autowired
    lateinit var service: ExamService

    @Test
    fun test() {
        (1..5).forEach {
            service.request("data" + it)
        }
    }
}