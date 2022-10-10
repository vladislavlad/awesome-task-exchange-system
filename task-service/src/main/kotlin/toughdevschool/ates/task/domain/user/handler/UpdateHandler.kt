package toughdevschool.ates.task.domain.user.handler

import arrow.core.flatMap
import org.springframework.stereotype.Component
import toughdevschool.ates.task.domain.user.business.UserService
import toughdevschool.ates.task.domain.user.business.UserUpdate
import toughdevschool.ates.task.event.consumer.cud.model.UserInfo

@Component
class UpdateHandler(
    private val userService: UserService
) {

    suspend fun handle(userInfo: UserInfo) =
        userService.getByUsername(userInfo.username)
            .flatMap {
                userService.update(
                    UserUpdate(
                        user = it,
                        firstName = userInfo.firstName,
                        lastName = userInfo.lastName,
                        middleName = userInfo.middleName
                    )
                )
            }
}
