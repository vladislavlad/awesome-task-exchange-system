package toughdevschool.ates.analytics.domain.task.crud.data

import org.springframework.data.r2dbc.repository.Query
import software.darkmatter.platform.data.CoroutineCrudSortingRepository
import java.time.OffsetDateTime
import java.util.UUID

interface TaskRepository : CoroutineCrudSortingRepository<Task, Long> {

    suspend fun findByUuid(uuid: UUID): Task?


    @Query(
        """
            select * from tasks
            where status = :status
            and completed_at is not null
            and completed_at >= :start 
            and completed_at < :end
            order by reward desc
            limit 1
        """
    )
    suspend fun findFirstByStatusInPeriodAndOrderByRewardDesc(status: Task.Status, start: OffsetDateTime, end: OffsetDateTime): Task?
}
