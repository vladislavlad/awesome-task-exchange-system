package toughdevschool.ates.task.event.producer.business

import software.darkmatter.event.business.BusinessEvent
import java.util.UUID

data class TaskCompleted(
    override val id: UUID,
    override val source: String,
    val taskUuid: UUID,
    val userUuid: UUID,
) : BusinessEvent {

    override val key = id

    override val type = "TaskCompleted"
}
