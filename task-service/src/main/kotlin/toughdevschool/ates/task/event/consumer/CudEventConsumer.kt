package toughdevschool.ates.task.event.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import kotlinx.coroutines.reactor.mono
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import software.darkmatter.event.cud.CudEvent.Type
import toughdevschool.ates.task.domain.user.handler.CreateHandler
import toughdevschool.ates.task.domain.user.handler.UpdateHandler
import toughdevschool.ates.task.event.consumer.cud.CudEvent
import toughdevschool.ates.task.event.consumer.cud.model.ConsumerEntities
import toughdevschool.ates.task.event.consumer.cud.model.UserData
import java.util.function.Function

@Configuration
class CudEventConsumer(
    private val userCreateHandler: CreateHandler,
    private val userUpdateHandler: UpdateHandler,
    private val objectMapper: ObjectMapper,
) {

    @Bean
    fun accountsStream(): Function<Flux<CudEvent>, Mono<Void>> =
        Function { eventFlux ->
            eventFlux.flatMap {
                when (it.entity) {
                    ConsumerEntities.User.name -> mono { handleUserCud(it, objectMapper.convertValue(it.data)) }
                    else -> Mono.empty()
                }
            }.then()
        }

    private suspend fun handleUserCud(cudEvent: CudEvent, data: UserData) {
        when (cudEvent.type) {
            Type.Create -> userCreateHandler.handle(data)
            Type.Update -> userUpdateHandler.handle(data)
            Type.Delete -> TODO()
        }
    }
}
