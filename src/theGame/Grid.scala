package theGame

object Grid{
  new Grid{
    blobLocations = List(new GridLocation(5,7),
      new GridLocation(7,9), new GridLocation(4,6),
      new GridLocation(6,10), new GridLocation(1,1), new GridLocation(3,6), new GridLocation(9, 18),
      new GridLocation(10, 20), new GridLocation(3, 5), new GridLocation(1,9), new GridLocation(2,4),
      new GridLocation(4,7), new GridLocation(12,13),
      new GridLocation(7,10), new GridLocation(8,9),
      new GridLocation(14,15), new GridLocation(6,2),
      new GridLocation(1,20), new GridLocation(2, 20),
      new GridLocation(4,2), new GridLocation(9,10)
    )
    Spawn = new GridLocation(0, 5)
  }
}
class Grid{
  var blobLocations: List[GridLocation] = List()
  var gridWidth : Int = 30
  var gridHeight: Int = 12
  var Spawn = new GridLocation(0, 5)
}
