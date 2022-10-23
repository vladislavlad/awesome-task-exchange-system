package toughdevschool.ates.analytics.domain.analytics.tasks

import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.RequestAssembler
import software.darkmatter.platform.assembler.ResponseAssembler
import toughdevschool.ates.analytics.domain.analytics.TaskAnalyticsApi
import toughdevschool.ates.analytics.domain.analytics.TaskAnalyticsService

@Component
class Api(
    override val requestAssembler: RequestAssembler<Unit, Unit>,
    override val service: TaskAnalyticsService,
    override val responseAssembler: ResponseAssembler<Unit, Unit>,
) : TaskAnalyticsApi()
