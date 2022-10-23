package toughdevschool.ates.accounting.domain.account.data

import org.springframework.data.repository.kotlin.CoroutineSortingRepository
import java.util.UUID

interface AccountRepository : CoroutineSortingRepository<Account, Long> {

    suspend fun findByUuid(uuid: UUID): Account?

    suspend fun findByUserId(userId: Long): Account?
}
