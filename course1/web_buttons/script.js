function sayHi(){
  var message = "Are you sure you want to cancel? Sure sure???";
  if (confirm('Hi there! a new popup!')){
    message = "You pressed OK!";
  }
  alert(message);
}
