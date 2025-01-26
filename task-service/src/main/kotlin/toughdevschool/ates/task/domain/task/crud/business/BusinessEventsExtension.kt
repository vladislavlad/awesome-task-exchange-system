package toughdevschool.ates.task.domain.task.crud.business

import org.springframework.stereotype.Component
import software.darkmatter.platform.service.CrudServiceExtension
import toughdevschool.ates.event.business.task.v1.TaskAssigned
import toughdevschool.ates.task.domain.task.data.Task
import toughdevschool.ates.task.event.producer.TaskBusinessEventProducer

@Component
class BusinessEventsExtension(
    private val businessEventProducer: TaskBusinessEventProducer,
) : CrudServiceExtension<Long, Task> {

    override suspend fun onGet(business: Task) = Unit

    override suspend fun onList(list: List<Task>) = Unit

    override suspend fun onCreate(business: Task) =
        businessEventProducer.sendTaskAssignedV1(
            TaskAssigned(
                taskUuid = business.uuid,
                userUuid = business.userUuid,
            )
        )

    override suspend fun onUpdate(business: Task) =
        businessEventProducer.sendTaskAssignedV1(
            TaskAssigned(
                taskUuid = business.uuid,
                userUuid = business.userUuid,
            )
        )

    override suspend fun onDelete(business: Task) = Unit
}
