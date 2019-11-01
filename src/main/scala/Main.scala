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
}
