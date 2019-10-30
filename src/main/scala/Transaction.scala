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
        var value = Queue(0)
        Queue - value
        value
      }
    }
    

    // Return whether the queue is empty
    def isEmpty: Boolean = {
      Queue.length == 0
    }

    // Add new element to the back of the queue
    def push(t: Transaction): Unit = {
      this.synchronized{
        Queue += t
      }
    }

    // Return the first element from the queue without removing it
    def peek: Transaction = {
      this.synchronized{
        Queue(0)
      }
    }

    // Return an iterator to allow you to iterate over the queue
    def iterator: Iterator[Transaction] = {
      this.synchronized{
        Queue.iterator
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

  override def run= {

    def doTransaction() = {
      val res_to = from.withdraw(amount)
L     /*if(val.isLeft){
        val res_to = to.deposit(amount)
        if (res_to.isRight){
          processedTransactions.push(this)
        }
      }else{
      processedTransactions.push(this)
      }
       */
    }

      // TODO - project task 3
      // make the code below thread safe
      if (status == TransactionStatus.PENDING) {
          doTransaction
          Thread.sleep(50) // you might want this to make more room for
                           // new transactions to be added to the queue
      }

    }
}
