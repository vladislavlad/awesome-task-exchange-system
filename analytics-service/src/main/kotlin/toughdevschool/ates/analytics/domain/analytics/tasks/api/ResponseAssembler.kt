package toughdevschool.ates.analytics.domain.analytics.tasks.api

import arrow.core.Either
import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.ResponseAssembler
import software.darkmatter.platform.error.BusinessError
import toughdevschool.ates.analytics.domain.analytics.TaskAnalytics

@Component
class ResponseAssembler : ResponseAssembler<Unit, TaskAnalytics> {

    override suspend fun assemble(business: Unit): Either<BusinessError, TaskAnalytics> {
        TODO("Not yet implemented")
    }
}
