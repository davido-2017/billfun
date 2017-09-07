package billcalculator

import org.scalatest.FunSuite

class BillCalculatorTest extends FunSuite {

  def fixture =
    new {
      val menuItems: Map[String, BigDecimal] = Map("Cola" -> BigDecimal(".50"),
        "Coffee" -> BigDecimal("1.00"), "Cheese Sandwich" -> BigDecimal("2.00"), "Steak Sandwich" -> BigDecimal("4.50"))
      val billCalculator: BillCalculator = new BillCalculator(menuItems)
    }

  test("BillCalculator.calculateBill purchased items") {
    val f = fixture
    assert(f.billCalculator.calculateCost(Array("Cola", "Coffee", "Cheese Sandwich")) == BigDecimal(3.5))
  }

  test("BillCalculator.calculateBill no items") {
    val f = fixture
    assert(f.billCalculator.calculateCost(Array()) == BigDecimal(0))
  }


}





