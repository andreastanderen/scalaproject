class Concurrency(val test: String="heisann") {
  private var counter: Int = 0

  def printCount(): Unit = {
    println(counter)
  }

  def newThread(func: () => Unit): Thread = {
    new Thread(new Runnable {
      override def run(): Unit = {
        func()
      }
    })
  }



  def increaseCounterUnSafe(): Unit = {
    counter += 1
  }
}

