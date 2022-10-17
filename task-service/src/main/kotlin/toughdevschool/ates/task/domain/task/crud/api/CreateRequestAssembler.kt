package toughdevschool.ates.task.domain.task.crud.api

import arrow.core.Either
import arrow.core.continuations.either
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import software.darkmatter.platform.assembler.RequestAssembler
import software.darkmatter.platform.data.OffsetLimitPage
import software.darkmatter.platform.error.BusinessError
import toughdevschool.ates.task.api.TaskDto
import toughdevschool.ates.task.domain.task.crud.business.TaskCreate
import toughdevschool.ates.task.domain.user.business.UserService
import kotlin.random.Random

@Component
class CreateRequestAssembler(
    private val userService: UserService,
) : RequestAssembler<TaskDto.CreateRequest, TaskCreate> {

    @Transactional(isolation = Isolation.READ_COMMITTED)
    override suspend fun assemble(request: TaskDto.CreateRequest): Either<BusinessError, TaskCreate> =
        either {
            TaskCreate(
                title = request.title!!,
                description = request.description!!,
                user = getRandomUser().bind()
            )
        }

    private suspend fun getRandomUser() =
        userService.count()
            .map { Random.nextLong(0, it) }
            .map { userService.list(OffsetLimitPage(1, it)).first() }
}
