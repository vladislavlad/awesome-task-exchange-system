package toughdevschool.ates.interaction.events.cud.accounts

import toughdevschool.ates.interaction.events.Event
import toughdevschool.ates.interaction.events.model.UserInfo
import java.util.UUID

data class UserDeleted(
    override val id: UUID,
    val userInfo: UserInfo,
) : Event
