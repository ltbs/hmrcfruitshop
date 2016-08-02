package fruit

trait Fruit {
  def value: BigDecimal
}

object Apple extends Fruit {
  val value = BigDecimal("0.6")
  override def toString = "Apple"
}

object Orange extends Fruit {
  val value = BigDecimal("0.25")
  override def toString = "Orange"
}