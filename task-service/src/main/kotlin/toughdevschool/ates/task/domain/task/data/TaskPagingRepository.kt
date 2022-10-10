package toughdevschool.ates.task.domain.task.data

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Repository
import software.darkmatter.platform.data.PagingRepository

@Repository
class TaskPagingRepository(
    override val r2dbcTemplate: R2dbcEntityTemplate
) : PagingRepository<Task, Long> {

    override val klass = Task::class
}
