package toughdevschool.ates.accounting.domain.task.assign

import arrow.core.continuations.either
import io.micrometer.observation.ObservationRegistry
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import software.darkmatter.platform.error.BusinessError
import software.darkmatter.platform.observation.Observable
import toughdevschool.ates.accounting.domain.account.CompanyAccountUuid
import toughdevschool.ates.accounting.domain.account.business.AccountService
import toughdevschool.ates.accounting.domain.task.crud.business.TaskService
import toughdevschool.ates.accounting.domain.transaction.data.Transaction
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
    override val observationRegistry: ObservationRegistry,
) : Observable {

    @Transactional
    suspend fun handle(taskAssigned: TaskAssigned) = either<BusinessError, Unit> {
        observed("task-assigned-handler") {
            val user = userService.getByUuid(taskAssigned.userUuid).bind()
            val userAccount = accountService.getByUser(user).bind()
            val companyAccount = accountService.getByUuid(CompanyAccountUuid).bind()
            val task = taskService.getByUuid(taskAssigned.taskUuid).bind()

            transferService.perform(
                TransferRequest(
                    type = Transaction.Type.Transfer,
                    source = userAccount,
                    destination = companyAccount,
                    amount = task.assignCost.toBigDecimal(),
                    description = "Assigning task '${task.title}' uuid: '${task.uuid}'"
                )
            ).bind()
        }
    }
}
