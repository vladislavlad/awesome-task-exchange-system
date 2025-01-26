package toughdevschool.ates.analytics.domain.task.cost.data

import software.darkmatter.platform.data.CoroutineCrudSortingRepository
import java.util.UUID

interface TaskCostRepository : CoroutineCrudSortingRepository<TaskCost, Long> {

    fun findByTaskUuid(taskUuid: UUID): TaskCost?
}
