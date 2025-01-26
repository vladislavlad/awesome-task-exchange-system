package toughdevschool.ates.analytics.domain.task.cost.data

import org.springframework.data.r2dbc.core.R2dbcEntityOperations
import org.springframework.stereotype.Repository
import software.darkmatter.platform.data.PagingRepository

@Repository
class TaskCostPagingRepository(
    override val r2dbcOperations: R2dbcEntityOperations
) : PagingRepository<TaskCost, Long> {

    override val klass = TaskCost::class
}
