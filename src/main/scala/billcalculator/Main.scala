package billcalculator

object Main extends App {
  val menuItems: Map[String, BigDecimal] = Map("Cola" -> BigDecimal(".50"),
    "Coffee" -> BigDecimal("1.00"), "Cheese Sandwich" -> BigDecimal("2.00"), "Steak Sandwich" -> BigDecimal("4.50"))
  val billCalculator: BillCalculator = new BillCalculator(menuItems)

  val cost = billCalculator.calculateCost(Array("Cola", "Coffee", "Cheese Sandwich"))
  println(s"Bill cost is: $cost")


}
