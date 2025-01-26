package toughdevschool.ates.task.domain.task.crud.business

import arrow.core.right
import org.springframework.stereotype.Service
import software.darkmatter.platform.business.BusinessCheck
import software.darkmatter.platform.business.businessChecks
import software.darkmatter.platform.business.onTrue
import software.darkmatter.platform.data.PagingRepository
import software.darkmatter.platform.error.BusinessError
import software.darkmatter.platform.error.ErrorType
import software.darkmatter.platform.security.service.AuthCrudService
import software.darkmatter.platform.service.CrudServiceExtension.Companion.combine
import software.darkmatter.platform.syntax.leftIfNull
import toughdevschool.ates.task.domain.task.data.Task
import toughdevschool.ates.task.domain.task.data.TaskRepository
import java.time.OffsetDateTime
import java.util.UUID

@Service
class Service(
    private val repository: TaskRepository,
    pagingRepository: PagingRepository<Task, Long>,
    cudEventsExtension: CudEventsExtension,
    businessEventsExtension: BusinessEventsExtension,
) : AuthCrudService<Task, Long, TaskCreate, TaskUpdate>(repository, pagingRepository, combine(cudEventsExtension, businessEventsExtension)),
    TaskService {

    override suspend fun getByUuid(uuid: UUID) = repository.findByUuid(uuid).leftIfNull { notFound }

    override suspend fun getFlowWithStatus(status: Task.Status) = repository.findAllByStatus(status).right()

    override suspend fun createEntity(businessCreate: TaskCreate): Task {
        val createdAt = OffsetDateTime.now()
        return Task(
            uuid = UUID.randomUUID(),
            userUuid = businessCreate.user.uuid,
            title = businessCreate.title,
            jiraId = businessCreate.jiraId,
            description = businessCreate.description,
            status = Task.Status.New,
            completedAt = null,
            createdAt = createdAt,
            createdBy = businessCreate.createdBy,
            updatedAt = createdAt,
            updatedBy = businessCreate.createdBy,
        )
    }

    override suspend fun updateEntity(businessUpdate: TaskUpdate): Task =
        with(businessUpdate.task) {
            businessUpdate.user?.also { userUuid = it.uuid }
            businessUpdate.title?.also { title = it }
            businessUpdate.jiraId?.also { jiraId = it }
            businessUpdate.description?.also { description = it }
            businessUpdate.status?.also { status = it }
            businessUpdate.completedAt?.also { completedAt = it }

            updatedBy = businessUpdate.updatedBy
            this
        }

    override val checksOnCreate = emptyList<BusinessCheck<TaskCreate>>()

    override val checksOnUpdate = businessChecks<TaskUpdate>(
        onTrue(
            { it.task.completedAt != null },
            { BusinessError.FlowConflict("Completed task cannot be updated", ErrorType.BusinessRequirement) })
    )

    override fun entityClass() = Task::class
}
