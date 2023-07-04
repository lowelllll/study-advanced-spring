package hello.aop.exam

import hello.aop.exam.annotation.Trace
import org.springframework.stereotype.Repository

@Repository
class ExamRepository {

    @Trace
    fun save(itemId: String): String {
        seq++
        if (seq % 5 == 0) {
            throw IllegalStateException("예외발생")
        }
        return "ok"
    }
    companion object {
        private var seq = 0;
    }
}