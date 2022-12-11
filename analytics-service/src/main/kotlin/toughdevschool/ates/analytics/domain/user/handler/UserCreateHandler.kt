package toughdevschool.ates.analytics.domain.user.handler

import org.springframework.stereotype.Component
import toughdevschool.ates.event.cud.user.v1.UserData
import toughdevschool.ates.analytics.domain.user.business.UserCreate
import toughdevschool.ates.analytics.domain.user.business.UserService

@Component
class UserCreateHandler(
    private val userService: UserService,
) {

    suspend fun handle(data: UserData) =
        userService.create(
            UserCreate(
                uuid = data.uuid,
                username = data.username,
                firstName = data.firstName,
                lastName = data.lastName,
                middleName = data.middleName
            )
        )
}
