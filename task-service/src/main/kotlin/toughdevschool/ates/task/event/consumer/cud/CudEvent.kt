package toughdevschool.ates.task.event.consumer.cud

import software.darkmatter.event.cud.CudEvent
import java.util.UUID

data class CudEvent(
    override val id: UUID,
    override val key: UUID,
    override val type: CudEvent.Type,
    override val data: Any,
    override val entity: String,
    override val source: String,
) : CudEvent<Any, UUID>
