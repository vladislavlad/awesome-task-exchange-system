package toughdevschool.ates.accounting.event.consumer

import arrow.core.Either
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import kotlinx.coroutines.reactor.mono
import mu.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.util.retry.Retry
import software.darkmatter.platform.error.BusinessError
import toughdevschool.ates.accounting.domain.task.assign.TaskAssignedHandler
import toughdevschool.ates.accounting.domain.task.complete.TaskCompletedHandler
import toughdevschool.ates.event.Event
import toughdevschool.ates.event.business.BusinessEvent
import toughdevschool.ates.event.business.Type
import toughdevschool.ates.event.business.task.v1.TaskAssigned
import toughdevschool.ates.event.business.task.v1.TaskCompleted
import java.time.Duration
import java.util.UUID
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Function

@Configuration
class BusinessEventConsumer(
    private val taskAssignedHandler: TaskAssignedHandler,
    private val taskCompletedHandler: TaskCompletedHandler,
    private val objectMapper: ObjectMapper,
) {

    @Bean
    fun tasks(): Function<Flux<Message<BusinessEvent<*>>>, Mono<Void>> =
        Function { eventFlux ->
            eventFlux.flatMap { message ->
                val event = message.payload
                when (event.type) {
                    Event.Type(Type.TaskAssigned, 1) -> handleTaskAssigned(event, objectMapper.convertValue(event))
                    Event.Type(Type.TaskCompleted, 1) -> handleTaskCompleted(event, objectMapper.convertValue(event))
                    else -> Mono.empty()
                }.retryWhen(Retry.backoff(5, Duration.ofMillis(500))) // move to config?
                    .doOnError { logger.error { "Retry failed" } } // move to platform
                    .onErrorComplete()
            }.then()
        }

    private fun handleTaskAssigned(event: BusinessEvent<*>, data: TaskAssigned): Mono<Void> =
        mono { taskAssignedHandler.handle(data) }.unpack(event.id)

    private fun handleTaskCompleted(event: BusinessEvent<*>, data: TaskCompleted): Mono<Void> =
        mono { taskCompletedHandler.handle(data) }.unpack(event.id)

    // move to platform
    private val logger = KotlinLogging.logger { }

    private val mapOfAtomics: MutableMap<UUID, AtomicInteger> = mutableMapOf() // for tests

    private fun <B : Any> Mono<Either<BusinessError, B>>.unpack(eventId: UUID): Mono<Void> =
        this.flatMap { either ->
            either.fold(
                {
                    val atomicInteger = mapOfAtomics.computeIfAbsent(eventId) { AtomicInteger() }
                    logger.warn { "Retry #${atomicInteger.getAndIncrement()} for Event id $eventId" }
                    Mono.error(MessageHandlingException(it))
                },
                { Mono.empty() }
            )
        }

    class MessageHandlingException(error: BusinessError) : RuntimeException("MessageHandlingException. Error: $error")
}
