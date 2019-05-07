var socket = io.connect({transports: ['websocket']});
socket.on('gameState', parseGameState);

tileSize = 30;

var canvas = document.getElementById("canvas");
var context = canvas.getContext("2d");
context.globalCompositeOperation = 'source-over';

function parseGameState(event) {
    // console.log(event);
    const  gameState = JSON.parse(event);

    drawGameBoard(gameState['gridSize']);


    placeSquare(gameState['spawn']['x'], gameState['spawn']['y'], '#8c4fff');

    for (let blob of gameState['blobs']) {
        placeCircle(blob['x'], blob['y'], '#df20ff', 20.0);
    }
    for (let player of gameState['players']) {
        placeCircle(player['x'], player['y'], player['id'] === socket.id ? '#ff5400' : '#bfff4f', 200.0);
    }

}
function drawGameBoard(gridSize) {

    const gridWidth = gridSize['x'];
    const gridHeight = gridSize['y'];

    context.clearRect(0, 0, gridWidth * tileSize, gridHeight * tileSize);

    canvas.setAttribute("width", gridWidth * tileSize);
    canvas.setAttribute("height", gridHeight * tileSize);

    context.strokeStyle = '#184abb';
    for (var j = 0; j <= gridWidth; j++) {
        context.beginPath();
        context.moveTo(j * tileSize, 0);
        context.lineTo(j * tileSize, gridHeight * tileSize);
        context.stroke();
    }
    for (var k = 0; k <= gridHeight; k++) {
        context.beginPath();
        context.moveTo(0, k * tileSize);
        context.lineTo(gridWidth * tileSize, k * tileSize);
        context.stroke();
    }
}
function placeSquare(x, y, color) {
    context.fillStyle = color;
    context.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
    context.strokeStyle = 'black';
    context.strokeRect(x * tileSize, y * tileSize, tileSize, tileSize);
}
function placeCircle(x, y, color, size) {
    context.fillStyle = color;
    context.beginPath();
    context.arc(x * tileSize,
        y * tileSize,
        size / 10.0 * tileSize,
        0,
        2 * Math.PI);
    context.fill();
    context.strokeStyle = 'black';
    context.stroke();
}


