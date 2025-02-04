package software.darkmatter.domain.user

import kotlin.uuid.Uuid

data class User(
    val id: String,
    val uuid: Uuid,
    val username: String,
    var firstName: String?,
    var lastName: String?,
    var middleName: String?,
)
