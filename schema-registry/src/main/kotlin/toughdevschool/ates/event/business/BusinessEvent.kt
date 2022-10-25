package toughdevschool.ates.event.business

import toughdevschool.ates.event.Event
import toughdevschool.ates.event.KeyAware
import java.time.OffsetDateTime
import java.util.UUID

data class BusinessEvent<Data : KeyAware>(
    override val id: UUID,
    override val producer: String,
    override val producedAt: OffsetDateTime,
    override val type: Type<toughdevschool.ates.event.business.Type>,
    override val data: Data,
) : Event<Type, Data>(id, data.key, Metadata.B_V1, producer, producedAt, type, data)
