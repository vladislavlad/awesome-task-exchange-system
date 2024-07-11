package toughdevschool.ates.accounting

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator
import reactor.core.publisher.Hooks

@SpringBootApplication(
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator::class,
    exclude = [ReactiveUserDetailsServiceAutoConfiguration::class],
)
class AccountingService

fun main(args: Array<String>) {
    Hooks.enableAutomaticContextPropagation()
    runApplication<AccountingService>(*args)
}
