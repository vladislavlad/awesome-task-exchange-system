package toughdevschool.ates.task.domain.user.handler

import arrow.core.flatMap
import org.springframework.stereotype.Component
import toughdevschool.ates.event.cud.user.v1.UserData
import toughdevschool.ates.task.domain.user.business.UserService

@Component
class UserDeleteHandler(
    private val userService: UserService,
) {

    suspend fun handle(data: UserData) =
        userService.getByUsername(data.username)
            .flatMap { userService.delete(it) }
}
