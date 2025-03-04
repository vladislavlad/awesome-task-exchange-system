package toughdevschool.ates.task.event.producer

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import reactor.core.publisher.Flux
import software.darkmatter.platform.event.Event
import software.darkmatter.platform.event.cud.Operation
import software.darkmatter.platform.event.producer.CudEventStreamProducer
import toughdevschool.ates.event.cud.CudEventSchemaRegistry
import toughdevschool.ates.event.cud.CudEventSchemaRegistry.version
import toughdevschool.ates.event.cud.CudEventType
import java.util.function.Supplier
import toughdevschool.ates.event.cud.task.v3.TaskData as TaskDataV3

@Configuration
class TaskCudEventStreamProducer(
    @Value("\${spring.application.name}") applicationName: String,
    buildProperties: BuildProperties
) : CudEventStreamProducer<CudEventType>(applicationName, buildProperties),
    TaskCudEventProducer {

    override val logger = KotlinLogging.logger { }

    override val schemaRegistry = CudEventSchemaRegistry

    @Bean
    fun tasksStream(): Supplier<Flux<Message<out Event<CudEventType, *>>>> = producerSupplier()

    override suspend fun sendTaskV3(operation: Operation, data: TaskDataV3) =
        sendEvent(CudEventType.Task.version(3), operation, data)
}
