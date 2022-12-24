package toughdevschool.ates.accounting.domain.user.business

import org.springframework.stereotype.Component
import software.darkmatter.platform.service.CrudServiceExtension
import toughdevschool.ates.accounting.domain.account.business.AccountCreate
import toughdevschool.ates.accounting.domain.account.business.AccountService
import toughdevschool.ates.accounting.domain.account.data.Account
import toughdevschool.ates.accounting.domain.user.data.User

@Component
class AccountExtension(
    private val accountService: AccountService,
) : CrudServiceExtension<Long, User> {

    override suspend fun onCreate(business: User) {
        accountService.create(AccountCreate(business, Account.Type.User))
    }

    override suspend fun onUpdate(business: User) = Unit

    override suspend fun onDelete(business: User) = Unit

    override suspend fun onGet(business: User) = Unit

    override suspend fun onList(list: List<User>) = Unit
}
