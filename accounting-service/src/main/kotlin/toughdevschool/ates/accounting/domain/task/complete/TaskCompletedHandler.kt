package toughdevschool.ates.accounting.domain.task.complete

import arrow.core.Either
import org.springframework.stereotype.Component
import software.darkmatter.platform.error.BusinessError
import toughdevschool.ates.event.business.task.v1.TaskCompleted

@Component
class TaskCompletedHandler {

    suspend fun handle(taskAssigned: TaskCompleted): Either<BusinessError, Unit> = TODO()
}
