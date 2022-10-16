package toughdevschool.ates.task.event.producer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import toughdevschool.ates.task.event.producer.cud.TaskStreamEvent
import java.util.function.Supplier

@Configuration
class CudEventProducer {

    private val unicastProcessor = Sinks.many().unicast().onBackpressureBuffer<Message<out TaskStreamEvent<*, *>>>()

    @Bean
    fun tasksStream(): Supplier<Flux<Message<out TaskStreamEvent<*, *>>>> =
        Supplier { unicastProcessor.asFlux() }

    suspend fun sendEvent(event: TaskStreamEvent<*, *>) {
        val messageEvent = MessageBuilder
            .withPayload(event)
            .setHeader(KafkaHeaders.MESSAGE_KEY, event.id.toString().toByteArray())
            .build()
        unicastProcessor.emitNext(messageEvent, Sinks.EmitFailureHandler.FAIL_FAST)
    }
}
