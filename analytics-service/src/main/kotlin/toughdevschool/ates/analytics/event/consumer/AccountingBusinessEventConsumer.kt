package toughdevschool.ates.analytics.event.consumer

import arrow.core.Either
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import io.micrometer.observation.ObservationRegistry
import org.springframework.context.annotation.Bean
import org.springframework.messaging.Message
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import software.darkmatter.platform.error.BusinessError
import software.darkmatter.platform.event.Event
import software.darkmatter.platform.event.KeyAware
import software.darkmatter.platform.event.business.BusinessEvent
import software.darkmatter.platform.event.config.ConsumerProperties
import software.darkmatter.platform.event.consumer.BusinessEventConsumer
import toughdevschool.ates.analytics.domain.transaction.handler.TransactionCompletedHandler
import toughdevschool.ates.event.business.BusinessEventSchemaRegistry
import toughdevschool.ates.event.business.BusinessEventType
import toughdevschool.ates.event.business.transaction.v1.TransactionCompleted
import java.util.function.Function

@Component
class AccountingBusinessEventConsumer(
    private val txCompletedHandler: TransactionCompletedHandler,
    override val observationRegistry: ObservationRegistry,
    override val consumerProperties: ConsumerProperties,
    override val objectMapper: ObjectMapper,
) : BusinessEventConsumer<BusinessEventType>() {

    override val consumerName = "accounting-business-event-consumer"

    override val logger = KotlinLogging.logger { }

    override val schemaRegistry = BusinessEventSchemaRegistry

    companion object {
        val TransactionCompletedV1 = Event.Type(BusinessEventType.TransactionCompleted, 1)
    }

    @Bean
    fun accounting(): Function<Flux<Message<ByteArray>>, Mono<Void>> = consumerFunction(::handlingStrategy)

    suspend fun <D : KeyAware> handlingStrategy(event: BusinessEvent<BusinessEventType, D>): Either<BusinessError, Unit> =
        when (event.type) {
            TransactionCompletedV1 -> txCompletedHandler.handle(event.data as TransactionCompleted)
            else -> Either.Right(Unit)
        }
}
