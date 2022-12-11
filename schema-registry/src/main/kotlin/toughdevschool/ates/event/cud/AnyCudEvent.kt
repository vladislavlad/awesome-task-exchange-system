package toughdevschool.ates.event.cud

import software.darkmatter.platform.event.Event
import java.util.UUID

data class AnyCudEvent(
    val id: UUID,
    val type: Event.Type<CudEventType>,
)
