import org.scalatest._

class MainTest extends FunSuite {
  test("Should return factorial 5 = 120") {
    assert(Main.factorial(5) == 120)
    assert(Main.factorialRecursive(5) == 120)
  }
}
