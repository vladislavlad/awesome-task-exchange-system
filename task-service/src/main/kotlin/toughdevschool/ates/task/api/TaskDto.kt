package toughdevschool.ates.task.api

import org.springframework.data.domain.Pageable
import toughdevschool.ates.task.domain.task.data.Task
import java.util.UUID
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

object TaskDto {

    data class Response(
        val id: Long,
        val uuid: UUID,
        val title: String,
        val description: String,
        val jiraId: String?,
        val status: Task.Status,
        val userUuid: UUID,
    )

    data class CreateRequest(
        @field:NotEmpty
        @field:Pattern(regexp = "^(((?!\\[|\\])).)+\$", message = "Use jiraId field")
        val title: String?,
        @field:NotNull
        val description: String?,
        val jiraId: String?,
    )

    data class UpdateRequest(
        val taskId: Long,
        val body: Body,
    ) {

        data class Body(
            @field:NotEmpty
            @field:Pattern(regexp = "^(((?!\\[|\\])).)+\$", message = "Use jiraId field")
            var title: String?,
            @field:NotNull
            var description: String?,
            var jiraId: String?,
        )
    }

    data class TasksReassignedResponse(
        val reassignedTasksCount: Int
    )

    data class TaskCompleteRequest(
        val taskId: Long,
    )

    data class TaskAssignedList(
        val pageable: Pageable,
    )
}
