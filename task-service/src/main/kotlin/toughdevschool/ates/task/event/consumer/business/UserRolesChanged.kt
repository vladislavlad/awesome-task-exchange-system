package toughdevschool.ates.task.event.consumer.business

import software.darkmatter.event.business.BusinessEvent
import java.util.UUID

data class UserRolesChanged(
    override val id: UUID,
    override val source: String,
    val userUuid: UUID,
    val roles: List<String>,
) : BusinessEvent {

    override val key = id

    override val type = "UserRolesChanged"
}
