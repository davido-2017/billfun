package billcalculator

trait MenuItem {
  def name: String

  def cost: BigDecimal
}

case class Drink(name: String, cost: BigDecimal) extends MenuItem

case class Food(name: String, cost: BigDecimal) extends MenuItem

case class HotFood(name: String, cost: BigDecimal) extends MenuItem

