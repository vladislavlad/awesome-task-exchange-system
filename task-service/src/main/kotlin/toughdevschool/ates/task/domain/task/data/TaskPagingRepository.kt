package toughdevschool.ates.task.domain.task.data

import org.springframework.data.r2dbc.core.R2dbcEntityOperations
import org.springframework.stereotype.Repository
import software.darkmatter.platform.data.PagingRepository

@Repository

class TaskPagingRepository(
    override val r2dbcOperations: R2dbcEntityOperations
) : PagingRepository<Task, Long> {

    override val klass = Task::class
}
