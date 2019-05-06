package theGame

class Game {
  val playerSize: Double = 0.6
  val players: Map[String, Player] = Map()
  val player: Player = new Player(player.location, player.velocity)
  val Width: Double = 20
  val Height: Double = 30
  var Line: Double = -0.3
  //circle on circle collision (orc attack)

  def detectCollision(player: Player, player2: Player, boundary: Boundary): Boolean = {
   if(player.location.x == player2.location.x && player.location.y == player2.location.y){
     return false
   }
    val mObj = Physics.slope(player.location, player2.location)
    val bObj = Physics.yIntercept(player.location, mObj)

    val mBound = Physics.slope(boundary.start, boundary.end)
    val bBound = Physics.yIntercept(boundary.start, mBound)
    if (Physics.equalDoubles(mObj, mBound)){
      return false
    }

    //    m1x + b1 = m2x + b2
    //    m1x - m2x = b2 - b1
    //    x(m1x - m2) = b2 - b1
    //    x = (b2 - b1) / (m1x - m2)

    val ix: Double = (bBound - bObj) / (mObj - mBound)
    val iy: Double = ix * mObj + bObj
    val iy_redundant: Double = ix * mBound + bBound
    val objLeft = player.location.x.min(player2.location.x)
    val objRight = player.location.x.max(player2.location.x)

    val objUp = player.location.y.min(player2.location.y)
    val objDown = player.location.y.max(player2.location.y)
    val bLeft = boundary.start.x.min(boundary.end.x)
    val bRight = boundary.start.x.max(boundary.end.x)
    val bUp = boundary.start.y.min(boundary.end.y)
    val bDown = boundary.start.y.max(boundary.end.y)
    ((ix >= objLeft - Physics.EPSILON && ix <= objRight + Physics.EPSILON) && (iy >= objUp-Physics.EPSILON && iy <= objDown+Physics.EPSILON)) && ((ix >= bLeft -Physics.EPSILON&& ix <= bRight+Physics.EPSILON) && (iy >= bUp-Physics.EPSILON && iy <= bDown+Physics.EPSILON))
  }
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

  // square on circle collision (melee/projectile hit boxes are a square)
