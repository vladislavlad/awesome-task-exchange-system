package toughdevschool.ates.analytics.domain.user.business

import toughdevschool.ates.analytics.domain.user.data.User
import java.util.UUID

data class UserCreate(
    val uuid: UUID,
    val username: String,
    val firstName: String? = null,
    val lastName: String? = null,
    val middleName: String? = null,
)

data class UserUpdate(
    val user: User,
    val firstName: String?,
    val lastName: String?,
    val middleName: String?,
)
