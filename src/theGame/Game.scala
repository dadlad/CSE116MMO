package theGame

class Game {
  val playerSize: Double = 0.6
  val players: Map[String, Player] = Map()
  val player: Player = new Player(player.location, player.velocity)
  val Width: Double = 20
  val Height: Double = 30
  var Line: Double = -0.3
  //circle on circle collision (orc attack)

  def detectCollision(player: Player, pla): Boolean = {
    Math.pow(enemy.xLocation - player.xLocation, 2) + (player.xLocation - enemy.yLocation) <= Math.pow(player.radius + enemy.radius, 2)
  }

  // square on circle collision (melee/projectile hit boxes are a square)
  def detectHit(enemy: Enemy, attack: Attack): Boolean = {
    val circleDistanceX = Math.abs(enemy.xLocation - attack.xLocation)
    val circleDistanceY = Math.abs(enemy.yLocation - attack.yLocation)
    if (circleDistanceX > (attack.width / 2 + enemy.radius)) {
      return false
    }
    if (circleDistanceY > (attack.height / 2 + enemy.radius)) {
      return false
    }
    if (circleDistanceX <= (attack.width / 2)) {
      return true
    }
    if (circleDistanceY <= (attack.height / 2)) {
      return true
    }
    val cornerDistance = Math.pow(circleDistanceX - attack.width / 2, 2) + Math.pow(circleDistanceY - attack.height / 2, 2)
    cornerDistance <= Math.pow(enemy.radius, 2)
  }
}