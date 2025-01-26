package toughdevschool.ates.analytics.domain.task.crud.data.withCost

import reactor.core.publisher.Mono
import toughdevschool.ates.analytics.domain.task.crud.data.Task
import java.time.OffsetDateTime

interface TaskWithCostRepository {

    fun findFirstByStatusAndCompletedAtInPeriodAndOrderByRewardDesc(
        status: Task.Status,
        startOfPeriod: OffsetDateTime,
        endOfPeriod: OffsetDateTime
    ): Mono<TaskWithCosts>
}
