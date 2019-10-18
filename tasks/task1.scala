
object Main extends App {
  println("hello world 2");
  def append(list: List[String]), new_element: Int): List[Int] = {
    list match {
      case Nil => List(new_element)
      case x :: xs => x::append(xs, new_element)
    }
  }
}