package toughdevschool.ates.analytics.domain.task.crud.data

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import software.darkmatter.platform.data.Model
import java.util.UUID

@Table("tasks")
data class Task(
    @Id
    override var id: Long? = null,
    val uuid: UUID,
    var title: String,
    val assignCost: Int,
    val reward: Int,
    var userUuid: UUID?,
) : Model<Long>
