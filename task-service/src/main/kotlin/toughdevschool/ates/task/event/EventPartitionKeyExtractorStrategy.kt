package toughdevschool.ates.task.event

import org.springframework.cloud.stream.binder.PartitionKeyExtractorStrategy
import org.springframework.messaging.Message
import org.springframework.stereotype.Component
import software.darkmatter.event.cud.CudEvent
import toughdevschool.ates.event.business.BusinessEvent

@Component
class EventPartitionKeyExtractorStrategy : PartitionKeyExtractorStrategy {

    override fun extractKey(message: Message<*>?) =
        when (message?.payload) {
            is CudEvent<*, *> -> (message.payload as CudEvent<*, *>).data.key
            is BusinessEvent<*> -> (message.payload as BusinessEvent<*>).data.key
            else -> null
        }
}