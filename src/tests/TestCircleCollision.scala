package tests
import Player.Knight
import org.scalatest._
import Enemies._
import theGame.Game

class TestCircleCollision extends FunSuite {
  test("Check for collision with a boundary") {
    val game = new Game
    val player1: Knight = new Knight
    val enemy1: Orc = new Orc
    player1.xLocation = 30
    player1.yLocation = 30
    enemy1.xLocation = 30
    enemy1.yLocation = 30
    val player2: Knight = new Knight
    val enemy2: Orc = new Orc
    player2.xLocation = 30
    player2.yLocation = 30
    enemy2.xLocation = 150
    enemy2.yLocation = 150
    val player3: Knight = new Knight
    val enemy3: Orc = new Orc
    player3.xLocation = 50
    player3.yLocation = 30
    enemy3.xLocation = 100
    enemy3.yLocation = 150

    assert(game.detectCollision(player1,enemy1))
    assert(!game.detectCollision(player2,enemy2))
    assert(game.detectCollision(player3,enemy3))



  }
}
