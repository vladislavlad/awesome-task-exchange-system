package toughdevschool.ates.task.domain.task.crud.business

import arrow.core.right
import org.springframework.stereotype.Service
import software.darkmatter.platform.business.BusinessCheck
import software.darkmatter.platform.data.PagingRepository
import software.darkmatter.platform.security.service.AuthCrudService
import software.darkmatter.platform.syntax.leftIfNull
import toughdevschool.ates.task.domain.task.data.Task
import toughdevschool.ates.task.domain.task.data.TaskRepository
import java.util.UUID

@Service
class Service(
    private val repository: TaskRepository,
    pagingRepository: PagingRepository<Task, Long>,
    cudEventsExtension: CudEventsExtension,
) : AuthCrudService<Task, Long, TaskCreate, TaskUpdate>(repository, pagingRepository, cudEventsExtension),
    TaskService {

    override suspend fun getByUuid(uuid: UUID) = repository.findByUuid(uuid).leftIfNull { notFound }

    override suspend fun getFlowWithStatus(status: Task.Status) = repository.findAllByStatus(status).right()

    override suspend fun createEntity(businessCreate: TaskCreate) =
        Task(
            uuid = UUID.randomUUID(),
            title = businessCreate.title,
            jiraId = businessCreate.jiraId,
            description = businessCreate.description,
            status = Task.Status.New,
            userUuid = businessCreate.user.uuid,
        )

    override suspend fun updateEntity(businessUpdate: TaskUpdate): Task =
        with(businessUpdate.task) {
            businessUpdate.title?.also { title = it }
            businessUpdate.jiraId?.also { jiraId = it }
            businessUpdate.description?.also { description = it }
            businessUpdate.status?.also { status = it }
            businessUpdate.user?.also { userUuid = it.uuid }
            this
        }

    override val checksOnCreate = emptyList<BusinessCheck<TaskCreate>>()

    override val checksOnUpdate = emptyList<BusinessCheck<TaskUpdate>>()

    override fun entityClass() = Task::class
}
