package toughdevschool.ates.task.api

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import toughdevschool.ates.task.domain.task.data.Task
import java.util.UUID

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

    @Schema(name = "TaskCreateRequest")
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

        @Schema(name = "TaskUpdateRequest")
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
}
