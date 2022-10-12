package toughdevschool.ates.task.domain.user.handler

import org.springframework.stereotype.Component
import toughdevschool.ates.task.domain.user.business.UserCreate
import toughdevschool.ates.task.domain.user.business.UserService
import toughdevschool.ates.task.event.consumer.cud.model.UserData

@Component
class CreateHandler(
    private val userService: UserService,
) {

    suspend fun handle(userData: UserData) =
        userService.create(
            UserCreate(
                uuid = userData.uuid,
                username = userData.username,
                firstName = userData.firstName,
                lastName = userData.lastName,
                middleName = userData.middleName
            )
        )
}
