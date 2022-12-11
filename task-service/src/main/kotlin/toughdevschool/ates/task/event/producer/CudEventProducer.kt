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
import software.darkmatter.platform.event.cud.CudEvent
import software.darkmatter.platform.event.cud.Operation
import toughdevschool.ates.event.cud.CudEventType
import java.time.OffsetDateTime
import java.util.UUID
import java.util.function.Supplier
import toughdevschool.ates.event.cud.task.v2.TaskData as TaskDataV2

@Configuration
class CudEventProducer(
    @Value("\${spring.application.name}")
    private val applicationName: String,
    private val buildProperties: BuildProperties
) {

    private val unicastProcessor = Sinks.many().unicast().onBackpressureBuffer<Message<out CudEvent<*, *>>>()

    @Bean
    fun tasksStream(): Supplier<Flux<Message<out CudEvent<*, *>>>> =
        Supplier { unicastProcessor.asFlux() }

    suspend fun sendTaskV2(operation: Operation, data: TaskDataV2) =
        sendEvent(Event.Type(CudEventType.Task, 2), operation, data)

    private suspend fun <T : KeyAware> sendEvent(type: Event.Type<CudEventType>, operation: Operation, data: T) {
        val event = CudEvent(
            id = UUID.randomUUID(),
            metadata = Event.Metadata.CudV1("$applicationName:${buildProperties.version}", OffsetDateTime.now()),
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
