package toughdevschool.ates.analytics.domain.task.crud.data

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Repository
import software.darkmatter.platform.data.PagingRepository
import toughdevschool.ates.analytics.domain.task.crud.data.Task

@Repository
class TaskPagingRepository(
    override val r2dbcTemplate: R2dbcEntityTemplate
) : PagingRepository<Task, Long> {

    override val klass = Task::class
}
