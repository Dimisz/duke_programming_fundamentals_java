// function changeColor(){
//   var canvasElt1 = document.getElementById('canvas1');
//   var canvasElt2 = document.getElementById('canvas2');
//
//   canvasElt1.className = "blueback";
//   canvasElt2.className = "orangeback";
// }

function doRed(){
  var canvasElt1 = document.getElementById('canvas1');
  var canvasElt2 = document.getElementById('canvas2');

  canvasElt1.style.backgroundColor = "red";
  canvasElt2.style.backgroundColor = "red";

  var context1 = canvasElt1.getContext("2d");
  var context2 = canvasElt2.getContext("2d");



  context1.fillStyle = "yellow";
  context1.fillRect(10, 10, 60, 60);
  context1.fillRect(80, 10, 60, 60);

  context1.fillStyle = "black";
  context1.font = "20px Arial";
  context1.fillText("Hello", 15, 45);
  context1.fillText("There", 85, 45);
}

function doGreen(){
  var canvasElt1 = document.getElementById('canvas1');
  var canvasElt2 = document.getElementById('canvas2');

  canvasElt1.style.backgroundColor = "green";
  canvasElt2.style.backgroundColor = "green";

  var context1 = canvasElt1.getContext("2d");
  var context2 = canvasElt2.getContext("2d");

  context2.beginPath();
  context2.fillStyle = "yellow";
  context2.fillRect(10, 10, 60, 60);
  context2.fillRect(80, 10, 60, 60);

  context2.fillStyle = "black";
  context2.font = "20px Arial";
  context2.fillText("Hello", 15, 45);
  context2.fillText("Here", 85, 45);

}

// function clear(){
//   var canvasElt1 = document.getElementById('canvas1');
//   var canvasElt2 = document.getElementById('canvas2');
//   var context1 = canvasElt1.getContext("2d");
//   var context2 = canvasElt2.getContext("2d");
//   context1.clearRect(0, 0, canvas.width, canvas.height);
//   context2.clearRect(0, 0, canvas.width, canvas.height);
// }
