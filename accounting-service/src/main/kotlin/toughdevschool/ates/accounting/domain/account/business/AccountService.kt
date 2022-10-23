package toughdevschool.ates.accounting.domain.account.business

import arrow.core.Either
import software.darkmatter.platform.error.BusinessError
import software.darkmatter.platform.service.CrudService
import toughdevschool.ates.accounting.domain.account.data.Account
import toughdevschool.ates.accounting.domain.user.data.User
import java.util.UUID

interface AccountService : CrudService<Account, Long, AccountCreate, AccountUpdate> {

    suspend fun getByUuid(uuid: UUID): Either<BusinessError, Account>

    suspend fun getByUser(user: User): Either<BusinessError, Account>
}
