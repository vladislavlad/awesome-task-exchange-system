package toughdevschool.ates.analytics.domain.task.crud.data

import io.r2dbc.spi.Row
import io.r2dbc.spi.RowMetadata
import org.springframework.data.r2dbc.core.R2dbcEntityOperations
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import software.darkmatter.platform.data.PagingRepository
import toughdevschool.ates.analytics.domain.task.crud.data.withCost.TaskWithCostRepository
import toughdevschool.ates.analytics.domain.task.crud.data.withCost.TaskWithCosts
import java.time.OffsetDateTime
import java.util.UUID
import java.util.function.BiFunction

@Repository
class TaskPagingRepository(
    override val r2dbcOperations: R2dbcEntityOperations
) : PagingRepository<Task, Long>, TaskWithCostRepository {

    override fun findFirstByStatusAndCompletedAtInPeriodAndOrderByRewardDesc(
        status: Task.Status,
        startOfPeriod: OffsetDateTime,
        endOfPeriod: OffsetDateTime
    ): Mono<TaskWithCosts> =
        r2dbcOperations.databaseClient.sql(
            """
            select t.uuid taskUuid,
                t.user_uuid userUuid,
                t.title title,
                t.status status,
                tr.assign_cost assignCost,
                tr.reward reward,
                t.created_at createdAt,
                t.completed_at completedAt
            from tasks t
                join task_costs tr on t.uuid = tr.task_uuid
            where t.status = :status
                and t.completed_at is not null
                and t.completed_at >= :start
                and t.completed_at < :end
            order by tr.reward desc
            limit 1
            """
        )
            .bind("status", Task.Status.Completed.toString())
            .bind("start", startOfPeriod)
            .bind("end", endOfPeriod)
            .map(taskWithCostsMapper)
            .first()

    private val taskWithCostsMapper = BiFunction<Row, RowMetadata, TaskWithCosts> { row, _ ->
        TaskWithCosts(
            taskUuid = row.get("taskUuid", UUID::class.java)!!,
            userUuid = row.get("userUuid", UUID::class.java),
            title = row.get("title", String::class.java)!!,
            status = Task.Status.valueOf(row.get("status", String::class.java)!!),
            assignCost = row.get("assignCost", Int::class.java)!!,
            reward = row.get("reward", Int::class.java)!!,
            createdAt = row.get("createdAt", OffsetDateTime::class.java)!!,
            completedAt = row.get("completedAt", OffsetDateTime::class.java),
        )
    }

    override val klass = Task::class
}
