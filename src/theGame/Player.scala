package theGame
import physics.PhysicsVector
class Player(Location: PhysicsVector, Velocity: PhysicsVector) extends PhysicalObject(Location, Velocity) {
  val speed: Double = 10.0
  def move(direction: PhysicsVector){
    val normalDirection = direction.normal2d()
    this.velocity = new PhysicsVector(normalDirection.x * speed, normalDirection.y * speed)
  }
  def stop(): Unit ={
    this.velocity = new PhysicsVector(0, 0)
  }


}
