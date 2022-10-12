package toughdevschool.ates.task.domain.user.handler

import arrow.core.flatMap
import org.springframework.stereotype.Component
import toughdevschool.ates.task.domain.user.business.UserService
import toughdevschool.ates.task.domain.user.business.UserUpdate
import toughdevschool.ates.task.event.consumer.cud.model.UserData

@Component
class UpdateHandler(
    private val userService: UserService
) {

    suspend fun handle(userData: UserData) =
        userService.getByUsername(userData.username)
            .flatMap {
                userService.update(
                    UserUpdate(
                        user = it,
                        firstName = userData.firstName,
                        lastName = userData.lastName,
                        middleName = userData.middleName
                    )
                )
            }
}
