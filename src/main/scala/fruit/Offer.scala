package fruit

case class Offer(description: String, value: BigDecimal) extends Valuable {
  override def toString = description
}

object Offer {
  def bogofApplesAndBananas(items: Seq[Valuable]) = {
    val applicableItems = items.filter(x => x == Apple || x == Banana)
    val ret = {applicableItems.size / 2} match {
      case 0 => None
      case x =>
        val cheapest = applicableItems.sortBy(_.value).take(x)
        Some(Offer("BOGOF Apples and Bananas", -cheapest.value))
    }
  }

  def thirdOrange(items: Seq[Valuable]) =
    {items.filter(_ == Orange).size / 3} match {
      case 0 => None
      case x => Some(Offer("Third Orange", x * -Orange.value))
    }

  def melonsTwoForThree(items: Seq[Valuable]) =
    {items.filter(_ == Melon).size / 3} match {
      case 0 => None
      case x => Some(Offer("Third Melon", x * -Melon.value))
    }

}
