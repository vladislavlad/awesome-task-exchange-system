package software.darkmatter.domain.accounting

data class Transaction(
    val id: String,
    val amount: Double,
    val date: String,
    val description: String
)
