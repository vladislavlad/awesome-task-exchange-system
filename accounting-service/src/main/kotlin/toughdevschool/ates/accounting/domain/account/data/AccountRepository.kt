package toughdevschool.ates.accounting.domain.account.data

import software.darkmatter.platform.data.CoroutineCrudSortingRepository
import java.util.UUID

interface AccountRepository : CoroutineCrudSortingRepository<Account, Long> {

    suspend fun findByUuid(uuid: UUID): Account?

    suspend fun findByUserId(userId: Long): Account?
}
