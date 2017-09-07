package billcalculator

class BillCalculator(val menuItems: Map[String, BigDecimal]) {

  def calculateCost(purchasedItems: Array[String]): BigDecimal = {
    purchasedItems.map(s => menuItems(s)).sum
  }

}
