import javax.sql.rowset.spi.TransactionalWriter

class Bank(val allowedAttempts: Integer = 3) {

    private val transactionsQueue: TransactionQueue = new TransactionQueue()
    private val processedTransactions: TransactionQueue = new TransactionQueue()


    def addTransactionToQueue(from: Account, to: Account, amount: Double): Unit = {
      val transaction = new Transaction(transactionsQueue, processedTransactions, from, to, amount, allowedAttempts)
      transactionsQueue.push(transaction)
      Main.thread(processTransactions)
    }
                                                // TODO
                                                // project task 2
                                                // create a new transaction object and put it in the queue
                                                // spawn a thread that calls processTransactions

    private def processTransactions: Unit = {
	  while (!this.transactionsQueue.isEmpty){
      val transaction = this.transactionsQueue.pop
      val t:Thread = Main.thread {
        if (transaction.status == TransactionStatus.PENDING ){
          transaction.run
          Thread.sleep(50)
        }
        if (transaction.status == TransactionStatus.SUCCESS || transaction.status == TransactionStatus.FAILED) {
          this.processedTransactions.push(transaction)
        }
        else{
          this.transactionsQueue.push(transaction)
          processTransactions
        }
      }
      }
    }

    def addAccount(initialBalance: Double): Account = {
        new Account(this, initialBalance)
    }

    def getProcessedTransactionsAsList: List[Transaction] = {
        processedTransactions.iterator.toList
    }

}
