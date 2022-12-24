package toughdevschool.ates.accounting.event.consumer

import arrow.core.Either
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import software.darkmatter.platform.error.BusinessError
import software.darkmatter.platform.event.Event
import software.darkmatter.platform.event.KeyAware
import software.darkmatter.platform.event.config.ConsumerProperties
import software.darkmatter.platform.event.consumer.CudEventConsumer
import software.darkmatter.platform.event.cud.CudEvent
import software.darkmatter.platform.event.cud.Operation
import toughdevschool.ates.accounting.domain.task.crud.handler.TaskCreateHandler
import toughdevschool.ates.accounting.domain.task.crud.handler.TaskUpdateHandler
import toughdevschool.ates.accounting.domain.user.handler.UserCreateHandler
import toughdevschool.ates.accounting.domain.user.handler.UserUpdateHandler
import toughdevschool.ates.accounting.domain.userRole.handler.UserRoleCreateHandler
import toughdevschool.ates.accounting.domain.userRole.handler.UserRoleDeleteHandler
import toughdevschool.ates.event.cud.CudEventSchemaRegistry
import toughdevschool.ates.event.cud.CudEventType
import toughdevschool.ates.event.cud.task.v2.TaskData
import toughdevschool.ates.event.cud.user.v1.UserData
import toughdevschool.ates.event.cud.userRole.v1.UserRoleData
import java.util.function.Function

@Configuration
class CudEventConsumerConfig(
    private val userCreateHandler: UserCreateHandler,
    private val userUpdateHandler: UserUpdateHandler,
    private val userRoleCreateHandler: UserRoleCreateHandler,
    private val userRoleDeleteHandler: UserRoleDeleteHandler,
    private val taskCreateHandler: TaskCreateHandler,
    private val taskUpdateHandler: TaskUpdateHandler,
    consumerProperties: ConsumerProperties,
    objectMapper: ObjectMapper,
) : CudEventConsumer<CudEventType>(consumerProperties, objectMapper) {

    override val logger = KotlinLogging.logger { }

    override val schemaRegistry = CudEventSchemaRegistry

    @Bean
    fun accountsStream(): Function<Flux<Message<ByteArray>>, Mono<Void>> = consumerFunction(::accountsHandlingStrategy)

    @Suppress("UNCHECKED_CAST")
    suspend fun <D : KeyAware> accountsHandlingStrategy(event: CudEvent<CudEventType, D>): Either<BusinessError, Unit> =
        when (event.type) {
            Event.Type(CudEventType.User, 1) -> handleUser(event as CudEvent<CudEventType, UserData>)
            Event.Type(CudEventType.UserRole, 1) -> handleUserRole(event as CudEvent<CudEventType, UserRoleData>)
            else -> Either.Right(Unit)
        }

    private suspend fun handleUser(event: CudEvent<CudEventType, UserData>) =
        when (event.operation) {
            Operation.Create -> userCreateHandler.handle(event.data)
            Operation.Update -> userUpdateHandler.handle(event.data)
            Operation.Delete -> TODO()
        }

    private suspend fun handleUserRole(event: CudEvent<CudEventType, UserRoleData>) =
        when (event.operation) {
            Operation.Create -> userRoleCreateHandler.handle(event.data)
            Operation.Update -> TODO()
            Operation.Delete -> userRoleDeleteHandler.handle(event.data)
        }

    @Bean
    fun tasksStream(): Function<Flux<Message<ByteArray>>, Mono<Void>> = consumerFunction(::tasksHandlingStrategy)

    @Suppress("UNCHECKED_CAST")
    suspend fun <D : KeyAware> tasksHandlingStrategy(event: CudEvent<CudEventType, D>): Either<BusinessError, Unit> =
        when (event.type) {
            Event.Type(CudEventType.Task, 2) -> handleTask(event as CudEvent<CudEventType, TaskData>)
            else -> Either.Right(Unit)
        }

    private suspend fun handleTask(event: CudEvent<CudEventType, TaskData>) =
        when (event.operation) {
            Operation.Create -> taskCreateHandler.handle(event.data)
            Operation.Update -> taskUpdateHandler.handle(event.data)
            Operation.Delete -> TODO()
        }
}
