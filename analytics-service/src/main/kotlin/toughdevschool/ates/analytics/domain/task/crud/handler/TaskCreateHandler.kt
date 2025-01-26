package toughdevschool.ates.analytics.domain.task.crud.handler

import arrow.core.raise.either
import org.springframework.stereotype.Component
import toughdevschool.ates.analytics.domain.task.crud.business.TaskCreate
import toughdevschool.ates.analytics.domain.task.crud.business.TaskService
import toughdevschool.ates.analytics.domain.task.crud.data.Task
import toughdevschool.ates.analytics.domain.user.business.UserService
import toughdevschool.ates.event.cud.task.v3.TaskData

@Component
class TaskCreateHandler(
    private val taskService: TaskService,
    private val userService: UserService,
) {

    suspend fun handle(data: TaskData) =
        either {
            val user = userService.getByUuid(data.userUuid).bind()
            taskService.create(
                TaskCreate(
                    uuid = data.uuid,
                    user = user,
                    title = data.title,
                    status = Task.Status.valueOf(data.status),
                    createdAt = data.createdAt,
                    completedAt = data.completedAt,
                )
            ).bind()
            Unit
        }
}
