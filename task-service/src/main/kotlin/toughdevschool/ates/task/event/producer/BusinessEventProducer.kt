package toughdevschool.ates.task.event.producer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import toughdevschool.ates.event.Event
import toughdevschool.ates.event.KeyAware
import toughdevschool.ates.event.business.BusinessEvent
import toughdevschool.ates.event.business.Type
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
        sendEvent(Event.Type(Type.TaskCompleted, 1), taskCompleted)

    suspend fun sendTaskCompletedV1(taskCompleted: TaskCompleted) =
        sendEvent(Event.Type(Type.TaskCompleted, 1), taskCompleted)

    private suspend fun <T : KeyAware> sendEvent(type: Event.Type<Type>, data: T) {
        val businessEvent = BusinessEvent(
            id = UUID.randomUUID(),
            producer = Constants.SERVICE_NAME,
            producedAt = OffsetDateTime.now(),
            type = type,
            data = data
        )
        val messageEvent = MessageBuilder
            .withPayload(businessEvent)
            .setHeader(KafkaHeaders.MESSAGE_KEY, businessEvent.key.toByteArray())
            .build()
        unicastProcessor.emitNext(messageEvent, Sinks.EmitFailureHandler.FAIL_FAST)
    }
}
