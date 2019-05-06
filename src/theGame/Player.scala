package theGame

class Player(location: PhysicsVector, velocity: PhysicsVector) extends PhysicalObject(location, velocity) {
  var leftKeyHeld = false
  var rightKeyHeld = false
  var DownKeyHeld = false
  def leftTouched(): Unit = {
    this.leftKeyHeld = true
  }

  def rightTouched(): Unit = {
    this.rightKeyHeld = true
  }

  def DownTouched(): Unit = {
    this.DownKeyHeld = true
  }

  def leftReleased(): Unit = {
    this.leftKeyHeld = false
  }

  def rightReleased(): Unit = {
    this.rightKeyHeld = false
  }

  def DownReleased(): Unit = {
    this.DownKeyHeld = false
  }

}
