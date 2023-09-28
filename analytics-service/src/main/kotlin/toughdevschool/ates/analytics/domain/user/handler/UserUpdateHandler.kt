package toughdevschool.ates.analytics.domain.user.handler

import arrow.core.flatMap
import org.springframework.stereotype.Component
import toughdevschool.ates.analytics.domain.user.business.UserService
import toughdevschool.ates.analytics.domain.user.business.UserUpdate
import toughdevschool.ates.event.cud.user.v1.UserData

@Component
class UserUpdateHandler(
    private val userService: UserService,
) {

    suspend fun handle(data: UserData) =
        userService.getByUsername(data.username)
            .flatMap {
                userService.update(
                    UserUpdate(
                        user = it,
                        firstName = data.firstName,
                        lastName = data.lastName,
                        middleName = data.middleName
                    )
                )
            }.map { }
}
