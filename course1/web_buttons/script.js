function changeColor(){
  var confirmMessage = "Do you want to change the first div to blue and the second div to yellow?";
  var message = "Ok, no change this time!";
  if (confirm(confirmMessage)){
    var dd1 = document.getElementById('div1');
    var dd2 = document.getElementById('div2');

    dd1.className = "blueback";
    dd2.className = "yellowback";
    message = "Ok, the divs colors changed!";
  }
  alert(message);
}

function changeText(){
  var confirmMessage = "Do you want to change text of the first div to 'Bonjour' and of the second div to 'Sayonara'?";
  var message = "Ok, no change this time!";
  if (confirm(confirmMessage)){
    var dd1 = document.getElementById('div1');
    var dd2 = document.getElementById('div2');

    dd1.innerHTML = "Bonjour";
    dd1.style.color = "white";
    dd2.innerHTML = "Sayonara";
    dd2.style.color = "blue";
    message = "Ok, the divs text changed!";
  }
  alert(message);
}
