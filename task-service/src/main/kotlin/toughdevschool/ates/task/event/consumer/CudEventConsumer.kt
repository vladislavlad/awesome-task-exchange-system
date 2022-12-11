package toughdevschool.ates.task.event.consumer

import arrow.core.Either
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import software.darkmatter.platform.error.BusinessError
import software.darkmatter.platform.event.Event
import software.darkmatter.platform.event.KeyAware
import software.darkmatter.platform.event.consumer.ConsumerFunction
import software.darkmatter.platform.event.cud.CudEvent
import software.darkmatter.platform.event.cud.Operation
import toughdevschool.ates.event.cud.CudEventRegistry
import toughdevschool.ates.event.cud.CudEventType
import toughdevschool.ates.event.cud.user.v1.UserData
import toughdevschool.ates.event.cud.userRole.v1.UserRoleData
import toughdevschool.ates.task.domain.user.handler.UserCreateHandler
import toughdevschool.ates.task.domain.user.handler.UserUpdateHandler
import toughdevschool.ates.task.domain.userRole.handler.UserRoleCreateHandler
import toughdevschool.ates.task.domain.userRole.handler.UserRoleDeleteHandler
import java.util.function.Function

@Configuration
class CudEventConsumer(
    private val userCreateHandler: UserCreateHandler,
    private val userUpdateHandler: UserUpdateHandler,
    private val userRoleCreateHandler: UserRoleCreateHandler,
    private val userRoleDeleteHandler: UserRoleDeleteHandler,
    private val consumerFunction: ConsumerFunction,
) {

    @Bean
    fun accountsStream(): Function<Flux<Message<ByteArray>>, Mono<Void>> =
        consumerFunction.f(
            CudEventType::class,
            CudEventRegistry.cudEvents,
            ::mapp
        )

    @Suppress("UNCHECKED_CAST")
    suspend fun <D : KeyAware> mapp(event: CudEvent<CudEventType, D>): Either<BusinessError, Unit> =
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
}
