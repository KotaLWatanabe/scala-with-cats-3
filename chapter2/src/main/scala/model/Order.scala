package model

import group.Monoid

final case class Order(totalCost: Double, quantity: Double)

object Order {
  given orderMonoid: Monoid[Order] with
    def combine: (Order, Order) => Order = (x, y) =>
      Order(x.totalCost + y.totalCost, x.quantity + y.quantity)
    def empty: Order = Order(0.0, 0.0)
}
