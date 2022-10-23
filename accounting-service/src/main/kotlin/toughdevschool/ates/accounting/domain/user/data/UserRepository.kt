package toughdevschool.ates.accounting.domain.user.data

import org.springframework.data.repository.kotlin.CoroutineSortingRepository
import java.util.UUID

interface UserRepository : CoroutineSortingRepository<User, Long> {

    suspend fun findByUsername(username: String): User?

    suspend fun findByUuid(uuid: UUID): User?
}
