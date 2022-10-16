package toughdevschool.ates.task.event.consumer

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
import software.darkmatter.event.cud.CudEvent.Type
import software.darkmatter.platform.error.BusinessError
import toughdevschool.ates.task.domain.user.handler.UserCreateHandler
import toughdevschool.ates.task.domain.user.handler.UserUpdateHandler
import toughdevschool.ates.task.domain.userRole.handler.UserRoleCreateHandler
import toughdevschool.ates.task.domain.userRole.handler.UserRoleDeleteHandler
import toughdevschool.ates.task.event.consumer.cud.CudEvent
import toughdevschool.ates.task.event.consumer.cud.model.ConsumerEntities
import toughdevschool.ates.task.event.consumer.cud.model.UserData
import toughdevschool.ates.task.event.consumer.cud.model.UserRoleData
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

    private val logger = KotlinLogging.logger { }

    private val mapOfAtomics: MutableMap<UUID, AtomicInteger> = mutableMapOf() // for tests

    @Bean
    fun accountsStream(): Function<Flux<Message<CudEvent>>, Mono<Void>> =
        Function { eventFlux ->
            eventFlux.flatMap { message ->
                val event = message.payload
                when (event.entity) {
                    ConsumerEntities.User.name -> handleUser(event, objectMapper.convertValue(event.data))
                    ConsumerEntities.UserRole.name -> handleUserRole(event, objectMapper.convertValue(event.data))
                    else -> Mono.empty()
                }.retryWhen(Retry.backoff(5, Duration.ofMillis(500))) // move to config?
                    .doOnError { logger.error { "Retry failed" } } // move to platform
                    .onErrorComplete()
            }.then()
        }

    private fun handleUser(cudEvent: CudEvent, data: UserData): Mono<Void> =
        mono {
            when (cudEvent.type) {
                Type.Create -> userCreateHandler.handle(data)
                Type.Update -> userUpdateHandler.handle(data)
                Type.Delete -> TODO()
            }
        }.unpack(cudEvent.id)

    private fun handleUserRole(cudEvent: CudEvent, data: UserRoleData) =
        mono {
            when (cudEvent.type) {
                Type.Create -> userRoleCreateHandler.handle(data)
                Type.Update -> TODO()
                Type.Delete -> userRoleDeleteHandler.handle(data)
            }
        }.unpack(cudEvent.id)

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
