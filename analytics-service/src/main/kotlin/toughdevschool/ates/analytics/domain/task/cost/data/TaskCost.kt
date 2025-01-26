package toughdevschool.ates.analytics.domain.task.cost.data

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import software.darkmatter.platform.data.Model
import java.util.UUID

@Table("task_costs")
data class TaskCost(
    @Id
    override var id: Long? = null,
    val taskUuid: UUID,
    var assignCost: Int,
    var reward: Int,
) : Model<Long>
