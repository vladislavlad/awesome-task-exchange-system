package toughdevschool.ates.analytics.domain.task.crud.data

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
    var userUuid: UUID?,
    var title: String,
    var status: Status,
    val assignCost: Int,
    val reward: Int,
    val createdAt: OffsetDateTime,
    var completedAt: OffsetDateTime?,
) : Model<Long> {

    enum class Status {
        New,
        Completed,
    }
}
