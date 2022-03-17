package ru.netology

fun main() {
    val typeOfCard = listOf("MasterCard", "VISA", "Maestro", "Мир", "VK Pay").random()
    val sumOfPreviousTransfers = (0..700000).random()
    val transactionSum = (0..200000).random()
    val sumOfCommissionForVISA = 0.75

    println(typeOfCard)
    println(sumOfPreviousTransfers)
    println(transactionSum)
    println(" ")
    countCommission(typeOfCard, sumOfPreviousTransfers, transactionSum, sumOfCommissionForVISA)

}


fun countCommission(
    typeOfCard: String = "VK Pay",
    sumOfPreviousTransfers: Int = 0,
    transactionSum: Int,
    sumOfCommissionForVISA: Double
) {
    when (limits(sumOfPreviousTransfers, transactionSum, typeOfCard)) {
        true ->
            when (typeOfCard) {

                "MasterCard", "Maestro" -> if (sumOfPreviousTransfers + transactionSum < 75_000) {
                    println("В рамках акции комиссия не взимается. Сумма перевода: $transactionSum.")
                } else {
                    val commission = transactionSum * 0.6 / 100 + 20
                    val amount = transactionSum - commission
                    println("Сумма перевода: $amount. Сумма комиссии: $commission.")
                }

                "VISA", "Мир" ->
                    if ((countCommissionForVISA(sumOfCommissionForVISA, transactionSum)) < 35) {
                        val commission = 35
                        val amount = transactionSum - commission
                        println("Сумма перевода: $amount. Сумма комиссии: $commission. ")
                    } else {
                        val commission = countCommissionForVISA(sumOfCommissionForVISA, transactionSum)
                        val amount = transactionSum - commission
                        println("Сумма перевода: $amount. Сумма комиссии: $commission.")
                    }

                "VK Pay" ->
                    println("Комиссия не взимается. Сумма перевода: $transactionSum")
            }

        false ->
            println("Лимиты превышены. Попробуйте другой способ перевода.")


    }
}

fun limits(sumOfPreviousTransfers: Int, transactionSum: Int, typeOfCard: String): Boolean {
    when (typeOfCard) {
        "MasterCard", "Maestro", "VISA", "Мир" ->
            return transactionSum < 150_000 && transactionSum + sumOfPreviousTransfers < 600_000
        "VK Pay" -> return transactionSum < 15_000 && transactionSum + sumOfPreviousTransfers < 40_000
    }
    return false
}

fun countCommissionForVISA(sumOfCommissionForVISA: Double, transactionSum: Int): Double {
    return transactionSum * sumOfCommissionForVISA / 100
}