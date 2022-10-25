package toughdevschool.ates.accounting.api.rest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import software.darkmatter.platform.api.handle
import toughdevschool.ates.accounting.domain.transaction.balance.BalanceApi

@RestController
@RequestMapping("/worker")
class WorkerController(
    private val balanceApi: BalanceApi,
) {

    @GetMapping(path = ["/balance"])
    suspend fun balance(): ResponseEntity<*> = balanceApi.handle()

    @GetMapping(path = ["/audit/log"])
    suspend fun auditLog(): ResponseEntity<*> = TODO()
}
