package billcalculator

import scala.math.BigDecimal.RoundingMode

class BillCalculator(val menuItems: Map[String, MenuItem]) {

  private val HotFoodServiceChargeRate = new ServiceChargeRate(BigDecimal(".20"), Some(BigDecimal("20.00")))
  private val ColdFoodServiceChargeRate = new ServiceChargeRate(BigDecimal(".10"), None)
  private val NoServiceChargeRate = new ServiceChargeRate(BigDecimal("0"), None)

  def this(menuItemsList: List[MenuItem]) {
    this(menuItemsList.map((mi: MenuItem) => (mi.name, mi)).toMap)
  }

  def calculateCost(purchasedItems: Array[String]): BigDecimal = {
    if (purchasedItems.isEmpty) return BigDecimal("0.00")
    val pItemsList: List[MenuItem] = purchasedItems.map(s => menuItems(s)).toList
    val rawTotal: BigDecimal = pItemsList.map((mi: MenuItem) => mi.cost).sum
    val fullTotal = rawTotal + calculateServiceCharge(rawTotal, pItemsList)
    fullTotal.setScale(2, RoundingMode.HALF_EVEN)
  }

  private def calculateServiceCharge(rawTotal: BigDecimal, purchasedItems: List[MenuItem]): BigDecimal = {
    val serviceChargeRate: ServiceChargeRate = purchasedItems.map((i: MenuItem) => getServiceChargeRateForItem(i)).maxBy(_.percentage)
    val inclusiveTotal = rawTotal * serviceChargeRate.percentage
    if (serviceChargeRate.ceiling.isDefined && inclusiveTotal > serviceChargeRate.ceiling.get)
      serviceChargeRate.ceiling.get
    else
      inclusiveTotal
  }

  private def getServiceChargeRateForItem(menuItem: MenuItem): ServiceChargeRate = {
    menuItem match {
      case _: HotFood => HotFoodServiceChargeRate
      case _: Food => ColdFoodServiceChargeRate
      case _: Drink => NoServiceChargeRate
      case _ => NoServiceChargeRate
    }

  }

}
