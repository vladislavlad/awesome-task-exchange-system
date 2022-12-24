package toughdevschool.ates.accounting.domain.task.crud.handler

import arrow.core.continuations.either
import org.springframework.stereotype.Component
import software.darkmatter.platform.error.BusinessError
import toughdevschool.ates.accounting.domain.task.crud.business.TaskService
import toughdevschool.ates.accounting.domain.task.crud.business.TaskUpdate
import toughdevschool.ates.event.cud.task.v2.TaskData

@Component
class TaskUpdateHandler(
    private val taskService: TaskService,
) {

    suspend fun handle(data: TaskData) =
        either<BusinessError, Unit> {
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
