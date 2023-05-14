package hello.advanced.strategy

import org.junit.jupiter.api.Test

class ContextV1(private val strategy: Strategy) {
    private val logger = mu.KotlinLogging.logger {  }

    fun execute() {
        val startTime = System.currentTimeMillis()
         strategy.call()
        logger.info { "resultTime=${System.currentTimeMillis() - startTime}" }
    }
}


class ContextV1Test {

    @Test
    fun test() {
        val context1 = ContextV1(object : Strategy {
            override fun call() {
                println("로직1")
            }
        })
        context1.execute()

        val contextV2 = ContextV1(object : Strategy {
            override fun call() {
                println("로직2")
            }
        })
        contextV2.execute()
    }
}

class ContextV2 {
    private val logger = mu.KotlinLogging.logger {  }

    fun execute(strategy: Strategy) {
        val startTime = System.currentTimeMillis()
        strategy.call()
        logger.info { "resultTime=${System.currentTimeMillis() - startTime}" }
    }
}

class ContextV2Test {

    @Test
    fun test() {
        val contextV2 = ContextV2()

        contextV2.execute(
            object : Strategy {
                override fun call() {
                    println("로직1")
                }
            }
        )

        contextV2.execute(
            object : Strategy {
                override fun call() {
                    println("로직2")
                }
            }
        )
    }
}