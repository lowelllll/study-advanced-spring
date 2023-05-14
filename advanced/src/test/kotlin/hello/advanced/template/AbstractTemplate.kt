package hello.advanced.template

import org.junit.jupiter.api.Test

abstract class AbstractTemplate<T> {

    protected val logger = mu.KotlinLogging.logger {  }

    protected abstract fun call(): T

    fun execute(): T {
        val startTime = System.currentTimeMillis()
        val result: T = call()
        logger.info { "resultTime=${System.currentTimeMillis() - startTime}" }
        return result
    }
}

class AbstractTemplateTest {

    @Test
    fun test() {
        val template = object: AbstractTemplate<Unit>() {
            override fun call(): Unit {
                logger.info { "execute template logic" }
                return Unit
            }
        }

        template.execute()
    }
}

