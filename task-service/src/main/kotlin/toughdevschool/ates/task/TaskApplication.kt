package toughdevschool.ates.task

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator

@SpringBootApplication(
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator::class,
    exclude = [ReactiveUserDetailsServiceAutoConfiguration::class],
)
class TaskApplication

fun main(args: Array<String>) {
    runApplication<TaskApplication>(*args)
}
