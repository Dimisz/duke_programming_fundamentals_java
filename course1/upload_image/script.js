var image;

function upload(){
  var imgcanvas = document.getElementById('can');
  var fileinput = document.getElementById('finput');
  image = new SimpleImage(fileinput);
  image.drawTo(imgcanvas);
}

function makeGray(){
  for (var pixel of image.values()){
    var avg = (pixel.getRed()+pixel.getGreen() + pixel.getBlue())/3;
    pixel.setRed(avg);
    pixel.setGreen(avg);
    pixel.setBlue(avg);
  }
  var imgcanvas = document.getElementById('can');
  image.drawTo(imgcanvas);
}

function clearCanvas(){
  var canvasElt = document.getElementById('can');
  var context = canvasElt.getContext("2d");
  context.clearRect(0, 0, canvasElt.width, canvasElt.height);
  var ImgFile = document.getElementById('finput');
  ImgFile.value = "";

  //alert("Hi");
}
