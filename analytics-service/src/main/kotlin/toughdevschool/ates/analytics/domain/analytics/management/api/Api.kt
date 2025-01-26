package toughdevschool.ates.analytics.domain.analytics.management.api

import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.RequestAssembler
import software.darkmatter.platform.assembler.ResponseAssembler
import toughdevschool.ates.analytics.api.ManagementAnalyticsDto
import toughdevschool.ates.analytics.domain.analytics.ManagementAnalyticsApi
import toughdevschool.ates.analytics.domain.analytics.management.ManagementAnalyticsService

@Component("managementAnalyticsApi")
class Api(
    override val requestAssembler: RequestAssembler<Unit, Unit>,
    override val service: ManagementAnalyticsService,
    override val responseAssembler: ResponseAssembler<Unit, ManagementAnalyticsDto.Response>,
) : ManagementAnalyticsApi()
