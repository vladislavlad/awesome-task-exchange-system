package toughdevschool.ates.task.domain.user.handler

import org.springframework.stereotype.Component
import toughdevschool.ates.task.domain.user.business.UserCreate
import toughdevschool.ates.task.domain.user.business.UserService
import toughdevschool.ates.task.event.consumer.cud.model.UserInfo

@Component
class CreateHandler(
    private val userService: UserService,
) {

    suspend fun handle(userInfo: UserInfo) =
        userService.create(
            UserCreate(
                uuid = userInfo.uuid,
                username = userInfo.username,
                firstName = userInfo.firstName,
                lastName = userInfo.lastName,
                middleName = userInfo.middleName
            )
        )
}
