package toughdevschool.ates.task.domain.task.crud.business

import org.springframework.stereotype.Component
import software.darkmatter.event.cud.CudEvent.Type
import software.darkmatter.platform.service.CrudServiceExtension
import toughdevschool.ates.task.domain.task.data.Task
import toughdevschool.ates.task.event.producer.CudEventProducer
import toughdevschool.ates.task.event.producer.cud.TaskCudEvent
import toughdevschool.ates.task.event.producer.cud.TaskCudEvent.TaskData
import java.util.UUID

@Component
class CudEventsExtension(
    private val cudEventProducer: CudEventProducer
) : CrudServiceExtension<Long, Task> {

    override suspend fun onGet(business: Task) = Unit

    override suspend fun onList(list: List<Task>) = Unit

    override suspend fun onCreate(business: Task) =
        cudEventProducer.sendEvent(
            TaskCudEvent(
                id = UUID.randomUUID(),
                data = TaskData(
                    uuid = business.uuid,
                    title = business.title,
                    description = business.description,
                    jiraId = business.jiraId,
                    status = business.status,
                    userId = business.userId,
                ),
                type = Type.Create,
            )
        )

    override suspend fun onUpdate(business: Task) =
        cudEventProducer.sendEvent(
            TaskCudEvent(
                id = UUID.randomUUID(),
                data = TaskData(
                    uuid = business.uuid,
                    title = business.title,
                    description = business.description,
                    jiraId = business.jiraId,
                    status = business.status,
                    userId = business.userId,
                ),
                type = Type.Update,
            )
        )

    override suspend fun onDelete(id: Long) = TODO()
}
