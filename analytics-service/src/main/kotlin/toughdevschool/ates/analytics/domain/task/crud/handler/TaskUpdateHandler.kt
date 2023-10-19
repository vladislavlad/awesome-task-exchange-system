package toughdevschool.ates.analytics.domain.task.crud.handler

import arrow.core.continuations.either
import org.springframework.stereotype.Component
import toughdevschool.ates.analytics.domain.task.crud.business.TaskService
import toughdevschool.ates.analytics.domain.task.crud.business.TaskUpdate
import toughdevschool.ates.event.cud.task.v2.TaskData

@Component
class TaskUpdateHandler(
    private val taskService: TaskService,
) {

    suspend fun handle(data: TaskData) =
        either {
            val task = taskService.getByUuid(data.uuid).bind()

            taskService.update(
                TaskUpdate(
                    task = task,
                    title = data.title,
                )
            ).bind()
            Unit
        }
}