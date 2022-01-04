var fgImage = null;
var bgImage = null;
var greenThreshold = 250;

function uploadForegroundImage(){
  var imgFile = document.getElementById('fgfile');
  fgImage = new SimpleImage(imgFile);
  var canvas = document.getElementById('fgCanvas');
  fgImage.drawTo(canvas);
}

function uploadBackgroundImage(){
  var imgFile = document.getElementById('bgfile');
  bgImage = new SimpleImage(imgFile);
  var canvas = document.getElementById('bgCanvas');
  bgImage.drawTo(canvas);
}

function replaceBackground(){
  var outputCanvas = document.getElementById('resultCanvas');
  if(fgImage == null || ! fgImage.complete()){
    alert("Foreground not loaded");
    return;
  }
  if (bgImage == null || ! bgImage.complete()){
    alert("Background image not loaded");
    return;
  }
  //clearCanvas();

  var output = new SimpleImage(fgImage.getWidth(),
                               fgImage.getHeight());
  for (var pixel of fgImage.values()){
    var x = pixel.getX();
    var y = pixel.getY();
    if (pixel.getGreen() > greenThreshold){
      var bgPixel = bgImage.getPixel(x, y);
      output.setPixel(x, y, bgPixel);
    }
    else {
      output.setPixel(x, y, pixel);
    }
  }
  output.drawTo(outputCanvas);
}
function clearCanvas(){
  var canvasElt1 = document.getElementById('fgCanvas');
  var canvasElt2 = document.getElementById('bgCanvas');
  var canvasElt3 = document.getElementById('resultCanvas');
  var context1 = canvasElt1.getContext("2d");
  var context2 = canvasElt2.getContext("2d");
  var context3 = canvasElt3.getContext("2d");
  context1.clearRect(0, 0, canvasElt1.width, canvasElt1.height);
  context2.clearRect(0, 0, canvasElt2.width, canvasElt2.height);
  context3.clearRect(0, 0, canvasElt3.width, canvasElt3.height);
  var fgImgFile = document.getElementById('fgfile');
  var bgImgFile = document.getElementById('bgfile');
  

  fgImgFile.value = "";
  bgImgFile.value = "";
  //alert("Hi");
}
