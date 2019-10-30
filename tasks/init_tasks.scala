// --------------------------- TASK 1A ---------------------------

object Main extends App {

  def for_loop(start: Int, end: Int): List[Int] = {
    val mylist: Array[Int] = new Array[Int](end);
    for (a <- start to end) {
      mylist(a - 1) = a;
    }
    mylist.toList
  }
  println(for_loop(1,50)(49));


  // --------------------------- TASK 1B ---------------------------

  def sum(list: List[Int]): Int = {
    var sum: Int = 0
    list.foreach{ sum += _}
    sum
  }
  println(sum(for_loop(1,100)))


  // --------------------------- TASK 1C ---------------------------
  def sum_rec2(list: List[Int], sum: Int): Int = {
    list match {
      case Nil => sum
      case head :: tail => sum_rec2(tail, sum + head)
    }
  }
  def sum_rec(list: List[Int]): Int ={
    sum_rec2(list,0)
  }
  println(sum_rec(for_loop(1,100)))


  // --------------------------- TASK 1D ---------------------------
  def fib(num: BigInt): BigInt = {
    //println(num);
    if (num == 0 || num == 1){
      num
    }
    else{
      fib(num - 1) + fib(num - 2)
    }
  }
  println(fib(10))

  // The different between the two datatypes BigInt and Int is the
  // size of the value the datatype is able to store. Int can only
  // store values up to 20 digits.


  // --------------------------- TASK 2A ---------------------------

  def newThread(func: () => Unit): Thread = {
    new Thread(new Runnable {
      override def run(): Unit = {
        func()
      }
    })
  }
  def func(): Unit = println("test")

  // --------------------------- TASK 2B ---------------------------
  private var counter: Int = 0

  def increaseCounterUnSafe(): Unit = {
    counter += 1
  }

  def printCount(): Unit = {
    println(counter)
  }

  val thread1 = newThread(increaseCounterUnSafe)
  val thread2 = newThread(increaseCounterUnSafe)
  val thread3 = newThread(printCount)

  /*
  thread1.start()
  thread2.start()
  thread3.start()
  */
  // --------------------------- TASK 2C ---------------------------

  def increaseCounterSafe(): Unit = {
    synchronized {
      counter += 1
    }
  }

  val thread4 = newThread(increaseCounterSafe)
  val thread5 = newThread(increaseCounterSafe)
  /*
  thread4.start()
  thread5.start()
  thread3.start()
  // RACE CONDITION
  // Added synchronized
  */

  // --------------------------- TASK 2D ---------------------------


  object test1 {
    lazy val number = 42;
    lazy val problem = test2.number;
  }

  object test2 {
    lazy val number = test1.number;
  }


  val deadlock1 = new Thread {
    override def run: Unit = {
      test1.problem
    }
  }

  val deadlock2 = new Thread{
    override def run: Unit ={
      test2.number
    }
  }

  //deadlock1.start()
  //deadlock2.start()
}


