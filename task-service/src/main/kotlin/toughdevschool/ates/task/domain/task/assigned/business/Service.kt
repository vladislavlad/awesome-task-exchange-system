package toughdevschool.ates.task.domain.task.assigned.business

import arrow.core.raise.either
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.stereotype.Service
import software.darkmatter.platform.error.BusinessError
import toughdevschool.ates.task.domain.task.crud.business.TaskService
import toughdevschool.ates.task.domain.task.data.Task

@Service
class Service(
    private val taskService: TaskService,
) : TaskAssignedService {

    override suspend fun perform(request: TaskAssignedListRequest) =
        either<BusinessError, TaskAssignedListResponse> {

            TaskAssignedListResponse(
                tasks = taskService.list(
                    Query.query(Criteria.where(Task::userUuid.name).`is`(request.userUuid)),
                    request.pageable
                )
            )
        }
}
