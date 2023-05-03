package toughdevschool.ates.accounting.event.producer

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.messaging.Message
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import software.darkmatter.platform.event.Event
import software.darkmatter.platform.event.producer.BusinessEventProducer
import toughdevschool.ates.event.business.BusinessEventSchemaRegistry
import toughdevschool.ates.event.business.BusinessEventType
import toughdevschool.ates.event.business.transaction.v1.TransactionCompleted
import java.util.function.Supplier

@Component
class AccountingBusinessEventProducer(
    @Value("\${spring.application.name}") applicationName: String,
    buildProperties: BuildProperties
) : BusinessEventProducer<BusinessEventType>(applicationName, buildProperties) {

    override val logger = KotlinLogging.logger {}

    override val schemaRegistry = BusinessEventSchemaRegistry

    @Bean
    fun accounting(): Supplier<Flux<Message<out Event<BusinessEventType, *>>>> = producerSupplier()

    suspend fun sendTransactionCompletedV1(transactionCompleted: TransactionCompleted) =
        sendEvent(Event.Type(BusinessEventType.TransactionCompleted, 1), transactionCompleted)
}
