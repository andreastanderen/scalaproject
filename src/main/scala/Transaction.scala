import exceptions._
import scala.collection.mutable

object TransactionStatus extends Enumeration {
  val SUCCESS, PENDING, FAILED = Value
}

class TransactionQueue {

    private var Queue = new mutable.ArrayBuffer[Transaction]()
    // TODO
    // project task 1.1
    // Add datastructure to contain the transactions

    // Remove and return the first element from the queue
    def pop: Transaction = {
      this.synchronized{
        var value = this.Queue(0)
        this.Queue -= value
        value
      }
    }
    

    // Return whether the queue is empty
    def isEmpty: Boolean = {
      this.Queue.isEmpty
    }

    // Add new element to the back of the queue
    def push(t: Transaction): Unit = {
      this.synchronized{
        this.Queue += t
      }
    }

    // Return the first element from the queue without removing it
    def peek: Transaction = {
      this.synchronized{
        this.Queue(0)
      }
    }

    // Return an iterator to allow you to iterate over the queue
    def iterator: Iterator[Transaction] = {
      this.synchronized{
        this.Queue.iterator
      }
    }
}

class Transaction(val transactionsQueue: TransactionQueue,
  val processedTransactions: TransactionQueue,
  val from: Account,
  val to: Account,
  val amount: Double,
  val allowedAttemps: Int) extends Runnable {

    var status: TransactionStatus.Value = TransactionStatus.PENDING
    var attempt = 0

    override def run = {

      def doTransaction() = {
        this.synchronized{
          this.attempt += 1
          val res_from = from.withdraw(amount)
          if (res_from.isLeft){
            val res_to = to.deposit(amount)
            this.status = TransactionStatus.SUCCESS
            t = this.transactionsQueue.pop
            this.processedTransactions.push(t)
          }

        }
      }
      // TODO - project task 3
      // make the code below thread safe
      if (this.status == TransactionStatus.PENDING && this.attempt < this.allowedAttemps) {
        doTransaction
        Thread.sleep(50) // you might want this to make more room for
        // new transactions to be added to the queue
      }
    }
  }
