package hello.proxy.config.v5_autoproxy

import hello.proxy.config.AppV1Config
import hello.proxy.config.AppV2Config
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice
import hello.proxy.trace.logtrace.LogTrace
import org.springframework.aop.Advisor
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

// 스프링 자동 프록시 생성기(AnnotationAwareAspectJAutoProxyCreator)에 의해 빈 후처리기를 자동으로 등록해줌
@Configuration
@Import(AppV1Config::class, AppV2Config::class)
class AutoProxyConfig {

    @Bean
    fun advisor1(logTrace: LogTrace): Advisor {
        val pointcut = NameMatchMethodPointcut().apply { setMappedNames("request*", "order*", "save*") }
        return DefaultPointcutAdvisor(pointcut, LogTraceAdvice(logTrace))
    }
}