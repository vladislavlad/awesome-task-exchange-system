package toughdevschool.ates.task.event.producer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import software.darkmatter.event.Event
import software.darkmatter.event.KeyAware
import toughdevschool.ates.event.business.BusinessEvent
import toughdevschool.ates.event.business.BusinessEventType
import toughdevschool.ates.event.business.task.v1.TaskAssigned
import toughdevschool.ates.event.business.task.v1.TaskCompleted
import toughdevschool.ates.task.Constants
import java.time.OffsetDateTime
import java.util.UUID
import java.util.function.Supplier

@Configuration
class BusinessEventProducer {

    private val unicastProcessor = Sinks.many().unicast().onBackpressureBuffer<Message<out BusinessEvent<*>>>()

    @Bean
    fun tasks(): Supplier<Flux<Message<out BusinessEvent<*>>>> =
        Supplier { unicastProcessor.asFlux() }

    suspend fun sendTaskAssignedV1(taskCompleted: TaskAssigned) =
        sendEvent(Event.Type(BusinessEventType.TaskCompleted, 1), taskCompleted)

    suspend fun sendTaskCompletedV1(taskCompleted: TaskCompleted) =
        sendEvent(Event.Type(BusinessEventType.TaskCompleted, 1), taskCompleted)

    private suspend fun <T : KeyAware> sendEvent(type: Event.Type<BusinessEventType>, data: T) {
        val event = BusinessEvent(
            id = UUID.randomUUID(),
            metadata = Event.Metadata.BusinessV1(Constants.SERVICE_NAME, OffsetDateTime.now()),
            type = type,
            data = data
        )
        val message = MessageBuilder
            .withPayload(event)
            .setHeader(KafkaHeaders.MESSAGE_KEY, event.data.key.toByteArray())
            .build()
        unicastProcessor.emitNext(message, Sinks.EmitFailureHandler.FAIL_FAST)
    }
}
