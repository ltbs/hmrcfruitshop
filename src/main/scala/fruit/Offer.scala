package fruit

case class Offer(description: String, value: BigDecimal) extends Valuable {
  override def toString = description
}

object Offer {
  def bogofApples(items: Seq[Valuable]) =
    {items.filter(_ == Apple).size / 2} match {
      case 0 => None
      case x => Some(Offer("BOGOF Apples",x * -Apple.value))
    }

  def thirdOrange(items: Seq[Valuable]) =
    {items.filter(_ == Orange).size / 3} match {
      case 0 => None
      case x => Some(Offer("Third Orange", x * -Orange.value))
    }
}
