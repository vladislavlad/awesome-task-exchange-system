package toughdevschool.ates.task.event.producer

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import software.darkmatter.platform.event.Event
import software.darkmatter.platform.event.KeyAware
import software.darkmatter.platform.event.business.BusinessEvent
import toughdevschool.ates.event.business.BusinessEventType
import toughdevschool.ates.event.business.task.v1.TaskAssigned
import toughdevschool.ates.event.business.task.v1.TaskCompleted
import java.time.OffsetDateTime
import java.util.UUID
import java.util.function.Supplier

@Configuration
class BusinessEventProducer(
    @Value("\${spring.application.name}")
    private val applicationName: String,
    private val buildProperties: BuildProperties
) {

    private val unicastProcessor = Sinks.many().unicast().onBackpressureBuffer<Message<out BusinessEvent<*, *>>>()

    @Bean
    fun tasks(): Supplier<Flux<Message<out BusinessEvent<*, *>>>> =
        Supplier { unicastProcessor.asFlux() }

    suspend fun sendTaskAssignedV1(taskCompleted: TaskAssigned) =
        sendEvent(Event.Type(BusinessEventType.TaskCompleted, 1), taskCompleted)

    suspend fun sendTaskCompletedV1(taskCompleted: TaskCompleted) =
        sendEvent(Event.Type(BusinessEventType.TaskCompleted, 1), taskCompleted)

    private suspend fun <T : KeyAware> sendEvent(type: Event.Type<BusinessEventType>, data: T) {
        val event = BusinessEvent(
            id = UUID.randomUUID(),
            metadata = Event.Metadata.BusinessV1("$applicationName:${buildProperties.version}", OffsetDateTime.now()),
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
