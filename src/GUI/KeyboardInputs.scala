package GUI
import theGame.Player
import javafx.event.EventHandler
import javafx.scene.input.KeyEvent
abstract class KeyboardInputs(player: Player) extends EventHandler[KeyEvent]  {
  val Left: String
  val Right: String
  val Down: String

  override def handle(event: KeyEvent): Unit = {
    val keyCode = event.getCode
    event.getEventType.getName match {
      case "KEY_RELEASED" => keyCode.getName match {
        case this.Left => player.leftReleased()
        case this.Right => player.rightReleased()
        case this.Down => player.DownReleased()
        case _ =>
      }
      case "KEY_PRESSED" => keyCode.getName match {
        case this.Left => player.leftKeyHeld()
        case this.Right => player.rightKeyHeld()
        case this.Down => player.DownKeyHeld()
        case _ =>
      }
      case _ =>
    }
  }
}

class WASDInputs(player: Player) extends KeyboardInputs(player) {
  override val Left: String = "A"
  override val Right: String = "D"
  override val Down: String = "S"
}


class ArrowInputs(player: Player) extends KeyboardInputs(player) {
  override val Left: String = "Left"
  override val Right : String = "Right"
  override val Down: String = "Down"

}
