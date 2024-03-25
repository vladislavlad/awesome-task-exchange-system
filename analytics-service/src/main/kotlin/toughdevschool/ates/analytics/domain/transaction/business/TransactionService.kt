package toughdevschool.ates.analytics.domain.transaction.business

import software.darkmatter.platform.service.CrudService
import toughdevschool.ates.analytics.domain.transaction.data.Transaction

interface TransactionService : CrudService<Transaction, Long, TransactionCreate, Unit>
