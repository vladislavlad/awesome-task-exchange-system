package toughdevschool.ates.task.event.producer

import software.darkmatter.platform.event.cud.Operation
import toughdevschool.ates.event.cud.task.v2.TaskData

interface TaskCudEventProducer {

    suspend fun sendTaskV2(operation: Operation, data: TaskData)
}
