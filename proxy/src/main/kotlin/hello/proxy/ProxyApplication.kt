package hello.proxy

import hello.proxy.config.InterfaceProxyConfig
import hello.proxy.trace.logtrace.LogTrace
import hello.proxy.trace.logtrace.ThreadLocalLogTrace
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

//@Import(AppV1Config::class)
//@Import(AppV2Config::class)
@Import(InterfaceProxyConfig::class)
@SpringBootApplication(scanBasePackages = ["hello.proxy.app"])
class ProxyApplication {

    @Bean
    fun logTrace(): LogTrace = ThreadLocalLogTrace()
}

fun main(args: Array<String>) {
    runApplication<ProxyApplication>(*args)
}
