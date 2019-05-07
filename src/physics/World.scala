package physics
import theGame.{PhysicalObject, Boundary}
class World(var gravity: Double) {

  var objects: List[PhysicalObject] = List()
  var boundaries: List[Boundary] = List()

}
