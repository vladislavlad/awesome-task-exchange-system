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
import software.darkmatter.platform.event.Event
import software.darkmatter.platform.event.cud.CudEvent
import software.darkmatter.platform.event.cud.Operation
import toughdevschool.ates.accounting.domain.task.crud.handler.TaskCreateHandler
import toughdevschool.ates.accounting.domain.task.crud.handler.TaskUpdateHandler
import toughdevschool.ates.accounting.domain.user.handler.UserCreateHandler
import toughdevschool.ates.accounting.domain.user.handler.UserUpdateHandler
import toughdevschool.ates.accounting.domain.userRole.handler.UserRoleCreateHandler
import toughdevschool.ates.accounting.domain.userRole.handler.UserRoleDeleteHandler
import toughdevschool.ates.event.cud.CudEventType
import toughdevschool.ates.event.cud.user.v1.UserData
import toughdevschool.ates.event.cud.userRole.v1.UserRoleData
import java.time.Duration
import java.util.UUID
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Function
import toughdevschool.ates.event.cud.task.v2.TaskData as TaskDataV2

@Configuration
class CudEventConsumer(
    private val userCreateHandler: UserCreateHandler,
    private val userUpdateHandler: UserUpdateHandler,
    private val userRoleCreateHandler: UserRoleCreateHandler,
    private val userRoleDeleteHandler: UserRoleDeleteHandler,
    private val taskCreateHandler: TaskCreateHandler,
    private val taskUpdateHandler: TaskUpdateHandler,
    private val objectMapper: ObjectMapper,
) {

    @Bean
    fun accountsStream(): Function<Flux<Message<CudEvent<*, *>>>, Mono<Void>> =
        Function { eventFlux ->
            eventFlux.flatMap { message ->
                val event = message.payload
                when (event.type) {
                    Event.Type(CudEventType.User, 1) -> handleUser(event, objectMapper.convertValue(event.data))
                    Event.Type(CudEventType.UserRole, 1) -> handleUserRole(event, objectMapper.convertValue(event.data))
                    else -> Mono.empty()
                }.retryWhen(Retry.backoff(5, Duration.ofMillis(500))) // move to config?
                    .doOnError { logger.error { "Retry failed" } } // move to platform
                    .onErrorComplete()
            }.then()
        }

    @Bean
    fun tasksStream(): Function<Flux<Message<CudEvent<*, *>>>, Mono<Void>> =
        Function { eventFlux ->
            eventFlux.flatMap { message ->
                val event = message.payload
                when (event.type) {
                    Event.Type(CudEventType.Task, 2) -> handleTask(event, objectMapper.convertValue(event.data))
                    else -> Mono.empty()
                }.retryWhen(Retry.backoff(5, Duration.ofMillis(500))) // move to config?
                    .doOnError { logger.error { "Retry failed" } } // move to platform
                    .onErrorComplete()
            }.then()
        }

    private fun handleUser(cudEvent: CudEvent<*, *>, data: UserData): Mono<Void> =
        mono {
            when (cudEvent.operation) {
                Operation.Create -> userCreateHandler.handle(data)
                Operation.Update -> userUpdateHandler.handle(data)
                Operation.Delete -> TODO()
            }
        }.unpack(cudEvent.id)

    private fun handleUserRole(cudEvent: CudEvent<*, *>, data: UserRoleData) =
        mono {
            when (cudEvent.operation) {
                Operation.Create -> userRoleCreateHandler.handle(data)
                Operation.Update -> TODO()
                Operation.Delete -> userRoleDeleteHandler.handle(data)
            }
        }.unpack(cudEvent.id)

    private fun handleTask(cudEvent: CudEvent<*, *>, data: TaskDataV2): Mono<Void> =
        mono {
            when (cudEvent.operation) {
                Operation.Create -> taskCreateHandler.handle(data)
                Operation.Update -> taskUpdateHandler.handle(data)
                Operation.Delete -> TODO()
            }
        }.unpack(cudEvent.id)

    // move to platform
    private val logger = KotlinLogging.logger { }

    private val mapOfAtomics: MutableMap<UUID, AtomicInteger> = mutableMapOf()

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
