package toughdevschool.ates.task.event.producer

import software.darkmatter.platform.event.cud.Operation
import toughdevschool.ates.event.cud.task.v3.TaskData

interface TaskCudEventProducer {

    suspend fun sendTaskV3(operation: Operation, data: TaskData)
}
