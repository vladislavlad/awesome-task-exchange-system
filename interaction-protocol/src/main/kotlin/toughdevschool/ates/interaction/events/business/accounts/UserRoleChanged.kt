package toughdevschool.ates.interaction.events.business.accounts

import toughdevschool.ates.interaction.events.Event
import java.util.UUID

data class UserRoleChanged(
    override val id: UUID,
    val userUuid: UUID,
    val roles: List<String>,
) : Event
