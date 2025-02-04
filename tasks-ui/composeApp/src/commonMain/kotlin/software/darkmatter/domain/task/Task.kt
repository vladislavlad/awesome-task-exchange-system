package software.darkmatter.domain.task

import kotlin.uuid.Uuid

data class Task  constructor(
    val id: Long,
    val uuid: Uuid,
    var userUuid: Uuid,
    val title: String,
    val description: String,
    var status: Status,
) {

    enum class Status {
        New,
        Completed,
    }
}
