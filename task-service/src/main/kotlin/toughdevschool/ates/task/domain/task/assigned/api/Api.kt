package toughdevschool.ates.task.domain.task.assigned.api

import arrow.core.flatMap
import arrow.core.traverse
import org.springframework.data.relational.core.query.Criteria.where
import org.springframework.data.relational.core.query.Query.query
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.ResponseAssembler
import software.darkmatter.platform.security.context.jwtAuthenticationFromSecurityContext
import toughdevschool.ates.task.api.TaskDto
import toughdevschool.ates.task.domain.task.assigned.business.TaskAssignedListApi
import toughdevschool.ates.task.domain.task.crud.business.TaskService
import toughdevschool.ates.task.domain.task.data.Task

@Component
class Api(
    val service: TaskService,
    val responseAssembler: ResponseAssembler<Task, TaskDto.Response>,
) : TaskAssignedListApi {

    override suspend fun handle(request: TaskDto.TaskAssignedList): ResponseEntity<*> =
        jwtAuthenticationFromSecurityContext()
            .flatMap { authentication ->
                service.list(
                    query(where(Task::userUuid.name).`is`(authentication.jwt.subject)),
                    request.pageable
                ).traverse { responseAssembler.assemble(it) }
            }
            .toResponseEntity()
}
