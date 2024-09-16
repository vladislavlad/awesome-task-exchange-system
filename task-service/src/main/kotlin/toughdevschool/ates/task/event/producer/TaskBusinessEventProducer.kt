package toughdevschool.ates.task.event.producer

import toughdevschool.ates.event.business.task.v1.TaskAssigned
import toughdevschool.ates.event.business.task.v1.TaskCompleted

interface TaskBusinessEventProducer {

    suspend fun sendTaskAssignedV1(taskAssigned: TaskAssigned)

    suspend fun sendTaskCompletedV1(taskCompleted: TaskCompleted)
}
