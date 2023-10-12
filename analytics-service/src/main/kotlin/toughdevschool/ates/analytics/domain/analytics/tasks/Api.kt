package toughdevschool.ates.analytics.domain.analytics.tasks

import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.RequestAssembler
import software.darkmatter.platform.assembler.ResponseAssembler
import toughdevschool.ates.analytics.domain.analytics.TaskAnalytics
import toughdevschool.ates.analytics.domain.analytics.TaskAnalyticsApi

@Component("taskAnalyticsApi")
class Api(
    override val requestAssembler: RequestAssembler<Unit, Unit>,
    override val service: TaskAnalyticsService,
    override val responseAssembler: ResponseAssembler<Unit, TaskAnalytics>,
) : TaskAnalyticsApi()
