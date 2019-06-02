import org.scalatest._

class MainTest extends FunSuite {
  test("Should compose function compose(f,g) x => f(g(x))") {
    // Using square & inc as example
    val h = Main.compose(Main.square, Main.inc)
    assert(h(6) == 49)
  }
}
