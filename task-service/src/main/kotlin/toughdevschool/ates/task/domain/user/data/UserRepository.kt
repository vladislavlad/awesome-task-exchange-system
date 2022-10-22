package toughdevschool.ates.task.domain.user.data

import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineSortingRepository
import java.util.UUID

interface UserRepository : CoroutineSortingRepository<User, Long> {

    suspend fun findByUsername(username: String): User?

    suspend fun findByUuid(uuid: UUID): User?

    @Query(
        """
            select u.*
            from users u 
                left join user_roles ur on ur.user_id = u.id where ur.role in (:roles) or ur.role is null
        """
    )
    fun findAllWithRoleIn(roles: List<String>): Flow<User>
}
