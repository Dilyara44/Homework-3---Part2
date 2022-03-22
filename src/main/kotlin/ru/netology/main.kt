package ru.netology

fun main() {
    println(countCommission("MasterCard", 23000.00, 1233.00))
}


fun countCommission(
    typeOfCard: String = "VK Pay",
    sumOfPreviousTransfers: Double = 0.00,
    transactionSum: Double,
): Double {
    return when (typeOfCard) {
        "MasterCard", "Maestro" -> if (sumOfPreviousTransfers + transactionSum < 75_000) {
            0.00
        } else {
            val commission = transactionSum * 0.6 / 100 + 20
            commission
        }
        "VISA", "Мир" ->
            if ((transactionSum * 0.75 / 100) < 35) {
                val commission = 35.00
                commission
            } else {
                return transactionSum * 0.75 / 100
            }

        else -> 0.00
    }
}
