package toughdevschool.ates.accounting.domain.account.business

import toughdevschool.ates.accounting.domain.account.data.Account
import toughdevschool.ates.accounting.domain.user.data.User

data class AccountCreate(
    val user: User,
    val type: Account.Type,
    val description: String? = null,
)

data class AccountUpdate(
    val account: Account,
    val status: Account.Status,
    val description: String? = null,
)
