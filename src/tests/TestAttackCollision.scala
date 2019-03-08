package tests

import Enemies.Orc
import Player.Knight
import org.scalatest.FunSuite
import theGame.Game

class TestAttackCollision extends FunSuite{
  test("Check for collision with a boundary") {
    var game: Game = new Game
    var player1: Knight = new Knight
    player1.xLocation = 50
    player1.yLocation = 50
    var sword1 = player1.attackPrimary()
    var enemy1 = new Orc
    enemy1.xLocation = 50
    enemy1.yLocation = 80
    var player2: Knight = new Knight
    player2.xLocation = 50
    player2.yLocation = 50
    var sword2 = player2.attackPrimary()
    var enemy2 = new Orc
    enemy1.xLocation = 50
    enemy1.yLocation = 50
    assert(game.detectHit(enemy1, sword1))
    assert(!game.detectHit(enemy2, sword2))
  }
}
