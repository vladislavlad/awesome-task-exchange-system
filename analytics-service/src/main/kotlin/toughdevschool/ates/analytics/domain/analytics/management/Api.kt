package toughdevschool.ates.analytics.domain.analytics.management

import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.RequestAssembler
import software.darkmatter.platform.assembler.ResponseAssembler
import toughdevschool.ates.analytics.domain.analytics.ManagementAnalyticsApi

@Component("managementAnalyticsApi")
class Api(
    override val requestAssembler: RequestAssembler<Unit, Unit>,
    override val service: ManagementAnalyticsService,
    override val responseAssembler: ResponseAssembler<Unit, Unit>,
) : ManagementAnalyticsApi()
