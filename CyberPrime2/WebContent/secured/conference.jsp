
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<title>Test Web Cam</title>
<style type="text/css">

</style>
<!--  For loading scriptcam  -->
<script language="JavaScript" src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script language="JavaScript" src="//ajax.googleapis.com/ajax/libs/swfobject/2.2/swfobject.js"></script>
<script language="JavaScript" src="resources/scriptcam.js"></script>
<script type="text/javascript">

<!-- Initialising Webcam -->
$(document).ready(function() {
	$("#webcam").scriptcam({ // insert ScriptCam on a div element having the id 'webcam'
		
		onError: oopsError
		promptWillShow: showMessage //To warn users to ignore warning message
// 		width:800, //Set dimensions to fit in iframe
// 		height:600
		onWebcamReady: webcamFound //Lists webcam and microphones
	}); 
});

function oopsError(errorId,errorMsg) {
	    alert(errorMsg);
	}

function webcamFound(cameraNames,camera,microphoneNames,microphone,volume) { //array of all webcam names, the index of the current webcam, an array of all microphone names, the index of the current microphone and the current microphone volume.
	$.each(cameraNames, function(index, text) {
		$('#cameraNames').append( $('<option></option>').val(index).html(text) )
	}); 
	$('#cameraNames').val(camera);
	$.each(microphoneNames, function(index, text) {
		$('#microphoneNames').append( $('<option></option>').val(index).html(text) )
	}); 
	$('#microphoneNames').val(microphone);
}

function showMessage() {
	    alert('A security dialog will be shown. Please click ACCEPT.');
	}

</script>
</head>
<body>
<div id="webcam">
    <span>Your WebCam should be here!</span>
</div>

<!-- Dropdown list to change webcam and microphone -->
<select id="cameraNames" size="1" onChange="$.scriptcam.changeCamera($('#cameraNames').val());"></select>
<select id="microphoneNames" size="1"onChange="$.scriptcam.changeMicrophone($('#microphoneNames').val());"></select>



</body>
</html>


<%-- <%@page contentType="text/html" pageEncoding="UTF-8"%> --%>
<!-- <title>Test Web Cam</title> -->
<!-- <style type="text/css"> -->
<!--  #webcam, #canvas {  -->
<!--      width: 320px;  -->
<!--      border:20px solid #333;  -->
<!--      background:#eee;  -->
<!--      -webkit-border-radius: 20px;  -->
<!--     -moz-border-radius: 20px;  -->
<!--      border-radius: 20px;  -->
<!--  }  -->

<!--  #webcam {  -->
<!--      position:relative;  -->
<!--      margin-top:50px;  -->
<!--      margin-bottom:50px;  -->
<!--  }  -->

<!--  #webcam > span {  -->
<!--      z-index:2;  -->
<!--      position:absolute;  -->
<!--      color:#eee;  -->
<!--     font-size:10px;  -->
<!--      bottom: -16px;  -->
<!--      left:152px; -->
<!--  }  -->

<!--  #canvas {    border:20px solid #ccc;  -->
<!--      background:#eee;  -->
<!--  }  -->
<!-- </style><script type="text/javascript" src="resources/jquery-1.js"></script> -->
<!-- <script type="text/javascript" src="resources/jquery.js"></script> -->
<!-- </head><body> -->
<!-- <p id="status" style="height:22px; color:#c00;font-weight:bold;"></p> -->
<!-- <div id="webcam"> -->
<!--     <span>Your WebCam should be here!</span> -->
<!-- </div> -->

<!-- <script type="text/javascript"> -->
<!--  var pos = 0; -->
<!--  var ctx = null; -->
<!--  var cam = null; -->
<!--  var image = null; -->

<!--  var filter_on = false; -->
<!--  var filter_id = 0; -->

<!--  $(function() { -->

<!--          var pos = 0, ctx = null, saveCB, image = []; -->
<!--          var canvas = document.createElement("canvas"); -->
<!--          canvas.setAttribute('width', 320); -->
<!--          canvas.setAttribute('height', 240);   -->
<!--          if (canvas.toDataURL) { -->
<!--                  ctx = canvas.getContext("2d");               -->
<!--                  image = ctx.getImageData(0, 0, 320, 240); -->
<!--                  saveCB = function(data) {                         -->
<!--                          var col = data.split(";"); -->
<!--                          var img = image; -->
<!--                          for(var i = 0; i < 320; i++) { -->
<!--                                  var tmp = parseInt(col[i]); -->
<!--                                  img.data[pos + 0] = (tmp >> 16) & 0xff; -->
<!--                                  img.data[pos + 1] = (tmp >> 8) & 0xff; -->
<!--                                  img.data[pos + 2] = tmp & 0xff; -->
<!--                                  img.data[pos + 3] = 0xff; -->
<!--                                  pos+= 4; -->
<!--                          } -->
<!--                          if (pos >= 4 * 320 * 240) { -->
<!--                                  ctx.putImageData(img, 0, 0); -->
<!--                                  $.post("/TestWebCam/UploadServlet", {type: "data", image: canvas.toDataURL("image/png")}); -->
<!--                                  pos = 0; -->
<!--                          } -->
<!--                  }; -->
<!--          } else { -->
<!--                 saveCB = function(data) { -->
<!--                          image.push(data);                         -->
<!--                          pos+= 4 * 320;                         -->
<!--                          if (pos >= 4 * 320 * 240) { -->
<!--                                  $.post("/TestWebCam/UploadServlet", {type: "pixel", image: image.join('|')}); -->
<!--                                  pos = 0; -->
<!--                          } -->
<!--                  }; -->
<!--          } -->
<!--          $("#webcam").webcam({ -->
<!--                  width: 320, -->
<!--                  height: 240, -->
<!--                  mode: "callback", -->
<!--                  swffile: "resources/jscam_canvas_only.swf", -->
<!--                  onSave: saveCB, -->
<!--                  onCapture: function () { -->
<!--                          webcam.save(); -->
<!--                  }, -->
<!--                  debug: function (type, string) { -->
<!--                          console.log(type + ": " + string); -->
<!--                  } -->
<!--          }); -->

<!--  }); -->
