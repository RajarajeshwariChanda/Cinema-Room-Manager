package cinema

const val SHOW = 1
const val BUY = 2
const val STATISTICS = 3
const val EXIT = 0

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seats = readln().toInt() 
    var selectedRow : Int
    var selectedSeat : Int
    var purchasedTickets = 0
    var currentIncome = 0
    var percentage = 0
    val seatingArrangement = MutableList(rows+1) {MutableList(seats+1) {"S"}} 
    for(i in 1..rows) seatingArrangement[i][0] = i.toString()
    for(j in 1..seats) seatingArrangement[0][j] = j.toString()
    seatingArrangement[0][0] = " "
    while(true) {
        println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit")
        when(readln().toInt()) {
            SHOW -> {
                println("Cinema:")
                for(i in 0..rows) println(seatingArrangement[i].joinToString(" "))
            }
            BUY -> {
                println("Enter a row number:")
                selectedRow = readln().toInt()
                println("Enter a seat number in that row:")
                selectedSeat = readln().toInt()
                try{ if(seatingArrangement[selectedRow][selectedSeat] == "B") {
                        println("That ticket has already been purchased!")
                         println("Enter a row number:")
                        selectedRow = readln().toInt()
                        println("Enter a seat number in that row:")
                        selectedSeat = readln().toInt()
                }
                seatingArrangement[selectedRow][selectedSeat] = "B"
                } catch(e: IndexOutOfBoundsException) {
                    println("Wrong input!")
                    println("Enter a row number:")
                    selectedRow = readln().toInt()
                    println("Enter a seat number in that row:")
                    selectedSeat = readln().toInt()
                }
                purchasedTickets++
                val ticketPrice = if((rows*seats) <= 60 ) 10 else if (selectedRow <= rows/2 ) 10 else 8
                currentIncome += ticketPrice
                println("Ticket price: $$ticketPrice")
            }
            STATISTICS -> {
                val totalTickets = rows * seats
                println("Number of purchased tickets: $purchasedTickets")
                println("Percentage: ${"%.2f".format((purchasedTickets.toDouble() / totalTickets) * 100.0)}%")
                println("Current income: $$currentIncome")
                println("Total income: $${if (totalTickets <= 60) totalTickets * 10 else totalTickets * 9 - rows % 2 * seats }")
            }
            EXIT -> break
        }
        
    }
}
