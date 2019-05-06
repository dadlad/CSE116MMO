package GUI
import theGame.PhysicsVector
import theGame.Game
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.paint.Color
import scalafx.animation.AnimationTimer
import scalafx.scene.shape.Circle
import scalafx.scene.{Group, Scene}

//this is the actual game gui
//this will be opened after the player hits a "create character" button on the character selection screen
object MapGui extends JFXApp {
  var lastUpdateTime: Long = System.nanoTime()
  val game: Game = new Game
  val playerDesignSize: Double = game.playerSize
  val scaleFactor: Double = 60.0
  val windowWidth: Double = game.Width * scaleFactor
  val windowHeight: Double = game.Height * scaleFactor
  var playerDesign: Circle = playersDesign(game.player.location.x, game.player.location.y, Color.DarkRed)
  var sceneGraphics: Group = new Group {}
  sceneGraphics.children.add(playerDesign)
  for(player <- game.players.values){
    val PlayerDesign = playersDesign(player.location.x, player.location.y, Color.DarkRed)
    playerDesign = PlayerDesign
    sceneGraphics.children.add(playerDesign)
  }
  def PutX(gameX: Double, width: Double): Double = {
    (gameX - width / 2.0) * scaleFactor
  }
  def PutY(gameY: Double, height: Double): Double = {
    (game.Height - (gameY - game.Line) - height) * scaleFactor
  }
  def playersDesign(xLocation: Double, yLocation: Double, color: Color): Circle = {
    new Circle {
      translateX = PutX(xLocation, playerDesignSize)
      translateY = PutY(yLocation, playerDesignSize)
      radius = 10
      fill = color
    }
  }
  def Distance(v1: PhysicsVector, v2: PhysicsVector): Double = {
    Math.sqrt(Math.pow(v1.x - v2.x, 2.0) + Math.pow(v1.z - v2.z, 2.0))
  }
  this.stage = new PrimaryStage {
    this.title = "Epic Game"
    scene = new Scene(windowWidth, windowHeight) {
      content = List(sceneGraphics)
    }
  }
  val update: Long => Unit = (time: Long) => {
    val dt: Double = (time - lastUpdateTime) / 1000000000.0
    lastUpdateTime = time
    game.update(dt)

    playerDesign.translateX.value = PutX(game.player.location.x, playerDesignSize)
    playerDesign.translateY.value = PutY(game.player.location.y, playerDesignSize)
  }
}
