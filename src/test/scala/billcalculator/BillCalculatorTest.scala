package billcalculator

import org.scalatest.FunSuite

class BillCalculatorTest extends FunSuite {

  def fixture =
    new {
      val menuItems: List[MenuItem] = List(Drink("Cola", BigDecimal(".50")),
        Drink("Coffee", BigDecimal("1.00")),
        Food("Cheese Sandwich", BigDecimal("2.00")),
        HotFood("Steak Sandwich", BigDecimal("4.50")),
        HotFood("Mega Deluxe Steak Sandwich", BigDecimal("250.00"))
      )

      val billCalculator: BillCalculator = new BillCalculator(menuItems)
    }

  test("BillCalculator.calculateBill purchased items all drinks") {
    val f = fixture
    assert(f.billCalculator.calculateCost(Array("Cola", "Coffee")) == BigDecimal("1.50"))
  }

  test("BillCalculator.calculateBill purchased items with hot food") {
    val f = fixture
    assert(f.billCalculator.calculateCost(Array("Steak Sandwich", "Cheese Sandwich", "Cola")) == BigDecimal("8.40"))
  }

  test("BillCalculator.calculateBill purchased items with hot food exceeding service charge ceiling") {
    val f = fixture
    assert(f.billCalculator.calculateCost(Array("Steak Sandwich", "Cheese Sandwich", "Cola", "Mega Deluxe Steak Sandwich")) == BigDecimal("277.00"))
  }


  test("BillCalculator.calculateBill purchased items with cold food") {
    val f = fixture
    assert(f.billCalculator.calculateCost(Array("Cheese Sandwich", "Coffee")) == BigDecimal("3.30"))
  }

  test("BillCalculator.calculateBill no items") {
    val f = fixture
    assert(f.billCalculator.calculateCost(Array()) == BigDecimal("0"))
  }


}





