package toughdevschool.ates.accounting.api.rest

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import toughdevschool.ates.accounting.api.TransferDto
import toughdevschool.ates.accounting.domain.transaction.transfer.TransferApi

@Tag(name = "Transfer")
@RestController
@RequestMapping("/transfer")
class TransferController(
    private val transferApi: TransferApi,
) {

    @PostMapping
    suspend fun transfer(@RequestBody body: TransferDto.Request.Body) =
        transferApi.handle(TransferDto.Request(body))
}
