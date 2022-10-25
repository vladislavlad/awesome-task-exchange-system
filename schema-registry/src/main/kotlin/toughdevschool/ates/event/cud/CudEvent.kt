package toughdevschool.ates.event.cud

import toughdevschool.ates.event.Event
import toughdevschool.ates.event.KeyAware
import java.time.OffsetDateTime
import java.util.UUID

data class CudEvent<Data : KeyAware>(
    override val id: UUID,
    override val producer: String,
    override val producedAt: OffsetDateTime,
    override val type: Type<toughdevschool.ates.event.cud.Type>,
    override val operation: Operation,
    override val data: Data,
) : Event<Type, Data>(id, data.key, Metadata.CUD_V1, producer, producedAt, type, data),
    OperationAware
