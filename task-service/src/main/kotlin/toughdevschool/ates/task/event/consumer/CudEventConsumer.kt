package toughdevschool.ates.task.event.consumer

import arrow.core.Either
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
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
import toughdevschool.ates.event.cud.AnyCudEvent
import toughdevschool.ates.event.cud.CudEventType
import toughdevschool.ates.event.cud.user.v1.UserData
import toughdevschool.ates.event.cud.userRole.v1.UserRoleData
import toughdevschool.ates.task.domain.user.handler.UserCreateHandler
import toughdevschool.ates.task.domain.user.handler.UserUpdateHandler
import toughdevschool.ates.task.domain.userRole.handler.UserRoleCreateHandler
import toughdevschool.ates.task.domain.userRole.handler.UserRoleDeleteHandler
import java.time.Duration
import java.util.UUID
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Function

@Configuration
class CudEventConsumer(
    private val userCreateHandler: UserCreateHandler,
    private val userUpdateHandler: UserUpdateHandler,
    private val userRoleCreateHandler: UserRoleCreateHandler,
    private val userRoleDeleteHandler: UserRoleDeleteHandler,
    private val objectMapper: ObjectMapper,
) {

    @Bean
    fun accountsStream(): Function<Flux<Message<ByteArray>>, Mono<Void>> =
        Function { eventFlux ->
            eventFlux.flatMap { message ->
                val any = objectMapper.readValue(message.payload, AnyCudEvent::class.java)  // move to platform
                when (any!!.type) {
                    Event.Type(CudEventType.User, 1) -> handleUser(objectMapper.readValue(message.payload))
                    Event.Type(CudEventType.UserRole, 1) -> handleUserRole(objectMapper.readValue(message.payload))
                    else -> Mono.empty()
                }.retryWhen(Retry.backoff(5, Duration.ofMillis(500))) // move to config?
                    .doOnError { logger.error { "Retry failed" } } // move to platform
                    .onErrorComplete()
            }.then()
        }

    private fun handleUser(event: CudEvent<CudEventType, UserData>): Mono<Void> =
        mono {
            when (event.operation) {
                Operation.Create -> userCreateHandler.handle(event.data)
                Operation.Update -> userUpdateHandler.handle(event.data)
                Operation.Delete -> TODO()
            }
        }.unpack(event.id)

    private fun handleUserRole(event: CudEvent<CudEventType, UserRoleData>) =
        mono {
            when (event.operation) {
                Operation.Create -> userRoleCreateHandler.handle(event.data)
                Operation.Update -> TODO()
                Operation.Delete -> userRoleDeleteHandler.handle(event.data)
            }
        }.unpack(event.id)

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

    class MessageHandlingException(error: BusinessError) : RuntimeException("MessageHandlingException. Error: $error") // move to platform
}
