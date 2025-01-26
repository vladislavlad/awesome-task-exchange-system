package toughdevschool.ates.task.domain.task.crud.business

import org.springframework.stereotype.Component
import software.darkmatter.platform.event.cud.Operation
import software.darkmatter.platform.service.CrudServiceExtension
import toughdevschool.ates.event.cud.task.v3.TaskData
import toughdevschool.ates.task.domain.task.data.Task
import toughdevschool.ates.task.event.producer.TaskCudEventProducer

@Component
class CudEventsExtension(
    private val cudEventProducer: TaskCudEventProducer,
) : CrudServiceExtension<Long, Task> {

    override suspend fun onGet(business: Task) = Unit

    override suspend fun onList(list: List<Task>) = Unit

    override suspend fun onCreate(business: Task) {
        cudEventProducer.sendTaskV3(
            Operation.Create,
            TaskData(
                uuid = business.uuid,
                userUuid = business.userUuid,
                title = business.title,
                description = business.description,
                status = business.status.name,
                jiraId = business.jiraId,
                createdAt = business.createdAt,
                completedAt = business.completedAt,
            )
        )
    }

    override suspend fun onUpdate(business: Task) =
        cudEventProducer.sendTaskV3(
            Operation.Update,
            TaskData(
                uuid = business.uuid,
                userUuid = business.userUuid,
                title = business.title,
                description = business.description,
                status = business.status.name,
                jiraId = business.jiraId,
                createdAt = business.createdAt,
                completedAt = business.completedAt,
            )
        )

    override suspend fun onDelete(business: Task) =
        cudEventProducer.sendTaskV3(
            Operation.Delete,
            TaskData(
                uuid = business.uuid,
                userUuid = business.userUuid,
                title = business.title,
                description = business.description,
                status = business.status.name,
                jiraId = business.jiraId,
                createdAt = business.createdAt,
                completedAt = business.completedAt,
            )
        )
}
