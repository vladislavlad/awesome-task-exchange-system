package toughdevschool.ates.analytics.domain.analytics

import arrow.core.Either
import org.springframework.stereotype.Component
import software.darkmatter.platform.api.http.ServiceApi
import software.darkmatter.platform.error.BusinessError
import software.darkmatter.platform.service.Service
import software.darkmatter.platform.syntax.UnitRight

typealias ManagementAnalyticsApi = ServiceApi<Unit, Unit, Unit, Unit>
typealias TaskAnalyticsApi = ServiceApi<Unit, Unit, Unit, Unit>

@Component
class ManagementAnalyticsService : Service<Unit, Unit> {

    override suspend fun perform(request: Unit): Either<BusinessError, Unit> = UnitRight
}

@Component
class TaskAnalyticsService : Service<Unit, Unit> {

    override suspend fun perform(request: Unit): Either<BusinessError, Unit> = UnitRight
}
