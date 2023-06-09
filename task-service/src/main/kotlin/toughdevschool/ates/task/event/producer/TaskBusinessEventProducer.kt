package toughdevschool.ates.task.event.producer

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import reactor.core.publisher.Flux
import software.darkmatter.platform.event.Event
import software.darkmatter.platform.event.producer.BusinessEventProducer
import toughdevschool.ates.event.business.BusinessEventSchemaRegistry
import toughdevschool.ates.event.business.BusinessEventSchemaRegistry.version
import toughdevschool.ates.event.business.BusinessEventType
import toughdevschool.ates.event.business.task.v1.TaskAssigned
import toughdevschool.ates.event.business.task.v1.TaskCompleted
import java.util.function.Supplier

@Configuration
class TaskBusinessEventProducer(
    @Value("\${spring.application.name}") applicationName: String,
    buildProperties: BuildProperties,
) : BusinessEventProducer<BusinessEventType>(applicationName, buildProperties) {

    override val logger = KotlinLogging.logger { }

    override val schemaRegistry = BusinessEventSchemaRegistry

    @Bean
    fun tasks(): Supplier<Flux<Message<out Event<BusinessEventType, *>>>> = producerSupplier()

    suspend fun sendTaskAssignedV1(taskAssigned: TaskAssigned) =
        sendEvent(BusinessEventType.TaskAssigned.version(1), taskAssigned)

    suspend fun sendTaskCompletedV1(taskCompleted: TaskCompleted) =
        sendEvent(BusinessEventType.TaskCompleted.version(1), taskCompleted)
}
