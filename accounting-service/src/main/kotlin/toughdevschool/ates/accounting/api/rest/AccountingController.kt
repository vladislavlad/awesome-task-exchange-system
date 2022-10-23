package toughdevschool.ates.accounting.api.rest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounting")
class AccountingController {

    @GetMapping(path = ["/audit/log"])
    suspend fun auditLog(): ResponseEntity<*> = TODO()

    @GetMapping(path = ["/statistics"])
    suspend fun statistics(): ResponseEntity<*> = TODO()
}
