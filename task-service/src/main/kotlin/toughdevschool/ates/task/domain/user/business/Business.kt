package toughdevschool.ates.task.domain.user.business

import toughdevschool.ates.task.domain.user.data.User
import java.util.UUID

data class UserCreate(
    var uuid: UUID,
    var username: String,
    var firstName: String? = null,
    var lastName: String? = null,
    var middleName: String? = null,
)

data class UserUpdate(
    val user: User,
    val firstName: String?,
    val lastName: String?,
    val middleName: String?,
)
