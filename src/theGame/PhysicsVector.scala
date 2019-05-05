package theGame

class PhysicsVector(var x: Double, var y: Double, var z: Double = 0.0) {

  def distance2d(other: PhysicsVector): Double = {
    Math.sqrt(Math.pow(x - other.x, 2.0) + Math.pow(y - other.y, 2.0))
  }
  override def toString: String = {
    "(" + x + ", " + y + ", " + z + ")"
  }

}
