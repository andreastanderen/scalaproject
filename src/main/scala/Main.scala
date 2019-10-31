object Main extends App {

    def thread(body: => Unit): Thread = {
        val t = new Thread {
            override def run() = body
        }
        t
    }
    val bank:Bank = new Bank
    val acc = new Account(bank, 500)
    val acc2= new Account(bank, 500)
    bank.addTransactionToQueue(acc, acc2, 200)
    bank.addTransactionToQueue(acc2, acc, 200)
    println(acc.balance.amount)
    println(acc2.balance.amount)
    println(acc.balance.amount)
    println(acc2.balance.amount)
    println(acc.balance.amount)
    println(acc2.balance.amount)
    println(acc.balance.amount)
    println(acc2.balance.amount)
    println(acc.balance.amount)
    println(acc2.balance.amount)
    println(acc.balance.amount)
    println(acc2.balance.amount)
    println(acc.balance.amount)
    println(acc2.balance.amount)
    println(acc.balance.amount)
    println(acc2.balance.amount)
    println(acc.balance.amount)
    println(acc2.balance.amount)
    println(acc.balance.amount)
    println(acc2.balance.amount)
    println(acc.balance.amount)
    println(acc2.balance.amount)
    println(acc.balance.amount)
    println(acc2.balance.amount)
}
