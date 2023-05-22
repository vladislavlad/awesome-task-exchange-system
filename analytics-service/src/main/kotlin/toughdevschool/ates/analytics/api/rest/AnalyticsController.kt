package toughdevschool.ates.analytics.api.rest

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import software.darkmatter.platform.api.handle
import toughdevschool.ates.analytics.domain.analytics.ManagementAnalyticsApi
import toughdevschool.ates.analytics.domain.analytics.TaskAnalyticsApi

@RestController
@RequestMapping("/analytics")
class AnalyticsController(
    @Qualifier("managementAnalyticsApi")
    private val managementAnalyticsApi: ManagementAnalyticsApi,
    @Qualifier("taskAnalyticsApi")
    private val taskAnalyticsApi: TaskAnalyticsApi,
) {

    @GetMapping(path = ["/management"])
    suspend fun auditLog(): ResponseEntity<*> = managementAnalyticsApi.handle()

    @GetMapping(path = ["/tasks"])
    suspend fun statistics(): ResponseEntity<*> = taskAnalyticsApi.handle()
}
