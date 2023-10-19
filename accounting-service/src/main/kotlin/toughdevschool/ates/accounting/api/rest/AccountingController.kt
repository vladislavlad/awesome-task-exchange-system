package toughdevschool.ates.accounting.api.rest

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import software.darkmatter.platform.api.handle
import toughdevschool.ates.accounting.api.AccountingAuditDto
import toughdevschool.ates.accounting.domain.audit.AccountingAuditLogApi
import toughdevschool.ates.accounting.domain.statistics.StatisticsApi

@Tag(name = "Accounting")
@RestController
@RequestMapping("/accounting")
class AccountingController(
    private val auditLogApi: AccountingAuditLogApi,
    private val statisticsApi: StatisticsApi,
) {

    @GetMapping(path = ["/audit/log"])
    suspend fun auditLog(
        @RequestBody body: AccountingAuditDto.Request.Body,
        @RequestParam(defaultValue = "0") page: Int?,
        @RequestParam(defaultValue = "100") size: Int?,
    ): ResponseEntity<*> = auditLogApi.handle(AccountingAuditDto.Request(body, page!!, size!!))

    @GetMapping(path = ["/statistics"])
    suspend fun statistics(): ResponseEntity<*> = statisticsApi.handle()
}
