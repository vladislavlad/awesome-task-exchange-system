package toughdevschool.ates.analytics.domain.userRole.data

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import software.darkmatter.platform.data.Model

@Table("user_roles")
data class UserRole(
    @Id
    override var id: Long? = null,
    val userId: Long,
    val role: String,
) : Model<Long>
