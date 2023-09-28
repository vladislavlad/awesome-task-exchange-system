package toughdevschool.ates.analytics.event.consumer

import arrow.core.Either
import com.fasterxml.jackson.databind.ObjectMapper
import io.micrometer.observation.ObservationRegistry
import mu.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.messaging.Message
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import software.darkmatter.platform.error.BusinessError
import software.darkmatter.platform.event.Event
import software.darkmatter.platform.event.KeyAware
import software.darkmatter.platform.event.config.ConsumerProperties
import software.darkmatter.platform.event.consumer.CudEventConsumer
import software.darkmatter.platform.event.cud.CudEvent
import software.darkmatter.platform.event.cud.Operation
import toughdevschool.ates.analytics.domain.task.crud.handler.TaskCreateHandler
import toughdevschool.ates.analytics.domain.task.crud.handler.TaskUpdateHandler
import toughdevschool.ates.event.cud.CudEventSchemaRegistry
import toughdevschool.ates.event.cud.CudEventType
import toughdevschool.ates.event.cud.task.v2.TaskData
import java.util.function.Function

@Component
class TaskCudEventConsumer(
    private val taskCreateHandler: TaskCreateHandler,
    private val taskUpdateHandler: TaskUpdateHandler,
    override val observationRegistry: ObservationRegistry,
    override val consumerProperties: ConsumerProperties,
    override val objectMapper: ObjectMapper,
) : CudEventConsumer<CudEventType>() {

    override val consumerName = "tasks-cud-consumer"

    override val logger = KotlinLogging.logger { }

    override val schemaRegistry = CudEventSchemaRegistry

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