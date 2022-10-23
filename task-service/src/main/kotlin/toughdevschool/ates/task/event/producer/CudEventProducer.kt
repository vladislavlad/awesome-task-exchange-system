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
import toughdevschool.ates.event.cud.CudEvent
import toughdevschool.ates.event.cud.Operation
import toughdevschool.ates.event.cud.Type
import toughdevschool.ates.task.Constants
import java.time.OffsetDateTime
import java.util.UUID
import java.util.function.Supplier
import toughdevschool.ates.event.cud.task.v2.TaskData as TaskDataV2

@Configuration
class CudEventProducer {

    private val unicastProcessor = Sinks.many().unicast().onBackpressureBuffer<Message<out CudEvent<*>>>()

    @Bean
    fun tasksStream(): Supplier<Flux<Message<out CudEvent<*>>>> =
        Supplier { unicastProcessor.asFlux() }

    suspend fun sendTaskV2(operation: Operation, data: TaskDataV2) =
        sendEvent(Event.Type(Type.Task, 2), operation, data)

    private suspend fun <T : KeyAware> sendEvent(type: Event.Type<Type>, operation: Operation, data: T) {
        val event = CudEvent(
            id = UUID.randomUUID(),
            producer = Constants.SERVICE_NAME,
            producedAt = OffsetDateTime.now(),
            type = type,
            operation = operation,
            data = data,
        )
        val messageEvent = MessageBuilder
            .withPayload(event)
            .setHeader(KafkaHeaders.MESSAGE_KEY, event.id.toString().toByteArray())
            .build()
        unicastProcessor.emitNext(messageEvent, Sinks.EmitFailureHandler.FAIL_FAST)
    }
}
