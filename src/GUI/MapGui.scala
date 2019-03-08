package GUI

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.shape.Circle
//this is the actual game gui
//this will be opened after the player hits a "create character" button on the character selection screen

object MapGui extends JFXApp{
  val player: Circle = new Circle {
    centerX = 20.0
    centerY = 50.0
    radius = 30.0

  }


  this.stage = new PrimaryStage {

  }

}
