package fruit

trait Valuable {
  def value: BigDecimal
}

trait Fruit extends Valuable

case object Apple extends Fruit {
  val value = BigDecimal("0.6")
}

case object Melon extends Fruit {
  val value = BigDecimal("1.0")
}

case object Orange extends Fruit {
  val value = BigDecimal("0.25")
}

case object Banana extends Fruit {
  val value = BigDecimal("0.20")
}
