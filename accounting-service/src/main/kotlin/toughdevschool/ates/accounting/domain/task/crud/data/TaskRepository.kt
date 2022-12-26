package toughdevschool.ates.accounting.domain.task.crud.data

import software.darkmatter.platform.data.CoroutineCrudSortingRepository
import java.util.UUID

interface TaskRepository : CoroutineCrudSortingRepository<Task, Long> {

    suspend fun findByUuid(uuid: UUID): Task?
}
