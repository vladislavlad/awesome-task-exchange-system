package toughdevschool.ates.event.business

import software.darkmatter.event.Event
import software.darkmatter.event.KeyAware
import java.util.UUID

data class BusinessEvent<Data : KeyAware>(
    override val id: UUID,
    override val metadata: Metadata.BusinessV1,
    override val type: Type<BusinessEventType>,
    override val data: Data,
) : Event<BusinessEventType, Data>(id, metadata, type, data)
