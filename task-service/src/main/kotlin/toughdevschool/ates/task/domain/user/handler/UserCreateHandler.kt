package toughdevschool.ates.task.domain.user.handler

import org.springframework.stereotype.Component
import toughdevschool.ates.task.domain.user.business.UserCreate
import toughdevschool.ates.task.domain.user.business.UserService
import toughdevschool.ates.task.event.consumer.cud.model.UserData

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
