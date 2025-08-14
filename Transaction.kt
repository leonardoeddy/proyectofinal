data class Transaction(
    val id: Int,
    val type: String, // "Ingreso" o "Gasto"
    val category: String,
    val amount: Double,
    val date: String
)
