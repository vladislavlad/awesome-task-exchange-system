package toughdevschool.ates.event

import java.time.OffsetDateTime
import java.util.UUID

open class Event<DataType, Data>(
    open val id: UUID,
    override val key: String,
    open val metadata: Metadata,
    open val producer: String,
    open val producedAt: OffsetDateTime,
    open val type: Type<DataType>,
    open val data: Data,
) : KeyAware {

    data class Metadata(
        val type: Type,
        val version: Int,
    ) {

        enum class Type {
            CudEvent,
            BusinessEvent,
        }

        companion object {

            val CUD_V1 = Metadata(Type.CudEvent, 1)

            val B_V1 = Metadata(Type.BusinessEvent, 1)
        }
    }

    data class Type<T>(
        val type: T,
        val version: Int,
    )
}
