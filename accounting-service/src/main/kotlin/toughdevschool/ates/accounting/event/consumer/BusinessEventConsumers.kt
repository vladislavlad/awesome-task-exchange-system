package toughdevschool.ates.accounting.event.consumer

import arrow.core.Either
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.messaging.Message
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import software.darkmatter.platform.error.BusinessError
import software.darkmatter.platform.event.Event
import software.darkmatter.platform.event.KeyAware
import software.darkmatter.platform.event.business.BusinessEvent
import software.darkmatter.platform.event.config.ConsumerProperties
import software.darkmatter.platform.event.consumer.BusinessEventConsumer
import software.darkmatter.platform.event.consumer.extension.EventConsumerObservation
import toughdevschool.ates.accounting.domain.task.assign.TaskAssignedHandler
import toughdevschool.ates.accounting.domain.task.complete.TaskCompletedHandler
import toughdevschool.ates.event.business.BusinessEventSchemaRegistry
import toughdevschool.ates.event.business.BusinessEventType
import toughdevschool.ates.event.business.task.v1.TaskAssigned
import toughdevschool.ates.event.business.task.v1.TaskCompleted
import java.util.function.Function

@Component
class BusinessEventConsumers(
    private val taskAssignedHandler: TaskAssignedHandler,
    private val taskCompletedHandler: TaskCompletedHandler,
    override val consumerObservation: EventConsumerObservation,
    override val consumerProperties: ConsumerProperties,
    override val objectMapper: ObjectMapper,
) : BusinessEventConsumer<BusinessEventType>() {

    override val consumerName = "business-event-consumer"

    override val logger = KotlinLogging.logger { }

    override val schemaRegistry = BusinessEventSchemaRegistry

    @Bean
    fun tasks(): Function<Flux<Message<ByteArray>>, Mono<Void>> = consumerFunction(::handlingStrategy)

    suspend fun <D : KeyAware> handlingStrategy(event: BusinessEvent<BusinessEventType, D>): Either<BusinessError, Unit> =
        when (event.type) {
            Event.Type(BusinessEventType.TaskAssigned, 1) -> taskAssignedHandler.handle(event.data as TaskAssigned)
            Event.Type(BusinessEventType.TaskCompleted, 1) -> taskCompletedHandler.handle(event.data as TaskCompleted)
            else -> Either.Right(Unit)
        }
}
