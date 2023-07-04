package hello.aop.exam

import hello.aop.exam.annotation.Trace
import org.springframework.stereotype.Service

@Service
class ExamService(private val repository: ExamRepository) {

    @Trace
    fun request(itemId: String) {
        repository.save(itemId)
    }
}