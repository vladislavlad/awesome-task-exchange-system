package toughdevschool.ates.task.domain.user.handler

import arrow.core.flatMap
import org.springframework.stereotype.Component
import toughdevschool.ates.event.cud.user.v1.UserData
import toughdevschool.ates.task.domain.user.business.UserService
import toughdevschool.ates.task.domain.user.business.UserUpdate

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
            }
}
