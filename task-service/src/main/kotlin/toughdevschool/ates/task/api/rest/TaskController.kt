package toughdevschool.ates.task.api.rest

import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import toughdevschool.ates.task.api.TaskDto
import toughdevschool.ates.task.domain.task.complete.business.TaskCompleteApi
import toughdevschool.ates.task.domain.task.crud.business.TaskCrudApi
import toughdevschool.ates.task.domain.task.reassign.business.TaskReassignApi
import javax.validation.Valid

@RestController
@RequestMapping("/tasks")
class TaskController(
    private val crudApi: TaskCrudApi,
    private val reassignApi: TaskReassignApi,
    private val completeApi: TaskCompleteApi,
) {

    @PostMapping(path = ["/reassign"])
    suspend fun reassign() = reassignApi.handle(Unit)

    @PostMapping(path = ["/{id}/complete"])
    suspend fun complete(@PathVariable id: Long) = completeApi.handle(TaskDto.TaskCompleteRequest(id))

    @GetMapping
    suspend fun list(@PageableDefault(size = 20, page = 0) pageable: Pageable) = crudApi.list(pageable)

    @GetMapping(path = ["/{id}"])
    suspend fun get(@PathVariable id: Long) = crudApi.get(id)

    @PostMapping
    suspend fun create(@Valid @RequestBody body: TaskDto.CreateRequest) = crudApi.create(body)

    @PutMapping(path = ["/{id}"])
    suspend fun update(
        @PathVariable id: Long,
        @Valid @RequestBody body: TaskDto.UpdateRequest.Body,
    ) = crudApi.update(TaskDto.UpdateRequest(id, body))
}
