package Player
import Player.Attacks._

abstract class Classes {
  var HP: Int
  var speed: Int
  val radius = 30
  //both xLocation and yLocation are centers of circle
  var xLocation: Double
  var yLocation: Double

  def attackPrimary(): Attack
  //need to get gui finished to do movement

}
