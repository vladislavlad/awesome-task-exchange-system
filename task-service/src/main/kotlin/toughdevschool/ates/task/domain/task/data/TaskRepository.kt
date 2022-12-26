package toughdevschool.ates.task.domain.task.data

import kotlinx.coroutines.flow.Flow
import software.darkmatter.platform.data.CoroutineCrudSortingRepository
import java.util.UUID

interface TaskRepository : CoroutineCrudSortingRepository<Task, Long> {

    suspend fun findByUuid(uuid: UUID): Task?

    fun findAllByStatus(status: Task.Status): Flow<Task>
}
