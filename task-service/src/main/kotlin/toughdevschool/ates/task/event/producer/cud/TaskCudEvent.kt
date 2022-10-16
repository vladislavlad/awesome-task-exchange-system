package toughdevschool.ates.task.event.producer.cud

import software.darkmatter.event.cud.CudEvent
import toughdevschool.ates.task.domain.task.data.Task
import java.util.UUID

private const val TASK_ENTITY = "Task"

data class TaskCudEvent(
    override val id: UUID,
    override val type: CudEvent.Type,
    override val data: TaskData,
) : TaskStreamEvent<TaskCudEvent.TaskData, UUID>() {

    override val key = data.uuid

    override val entity = TASK_ENTITY

    data class TaskData(
        val uuid: UUID,
        var title: String,
        var description: String,
        var status: Task.Status,
        var userId: Long,
    )
}
