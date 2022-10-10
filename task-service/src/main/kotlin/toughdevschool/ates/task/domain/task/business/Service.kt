package toughdevschool.ates.task.domain.task.business

import org.springframework.stereotype.Service
import software.darkmatter.platform.business.BusinessCheck
import software.darkmatter.platform.data.PagingRepository
import software.darkmatter.platform.service.AbstractCrudService
import software.darkmatter.platform.syntax.leftIfNull
import toughdevschool.ates.task.domain.task.data.Task
import toughdevschool.ates.task.domain.task.data.TaskRepository
import java.util.UUID

@Service
class Service(
    private val repository: TaskRepository,
    pagingRepository: PagingRepository<Task, Long>,
    cudEventsExtension: CudEventsExtension,
) : AbstractCrudService<Task, Long, TaskCreate, TaskUpdate>(repository, pagingRepository, cudEventsExtension),
    TaskService {

    override suspend fun getByUuid(uuid: UUID) =
        repository.findByUuid(uuid).leftIfNull { notFound }

    override suspend fun createEntity(businessCreate: TaskCreate) =
        Task(
            uuid = UUID.randomUUID(),
            title = businessCreate.title,
            description = businessCreate.description,
            status = Task.Status.New,
            userId = businessCreate.userId,
        )

    override suspend fun updateEntity(businessUpdate: TaskUpdate): Task =
        with(businessUpdate.task) {
            title = businessUpdate.title
            description = businessUpdate.description
            status = businessUpdate.status
            businessUpdate.userId?.also { userId = it }
            this
        }

    override val checksOnCreate = emptyList<BusinessCheck<TaskCreate>>()

    override val checksOnUpdate = emptyList<BusinessCheck<TaskUpdate>>()

    override fun entityClass() = Task::class
}
