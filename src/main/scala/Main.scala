object Main extends App {

    def thread(body: => Unit): Thread = {
        val t = new Thread {
            override def run() = body
        }
        t.start
        t
    }
    val bank:Bank = new Bank
    val acc = new Account(bank, 500)
    val acc2= new Account(bank, 500)
    println(acc.balance.amount)

    val bank2 = new Bank()

    val acc3 = bank2.addAccount(100)
    val acc4 = bank2.addAccount(200)

    acc3 transferTo(acc4, 50)

    while (bank2.getProcessedTransactionsAsList.size != 1) {
      println(bank2.getProcessedTransactionsAsList.size)
      Thread.sleep(100)
    }
    println(acc3.getBalanceAmount)
    println(acc4.getBalanceAmount)

}
