package toughdevschool.ates.task.domain.task.data

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import software.darkmatter.platform.data.Model
import java.time.OffsetDateTime
import java.util.UUID

@Table("tasks")
data class Task(
    @Id
    override var id: Long? = null,
    val uuid: UUID,
    var userUuid: UUID,
    var title: String,
    var description: String,
    var status: Status,
    var jiraId: String?,
    var completedAt: OffsetDateTime?,
    val createdAt: OffsetDateTime,
    val createdBy: UUID,
    var updatedAt: OffsetDateTime,
    var updatedBy: UUID,
) : Model<Long> {

    enum class Status {
        New,
        Completed,
    }
}
