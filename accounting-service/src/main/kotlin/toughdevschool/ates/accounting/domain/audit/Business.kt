package toughdevschool.ates.accounting.domain.audit

import software.darkmatter.platform.api.http.HttpApi
import toughdevschool.ates.accounting.api.AccountingAuditDto

typealias AccountingAuditLogApi = HttpApi<AccountingAuditDto.Request>
