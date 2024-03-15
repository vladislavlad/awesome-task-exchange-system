package toughdevschool.ates.task.domain.task.complete.business

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.left
import org.springframework.stereotype.Service
import software.darkmatter.platform.error.BusinessError
import software.darkmatter.platform.error.ErrorType
import software.darkmatter.platform.syntax.UnitRight
import toughdevschool.ates.event.business.task.v1.TaskCompleted
import toughdevschool.ates.task.domain.task.crud.business.TaskService
import toughdevschool.ates.task.domain.task.crud.business.TaskUpdate
import toughdevschool.ates.task.domain.task.data.Task
import toughdevschool.ates.task.domain.user.data.User
import toughdevschool.ates.task.event.producer.TaskBusinessEventProducer

@Service
class Service(
    private val taskService: TaskService,
    private val businessEventProducer: TaskBusinessEventProducer,
) : TaskCompleteService {

    override suspend fun perform(request: TaskComplete) = either<BusinessError, Unit> {
        checkStatusTransition(request.task).bind()
        checkUserAssignment(request.task, request.user).bind()
        taskService.update(
            TaskUpdate(
                task = request.task,
                status = Task.Status.Completed
            )
        ).bind()
        businessEventProducer.sendTaskCompletedV1(
            TaskCompleted(
                taskUuid = request.task.uuid,
                userUuid = request.user.uuid,
            )
        )
    }

    private fun checkUserAssignment(task: Task, user: User): Either<BusinessError, Unit> =
        if (task.userUuid != user.uuid)
            BusinessError.FlowConflict(
                "Task assigned to another user",
                ErrorType.BusinessRequirement
            ).left()
        else UnitRight

    private fun checkStatusTransition(task: Task): Either<BusinessError, Unit> =
        if (task.status == Task.Status.Completed)
            BusinessError.FlowConflict(
                "Task is already in ${Task.Status.Completed} status",
                ErrorType.BusinessRequirement
            ).left()
        else UnitRight
}
