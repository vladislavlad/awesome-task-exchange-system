package software.darkmatter.domain.task.repository

import software.darkmatter.domain.task.Task

interface TaskRepository {

    fun getTasks(): List<Task>

    fun completeTask(id: Long)
}
