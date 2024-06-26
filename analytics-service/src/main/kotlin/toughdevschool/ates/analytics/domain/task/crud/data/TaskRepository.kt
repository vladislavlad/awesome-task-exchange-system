package toughdevschool.ates.analytics.domain.task.crud.data

import software.darkmatter.platform.data.CoroutineCrudSortingRepository
import java.util.UUID

interface TaskRepository : CoroutineCrudSortingRepository<Task, Long> {

    suspend fun findByUuid(uuid: UUID): Task?

    suspend fun findFirstByOrderByRewardDesc(): Task?
}
