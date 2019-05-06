package theGame

class Player(location: PhysicsVector, velocity: PhysicsVector) extends PhysicalObject(location, velocity) {
  val speed: Double = 4.0
  def move(direction: PhysicsVector){
    val normalDirection = direction.normal2d()
    this.velocity = new PhysicsVector(normalDirection.x * speed, normalDirection.y * speed)
  }
  def stop(): Unit ={
    this.velocity = new PhysicsVector(0, 0)
  }

}
