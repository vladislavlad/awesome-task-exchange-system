package toughdevschool.ates.accounting.domain.user.data

import software.darkmatter.platform.data.CoroutineCrudSortingRepository
import java.util.UUID

interface UserRepository : CoroutineCrudSortingRepository<User, Long> {

    suspend fun findByUsername(username: String): User?

    suspend fun findByUuid(uuid: UUID): User?
}
