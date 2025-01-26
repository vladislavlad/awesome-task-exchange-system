package toughdevschool.ates.analytics.domain.task.crud.business

import arrow.core.Either
import arrow.core.right
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.stereotype.Service
import software.darkmatter.platform.business.BusinessCheck
import software.darkmatter.platform.data.PagingRepository
import software.darkmatter.platform.error.BusinessError
import software.darkmatter.platform.security.service.AuthCrudService
import software.darkmatter.platform.syntax.leftIfNull
import toughdevschool.ates.analytics.domain.task.crud.data.Task
import toughdevschool.ates.analytics.domain.task.crud.data.TaskRepository
import toughdevschool.ates.analytics.domain.task.crud.data.withCost.TaskWithCostRepository
import toughdevschool.ates.analytics.domain.task.crud.data.withCost.TaskWithCosts
import java.time.OffsetDateTime
import java.time.temporal.ChronoField
import java.time.temporal.ChronoUnit
import java.util.UUID

@Service
class Service(
    private val taskWithCostRepository: TaskWithCostRepository,
    private val repository: TaskRepository,
    pagingRepository: PagingRepository<Task, Long>,
) : AuthCrudService<Task, Long, TaskCreate, TaskUpdate>(repository, pagingRepository),
    TaskService {

    override suspend fun getByUuid(uuid: UUID) = repository.findByUuid(uuid).leftIfNull { notFound }

    override suspend fun getMostExpensiveTask(scale: Scale): Either<BusinessError, TaskWithCosts?> {
        val startOfDay = OffsetDateTime.now().truncatedTo(ChronoUnit.DAYS)
        val (startOfPeriod, endOfPeriod) = when (scale) {
            Scale.Day -> startOfDay to startOfDay.plusDays(1)
            Scale.Week -> {
                val startOfWeek = startOfDay.with(ChronoField.DAY_OF_WEEK, 1)
                val endOfWeek = startOfWeek.plusWeeks(1)
                startOfWeek to endOfWeek
            }
            Scale.Month -> {
                val startOfMonth = startOfDay.withDayOfMonth(1)
                val endOfMonth = startOfMonth.plusMonths(1)
                startOfMonth to endOfMonth
            }
        }

        return taskWithCostRepository.findFirstByStatusAndCompletedAtInPeriodAndOrderByRewardDesc(
            Task.Status.Completed,
            startOfPeriod,
            endOfPeriod,
        ).awaitFirst().right()
    }

    override suspend fun createEntity(businessCreate: TaskCreate) =
        Task(
            uuid = businessCreate.uuid,
            userUuid = businessCreate.user?.uuid,
            title = businessCreate.title,
            status = businessCreate.status,
            createdAt = businessCreate.createdAt,
            completedAt = businessCreate.completedAt,
        )

    override suspend fun updateEntity(businessUpdate: TaskUpdate): Task =
        with(businessUpdate.task) {
            businessUpdate.user?.also { userUuid = it.uuid }
            businessUpdate.title?.also { title = it }
            businessUpdate.status.also { status = it }
            businessUpdate.completedAt?.also { completedAt = it }
            this
        }

    override val checksOnCreate = emptyList<BusinessCheck<TaskCreate>>()

    override val checksOnUpdate = emptyList<BusinessCheck<TaskUpdate>>()

    override fun entityClass() = Task::class
}
