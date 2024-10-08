package toughdevschool.ates.task.event.producer

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import reactor.core.publisher.Flux
import software.darkmatter.platform.event.Event
import software.darkmatter.platform.event.producer.BusinessEventStreamProducer
import toughdevschool.ates.event.business.BusinessEventSchemaRegistry
import toughdevschool.ates.event.business.BusinessEventSchemaRegistry.version
import toughdevschool.ates.event.business.BusinessEventType
import toughdevschool.ates.event.business.task.v1.TaskAssigned
import toughdevschool.ates.event.business.task.v1.TaskCompleted
import java.util.function.Supplier

@Configuration
class TaskBusinessEventStreamProducer(
    @Value("\${spring.application.name}") applicationName: String,
    buildProperties: BuildProperties,
) : BusinessEventStreamProducer<BusinessEventType>(applicationName, buildProperties),
    TaskBusinessEventProducer {

    override val logger = KotlinLogging.logger { }

    override val schemaRegistry = BusinessEventSchemaRegistry

    @Bean
    fun tasks(): Supplier<Flux<Message<out Event<BusinessEventType, *>>>> = producerSupplier()

    override suspend fun sendTaskAssignedV1(taskAssigned: TaskAssigned) =
        sendEvent(BusinessEventType.TaskAssigned.version(1), taskAssigned)

    override suspend fun sendTaskCompletedV1(taskCompleted: TaskCompleted) =
        sendEvent(BusinessEventType.TaskCompleted.version(1), taskCompleted)
}
