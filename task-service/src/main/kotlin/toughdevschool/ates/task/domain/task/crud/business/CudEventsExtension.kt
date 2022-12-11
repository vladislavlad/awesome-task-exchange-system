package toughdevschool.ates.task.domain.task.crud.business

import org.springframework.stereotype.Component
import software.darkmatter.platform.event.cud.Operation
import software.darkmatter.platform.service.CrudServiceExtension
import toughdevschool.ates.event.business.task.v1.TaskAssigned
import toughdevschool.ates.event.cud.task.v2.TaskData
import toughdevschool.ates.task.domain.task.data.Task
import toughdevschool.ates.task.event.producer.TaskBusinessEventProducer
import toughdevschool.ates.task.event.producer.TaskCudEventProducer

@Component
class CudEventsExtension(
    private val cudEventProducer: TaskCudEventProducer,
    private val businessEventProducer: TaskBusinessEventProducer,
) : CrudServiceExtension<Long, Task> {

    override suspend fun onGet(business: Task) = Unit

    override suspend fun onList(list: List<Task>) = Unit


    override suspend fun onCreate(business: Task) {
        cudEventProducer.sendTaskV2(
            Operation.Create,
            TaskData(
                uuid = business.uuid,
                title = business.title,
                description = business.description,
                status = business.status.name,
                jiraId = business.jiraId,
                userUuid = business.userUuid,
            )
        )
        businessEventProducer.sendTaskAssignedV1(
            TaskAssigned(
                taskUuid = business.uuid,
                userUuid = business.userUuid,
            )
        )
    }

    override suspend fun onUpdate(business: Task) =
        cudEventProducer.sendTaskV2(
            Operation.Update,
            TaskData(
                uuid = business.uuid,
                title = business.title,
                description = business.description,
                status = business.status.name,
                jiraId = business.jiraId,
                userUuid = business.userUuid,
            )
        )

    override suspend fun onDelete(id: Long) = TODO()
}
