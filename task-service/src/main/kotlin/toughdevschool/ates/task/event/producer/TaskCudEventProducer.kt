package toughdevschool.ates.task.event.producer

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import reactor.core.publisher.Flux
import software.darkmatter.platform.event.Event
import software.darkmatter.platform.event.cud.Operation
import software.darkmatter.platform.event.producer.CudEventProducer
import toughdevschool.ates.event.cud.CudEventSchemaRegistry
import toughdevschool.ates.event.cud.CudEventType
import java.util.function.Supplier
import toughdevschool.ates.event.cud.task.v2.TaskData as TaskDataV2

@Configuration
class TaskCudEventProducer(
    @Value("\${spring.application.name}") applicationName: String,
    buildProperties: BuildProperties
) : CudEventProducer<CudEventType>(applicationName, buildProperties) {

    override val schemaRegistry = CudEventSchemaRegistry

    @Bean
    fun tasksStream(): Supplier<Flux<Message<out Event<CudEventType, *>>>> = producerSupplier()

    suspend fun sendTaskV2(operation: Operation, data: TaskDataV2) =
        sendEvent(Event.Type(CudEventType.Task, 2), operation, data)
}
