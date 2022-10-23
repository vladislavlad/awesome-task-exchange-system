package toughdevschool.ates.analytics

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator

@SpringBootApplication(
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator::class,
    exclude = [ReactiveUserDetailsServiceAutoConfiguration::class],
)
class AnalyticsService

fun main(args: Array<String>) {
    runApplication<AnalyticsService>(*args)
}
