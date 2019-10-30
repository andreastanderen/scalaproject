
object Main extends App {
  val x = List.range(0, 10)
  val test = new Concurrency("hei")
  def func():Unit = println("heiyooooO")
  val thread1 = test.newThread(test.increaseCounterSafe)
  val thread2 = test.newThread(test.increaseCounterSafe)
  val thread3 = test.newThread(test.printCount)
  val thread4 = test.newThread(test.increaseCounterUnSafe)
  val thread5 = test.newThread(test.increaseCounterUnSafe)
  val thread3 = test.newThread(test.printCount)
  /*
  thread1.start()
  thread2.start()
  thread3.start()
  */
  thread4.start()
  thread5.start()
  thread6.start()
  // RACE CONDITION
  // Added synchronized
}
