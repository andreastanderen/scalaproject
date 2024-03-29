import exceptions._
import scala.collection.mutable

object TransactionStatus extends Enumeration {
  val SUCCESS, PENDING, FAILED = Value
}

class TransactionQueue {
private var Queue = new mutable.Queue[Transaction]()
    // TODO
    // project task 1.1
    // Add datastructure to contain the transactions

    // Remove and return the first element from the queue
    def pop: Transaction = {
      this.synchronized{
        while (isEmpty) {
          wait()
        }
        this.Queue.dequeue()
      }
    }
    

    // Return whether the queue is empty
    def isEmpty: Boolean = {
      this.synchronized{
        this.Queue.isEmpty
      }
    }

    // Add new element to the back of the queue
    def push(t: Transaction): Unit = {
      this.synchronized{
        this.Queue.enqueue(t)
        notifyAll()
      } }

    // Return the first element from the queue without removing it
    def peek: Transaction = {
      this.synchronized{
        this.Queue.head
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
        this.attempt += 1
        val res_from = from.withdraw(amount)
        if (res_from.isLeft) {
          val res_to = to.deposit(amount)
          this.status = TransactionStatus.SUCCESS
        }
      }

      this.synchronized{
        if (this.attempt < this.allowedAttemps){
          doTransaction
          Thread.sleep(50) // you might want this to make more room for
        } else{
          this.status = TransactionStatus.FAILED
        }
      }
    }
  }
