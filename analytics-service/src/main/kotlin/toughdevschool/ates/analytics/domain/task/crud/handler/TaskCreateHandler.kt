package toughdevschool.ates.analytics.domain.task.crud.handler

import org.springframework.stereotype.Component
import toughdevschool.ates.analytics.domain.task.crud.business.TaskCreate
import toughdevschool.ates.analytics.domain.task.crud.business.TaskService
import toughdevschool.ates.event.cud.task.v2.TaskData

@Component
class TaskCreateHandler(
    private val taskService: TaskService,
) {

    suspend fun handle(data: TaskData) =
        taskService.create(
            TaskCreate(
                uuid = data.uuid,
                title = data.title,
            )
        ).map { }
}
