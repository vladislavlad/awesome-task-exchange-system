package toughdevschool.ates.accounting.domain.task.complete

import arrow.core.continuations.either
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import software.darkmatter.platform.error.BusinessError
import toughdevschool.ates.accounting.domain.account.CompanyAccountUuid
import toughdevschool.ates.accounting.domain.account.business.AccountService
import toughdevschool.ates.accounting.domain.task.crud.business.TaskService
import toughdevschool.ates.accounting.domain.transaction.transition.TransitionRequest
import toughdevschool.ates.accounting.domain.transaction.transition.TransitionService
import toughdevschool.ates.accounting.domain.user.business.UserService
import toughdevschool.ates.event.business.task.v1.TaskCompleted

@Component
class TaskCompletedHandler(
    private val taskService: TaskService,
    private val userService: UserService,
    private val accountService: AccountService,
    private val transitionService: TransitionService,
) {

    @Transactional
    suspend fun handle(taskAssigned: TaskCompleted) = either<BusinessError, Unit> {
        val user = userService.getByUuid(taskAssigned.userUuid).bind()
        val userAccount = accountService.getByUser(user).bind()
        val companyAccount = accountService.getByUuid(CompanyAccountUuid).bind()
        val task = taskService.getByUuid(taskAssigned.taskUuid).bind()

        transitionService.perform(
            TransitionRequest(
                source = companyAccount,
                destination = userAccount,
                amount = task.reward.toBigDecimal(),
                description = "Task completed '${task.title}' uuid: '${task.uuid}'"
            )
        )
    }
}
