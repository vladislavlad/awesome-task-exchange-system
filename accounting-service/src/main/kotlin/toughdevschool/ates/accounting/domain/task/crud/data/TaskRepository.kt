package toughdevschool.ates.accounting.domain.task.crud.data

import org.springframework.data.repository.kotlin.CoroutineSortingRepository
import java.util.UUID

interface TaskRepository : CoroutineSortingRepository<Task, Long> {

    suspend fun findByUuid(uuid: UUID): Task?
}
