package toughdevschool.ates.task.domain.task.reassign.business

import arrow.core.Either
import arrow.core.continuations.either
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import software.darkmatter.platform.error.BusinessError
import toughdevschool.ates.task.domain.task.crud.business.TaskService
import toughdevschool.ates.task.domain.task.crud.business.TaskUpdate
import toughdevschool.ates.task.domain.task.data.Task
import toughdevschool.ates.task.domain.user.business.UserService
import toughdevschool.ates.task.domain.user.data.User
import toughdevschool.ates.task.domain.userRole.business.RoleNames
import kotlin.random.Random

@Service
class Service(
    private val taskService: TaskService,
    private val userService: UserService,
) : TaskReassignService {

    private val rolesForTaskAssign = listOf(RoleNames.WORKER)

    @Transactional
    override suspend fun perform(request: Unit): Either<BusinessError, TasksReassigned> = either {
        val userList = userService.getFlowWithRoleIn(rolesForTaskAssign).bind()
            .buffer(100)
            .toList()

        if (userList.isNotEmpty()) {
            val taskFlow = taskService.getFlowWithStatus(Task.Status.New).bind()

            val taskListSize = taskFlow.buffer(100)
                .map { TaskUpdate(task = it, user = userList.takeRandomUser()) }
                .let { taskService.updateBatch(it.toList()).bind().size }

            return@either TasksReassigned(taskListSize)
        }
        TasksReassigned(0)
    }

    private fun List<User>.takeRandomUser(): User =
        this[Random.nextInt(0, this.size)]
}
