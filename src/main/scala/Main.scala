object Main extends App {

    def thread(body: => Unit): Thread = {
        val t = new Thread {
            override def run() = body
        }
        t
    }

    println("Hello World")
  
}
