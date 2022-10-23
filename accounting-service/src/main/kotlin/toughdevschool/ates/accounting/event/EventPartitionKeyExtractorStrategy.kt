package toughdevschool.ates.accounting.event

import org.springframework.cloud.stream.binder.PartitionKeyExtractorStrategy
import org.springframework.messaging.Message
import org.springframework.stereotype.Component
import toughdevschool.ates.event.Event

@Component
class EventPartitionKeyExtractorStrategy : PartitionKeyExtractorStrategy {

    override fun extractKey(message: Message<*>?) =
        when (message?.payload) {
            is Event<*, *> -> (message.payload as Event<*, *>).key
            else -> null
        }
}