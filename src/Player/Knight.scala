package Player

import Player.Attacks._

class Knight extends Classes {
      override var HP: Int = 500
      override var speed: Int = 5
      override var xLocation: Double = 40
      override var yLocation: Double = 40

  override def attackPrimary(): Attack = {
      val mySword = new Sword
      mySword.yLocation = xLocation + xLocation/2 + mySword.width/2
      mySword.xLocation = yLocation
    mySword
  }
}
