package toughdevschool.ates.task.domain.userRole.data

import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.repository.kotlin.CoroutineSortingRepository

interface UserRoleRepository : CoroutineSortingRepository<UserRole, Long> {

    @Modifying
    suspend fun deleteAllByUserIdAndRoleIn(userId: Long, roles: List<String>)
}
