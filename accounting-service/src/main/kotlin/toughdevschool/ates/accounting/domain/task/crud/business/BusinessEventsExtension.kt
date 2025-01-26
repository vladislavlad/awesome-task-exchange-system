package toughdevschool.ates.accounting.domain.task.crud.business

import org.springframework.stereotype.Component
import software.darkmatter.platform.service.CrudServiceExtension
import toughdevschool.ates.accounting.domain.task.crud.data.Task
import toughdevschool.ates.accounting.event.producer.AccountingBusinessEventProducer
import toughdevschool.ates.event.business.task.v1.TaskCostAssigned

@Component
class BusinessEventsExtension(
    private val businessEventProducer: AccountingBusinessEventProducer,
) : CrudServiceExtension<Long, Task> {

    override suspend fun onGet(business: Task) = Unit

    override suspend fun onList(list: List<Task>) = Unit

    override suspend fun onCreate(business: Task) =
        businessEventProducer.sendTaskCostAssignedV1(
            TaskCostAssigned(
                taskUuid = business.uuid,
                assignCost = business.assignCost,
                reward = business.reward
            )
        )

    override suspend fun onUpdate(business: Task) = Unit

    override suspend fun onDelete(business: Task) = Unit
}
