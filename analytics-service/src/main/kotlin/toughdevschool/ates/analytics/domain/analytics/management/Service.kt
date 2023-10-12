package toughdevschool.ates.analytics.domain.analytics.management

import arrow.core.Either
import org.springframework.stereotype.Component
import software.darkmatter.platform.error.BusinessError
import software.darkmatter.platform.syntax.UnitRight

@Component
class Service : ManagementAnalyticsService {

    override suspend fun perform(request: Unit): Either<BusinessError, Unit> = UnitRight
}