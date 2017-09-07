package billcalculator

object Main extends App {
  val menuItems: List[MenuItem] = List(Drink("Cola", BigDecimal(".50")),
    Drink("Coffee", BigDecimal("1.00")),
    Food("Cheese Sandwich", BigDecimal("2.00")),
    HotFood("Steak Sandwich", BigDecimal("4.50")))
  val billCalculator: BillCalculator = new BillCalculator(menuItems)

  val cost = billCalculator.calculateCost(Array("Cola", "Coffee", "Cheese Sandwich"))
  println(s"Bill cost is: $cost")


}
