package toughdevschool.ates.accounting.domain.task.crud.business

import org.springframework.stereotype.Service
import software.darkmatter.platform.business.BusinessCheck
import software.darkmatter.platform.data.PagingRepository
import software.darkmatter.platform.security.service.AuthCrudService
import software.darkmatter.platform.syntax.leftIfNull
import toughdevschool.ates.accounting.domain.task.crud.data.Task
import toughdevschool.ates.accounting.domain.task.crud.data.TaskRepository
import java.util.UUID
import kotlin.random.Random

@Service
class Service(
    private val repository: TaskRepository,
    pagingRepository: PagingRepository<Task, Long>,
    businessEventsExtension: BusinessEventsExtension,
) : AuthCrudService<Task, Long, TaskCreate, TaskUpdate>(repository, pagingRepository, businessEventsExtension),
    TaskService {

    override suspend fun getByUuid(uuid: UUID) = repository.findByUuid(uuid).leftIfNull { notFound }

    override suspend fun createEntity(businessCreate: TaskCreate) =
        Task(
            uuid = businessCreate.uuid,
            title = businessCreate.title,
            assignCost = calculateAssignCost(),
            reward = calculateReward(),
            userUuid = businessCreate.user?.uuid,
        )

    private fun calculateAssignCost(): Int = Random.nextInt(10, 21)

    private fun calculateReward(): Int = Random.nextInt(20, 41)

    override suspend fun updateEntity(businessUpdate: TaskUpdate): Task =
        with(businessUpdate.task) {
            businessUpdate.title?.also { title = it }
            businessUpdate.user?.also { userUuid = it.uuid }
            this
        }

    override val checksOnCreate = emptyList<BusinessCheck<TaskCreate>>()

    override val checksOnUpdate = emptyList<BusinessCheck<TaskUpdate>>()

    override fun entityClass() = Task::class
}
