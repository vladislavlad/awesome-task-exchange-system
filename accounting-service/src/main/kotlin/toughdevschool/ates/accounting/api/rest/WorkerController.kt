package toughdevschool.ates.accounting.api.rest

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import software.darkmatter.platform.api.handle
import toughdevschool.ates.accounting.api.AuditLogDto
import toughdevschool.ates.accounting.domain.transaction.balance.BalanceApi
import toughdevschool.ates.accounting.domain.transaction.log.AuditLogApi

@Tag(name = "History")
@RestController
@RequestMapping("/worker")
class WorkerController(
    private val balanceApi: BalanceApi,
    private val auditLogApi: AuditLogApi,
) {

    @GetMapping(path = ["/balance"])
    suspend fun balance() = balanceApi.handle()

    @GetMapping(path = ["/audit/log"])
    suspend fun auditLog() = auditLogApi.handle(AuditLogDto.Request)
}
