package toughdevschool.ates.accounting.domain.task.assign

import arrow.core.continuations.either
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import software.darkmatter.platform.error.BusinessError
import toughdevschool.ates.accounting.domain.account.CompanyAccountUuid
import toughdevschool.ates.accounting.domain.account.business.AccountService
import toughdevschool.ates.accounting.domain.task.crud.business.TaskService
import toughdevschool.ates.accounting.domain.transaction.transfer.TransferRequest
import toughdevschool.ates.accounting.domain.transaction.transfer.TransferService
import toughdevschool.ates.accounting.domain.user.business.UserService
import toughdevschool.ates.event.business.task.v1.TaskAssigned

@Component
class TaskAssignedHandler(
    private val taskService: TaskService,
    private val userService: UserService,
    private val accountService: AccountService,
    private val transferService: TransferService,
) {

    @Transactional
    suspend fun handle(taskAssigned: TaskAssigned) = either<BusinessError, Unit> {
        val user = userService.getByUuid(taskAssigned.userUuid).bind()
        val userAccount = accountService.getByUser(user).bind()
        val companyAccount = accountService.getByUuid(CompanyAccountUuid).bind()
        val task = taskService.getByUuid(taskAssigned.taskUuid).bind()

        transferService.perform(
            TransferRequest(
                source = userAccount,
                destination = companyAccount,
                amount = task.assignCost.toBigDecimal(),
                description = "Assigning task '${task.title}' uuid: '${task.uuid}'"
            )
        )
    }
}
