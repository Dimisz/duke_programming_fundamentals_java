function makeYellow(){
  var canvas = document.getElementById('canvas1');
  canvas.style.backgroundColor = "yellow";
}

function changeColor(){
  var canvas = document.getElementById('canvas1');
  var colorInput = document.getElementById('clr1');

  var color = colorInput.value;
  canvas.style.backgroundColor = color;
}

function makeGreen(){
  var canvas = document.getElementById('cn');
  canvas.style.backgroundColor = "green";
}

function changeColor2(){
  var canvas = document.getElementById('cn');
  var colorInput = document.getElementById('clr2');

  var color = colorInput.value;
  canvas.style.backgroundColor = color;
}

function drawSquare(){
  var cn = document.getElementById('cn');
  var sizeInput = document.getElementById('sldr');
  var size = sizeInput.value;
  var ctx = cn.getContext("2d");
  ctx.clearRect(0, 0, cn.width, cn.height);
  ctx.fillStyle = "yellow";
  ctx.fillRect(10, 10, size, size);
}
