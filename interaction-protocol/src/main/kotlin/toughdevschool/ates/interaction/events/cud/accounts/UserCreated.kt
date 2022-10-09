package toughdevschool.ates.interaction.events.cud.accounts

import toughdevschool.ates.interaction.events.model.UserInfo
import toughdevschool.ates.interaction.events.Event
import java.util.UUID

data class UserCreated(
    override val id: UUID,
    val userInfo: UserInfo,
) : Event
