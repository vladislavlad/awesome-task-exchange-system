package toughdevschool.ates.analytics.domain.analytics.tasks

import arrow.core.Either
import org.springframework.stereotype.Component
import software.darkmatter.platform.error.BusinessError
import software.darkmatter.platform.syntax.UnitRight
import toughdevschool.ates.analytics.domain.task.crud.business.TaskService

@Component
class Service(
    private val taskService: TaskService,
) : TaskAnalyticsService {

    override suspend fun perform(request: Unit): Either<BusinessError, Unit> {
        // TODO find most expensive task
        return UnitRight
    }
}
