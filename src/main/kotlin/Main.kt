import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    println("Enter the number of rows:")
    val numRows = scanner.nextInt()

    println("Enter the number of seats in each row:")
    val numSeats = scanner.nextInt()

    val cinema = Array(numRows) { Array(numSeats) { "S" } }
    var purchasedTickets = 0
    val totalTickets = numRows * numSeats
    var currentIncome = 0

    fun printCinema() {
        println("\nCinema:")
        for (i in 0..numRows) {
            for (j in 0..numSeats) {
                if (i == 0 && j == 0) {
                    print("  ")
                } else if (i == 0) {
                    print("${j} ")
                } else if (j == 0) {
                    print("$i ")
                } else {
                    print("${cinema[i - 1][j - 1]} ")
                }
            }
            println()
        }
    }

    fun buyTicket() {
        var ticketPrice = 0
        var row = -1
        var seat = -1
        while (true) {
            println("\nEnter a row number:")
            row = scanner.nextInt() - 1
            println("Enter a seat number in that row:")
            seat = scanner.nextInt() - 1
            if (row < 0 || row >= numRows || seat < 0 || seat >= numSeats) {
                println("\nWrong input!")
            } else if (cinema[row][seat] == "B") {
                println("\nThat ticket has already been purchased!")
            } else {
                break
            }
        }

        if (totalTickets <= 60) {
            ticketPrice = 10
        } else {
            ticketPrice = if (row < numRows / 2) 10 else 8
        }

        println("\nTicket price: $$ticketPrice")
        cinema[row][seat] = "B"
        purchasedTickets++
        currentIncome += ticketPrice
    }

    fun showStatistics() {
        val percentage = String.format("%.2f", purchasedTickets.toDouble() / totalTickets.toDouble() * 100)
        val totalIncome = if (totalTickets <= 60) {
            totalTickets * 10
        } else {
            val frontHalfRows = numRows / 2
            val backHalfRows = numRows - frontHalfRows
            (frontHalfRows * numSeats * 10) + (backHalfRows * numSeats * 8)
        }

        println("\nNumber of purchased tickets: $purchasedTickets")
        println("Percentage: $percentage%")
        println("Current income: $$currentIncome")
        println("Total income: $$totalIncome")
    }

    while (true) {
        println("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit")
        when (scanner.nextInt()) {
            1 -> printCinema()
            2 -> buyTicket()
            3 -> showStatistics()
            0 -> return
            else -> println("\nWrong input!")
        }
    }
}
