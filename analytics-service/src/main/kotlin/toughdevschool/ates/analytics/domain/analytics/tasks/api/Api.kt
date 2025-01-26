package toughdevschool.ates.analytics.domain.analytics.tasks.api

import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.RequestAssembler
import software.darkmatter.platform.assembler.ResponseAssembler
import toughdevschool.ates.analytics.api.TaskAnalyticsDto
import toughdevschool.ates.analytics.domain.analytics.TaskAnalytics
import toughdevschool.ates.analytics.domain.analytics.TaskAnalyticsApi
import toughdevschool.ates.analytics.domain.analytics.tasks.TaskAnalyticsService

@Component("taskAnalyticsApi")
class Api(
    override val requestAssembler: RequestAssembler<Unit, Unit>,
    override val service: TaskAnalyticsService,
    override val responseAssembler: ResponseAssembler<TaskAnalytics, TaskAnalyticsDto.Response>,
) : TaskAnalyticsApi()
