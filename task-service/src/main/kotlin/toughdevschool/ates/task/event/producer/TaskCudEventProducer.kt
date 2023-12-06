package toughdevschool.ates.task.event.producer

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import reactor.core.publisher.Flux
import software.darkmatter.platform.event.Event
import software.darkmatter.platform.event.cud.Operation
import toughdevschool.ates.event.cud.CudEventSchemaRegistry
import toughdevschool.ates.event.cud.CudEventSchemaRegistry.version
import toughdevschool.ates.event.cud.CudEventType
import java.util.function.Supplier
import software.darkmatter.platform.event.producer.CudEventProducer as CudEventStreamProducer
import toughdevschool.ates.event.cud.task.v2.TaskData as TaskDataV2

@Configuration
class TaskCudEventProducer(
    @Value("\${spring.application.name}") applicationName: String,
    buildProperties: BuildProperties
) : CudEventStreamProducer<CudEventType>(applicationName, buildProperties) {

    override val logger = KotlinLogging.logger { }

    override val schemaRegistry = CudEventSchemaRegistry

    @Bean
    fun tasksStream(): Supplier<Flux<Message<out Event<CudEventType, *>>>> = producerSupplier()

    suspend fun sendTaskV2(operation: Operation, data: TaskDataV2) =
        sendEvent(CudEventType.Task.version(2), operation, data)
}
