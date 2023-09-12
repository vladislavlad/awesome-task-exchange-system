package toughdevschool.ates.accounting.api.rest

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import software.darkmatter.platform.api.handle
import toughdevschool.ates.accounting.domain.statistics.StatisticsApi

@Tag(name = "Accounting")
@RestController
@RequestMapping("/accounting")
class AccountingController(
    private val statisticsApi: StatisticsApi,
) {

    @GetMapping(path = ["/audit/log"])
    suspend fun auditLog(): ResponseEntity<*> = statisticsApi.handle()

    @GetMapping(path = ["/statistics"])
    suspend fun statistics(): ResponseEntity<*> = TODO()
}
