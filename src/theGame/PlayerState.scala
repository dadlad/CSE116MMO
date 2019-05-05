package theGame

abstract class PlayerState(player: Player){

  var timeInState: Double = 0.0

  def update(dt: Double): Unit = {
    timeInState += dt

    if(player.leftKeyHeld){
      this.()
    }
    if(player.rightKeyHeld){
      this.rightPressed()
    }
  }
  def leftPressed(): Unit = {}

  def rightPressed(): Unit = {}

  def jumpPressed(): Unit = {}

  def leftReleased(): Unit = {
    player.stop()
  }

  def rightReleased(): Unit = {
    player.stop()
  }
}
