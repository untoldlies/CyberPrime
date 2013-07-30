var requestAnimationFrame = window.requestAnimationFrame || window.mozRequestAnimationFrame || window.webkitRequestAnimationFrame || window.msRequestAnimationFrame;
window.requestAnimationFrame = requestAnimationFrame;

var v, canvas, gCtx, pixelOperationFunction=encrypt;
var backBuffer = document.createElement('canvas');
var bCtx = backBuffer.getContext('2d');

function setFunction (fName) { 
	pixelOperationFunction=fName;
} 

function init() {
  v = document.getElementById('v');
  canvas = document.getElementById('c');
  gCtx = canvas.getContext('2d');
  navigator.webkitGetUserMedia({video:true}, callbackStreamIsReady);
}

function callbackStreamIsReady(stream) {
  v.src = URL.createObjectURL(stream);
  v.play();
  window.requestAnimationFrame(draw);
}

function draw() {
  var w = canvas.clientWidth;
  var h = canvas.clientHeight;
  backBuffer.width = w;
  backBuffer.height = h;
  bCtx.drawImage(v, 0, 0, w, h);
  pixelOperationFunction(w,h);
  window.requestAnimationFrame(draw);
}






	

				


