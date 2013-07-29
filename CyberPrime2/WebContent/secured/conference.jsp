<%@page contentType="text/html" pageEncoding="UTF-8"%>
<title>Test Web Cam</title>
<style type="text/css">
#webcam, #canvas {
    width: 640px;
    border:20px solid #333;
    background:#eee;
    -webkit-border-radius: 20px;
    -moz-border-radius: 20px;
    border-radius: 20px;
}

#webcam {
    position:relative;
}

#webcam > span {
    z-index:2;
    position:absolute;
    color:#eee;
    font-size:10px;
    bottom: -16px;
    left:152px;
}

#canvas {    border:20px solid #ccc;
    background:#eee;
}
</style><script type="text/javascript" src="resources/jquery-1.js"></script>
<script type="text/javascript" src="resources/jquery.js"></script>

<!-- For telasocial labs -->
<title>Simple Camera Canvas Manipulation</title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<script type="text/javascript" src="camcanvas.js"></script>

</head><body onload="init()">

<button onclick="setFunction(passEmboss)">Emboss</button>
<button onclick="setFunction(passRed)">Red</button>
<button onclick="setFunction(passNormal)">Normal</button>
<button onclick="setFunction(passInverse)">Inversed</button>
<button onclick="setFunction(passGray)">Grayscale</button>
<p>
<video id="v" width="320" height="240"></video>
<canvas id="c" width="320" height="240"></canvas>
</p>
<!-- End of telasocial labs -->

<p id="status" style="height:22px; color:#c00;font-weight:bold;"></p>
<div id="webcam">
</div>

<p style="width:360px;text-align:center; ">|
    <a href="javascript:webcam.capture();changeFilter();void(0);">TEST</a></p>

<script type="text/javascript">
var pos = 0;
var ctx = null;
var cam = null;
var image = null;

var filter_on = false;
var filter_id = 0;

$(function() {

        var pos = 0, ctx = null, saveCB, image = [];
        var canvas = document.createElement("canvas");
        canvas.setAttribute('width', 320);
        canvas.setAttribute('height', 240);  
        if (canvas.toDataURL) {
                ctx = canvas.getContext("2d");              
                image = ctx.getImageData(0, 0, 320, 240);
                saveCB = function(data) {                        
                        var col = data.split(";");
                        var img = image;
                        for(var i = 0; i < 320; i++) {
                                var tmp = parseInt(col[i]);
                                img.data[pos + 0] = (tmp >> 16) & 0xff;
                                img.data[pos + 1] = (tmp >> 8) & 0xff;
                                img.data[pos + 2] = tmp & 0xff;
                                img.data[pos + 3] = 0xff;
                                pos+= 4;
                        }
                        if (pos >= 4 * 320 * 240) {
                                ctx.putImageData(img, 0, 0);
                                $.post("/servlets/UploadServlet", {type: "data", image: canvas.toDataURL("image/png")});
                                pos = 0;
                        }
                };
        } else {
                saveCB = function(data) {
                        image.push(data);                        
                        pos+= 4 * 320;                        
                        if (pos >= 4 * 320 * 240) {
                                $.post("/servlets/UploadServlet", {type: "pixel", image: image.join('|')});
                                pos = 0;
                        }
                };
        }
        $("#webcam").webcam({
                width: 320,
                height: 240,
                mode: "callback", //Same procedure as callback, 
                //onSave is called non-stop, webcam.save() has no effect
                swffile: "resources/jscam_canvas_only.swf",
                onSave: saveCB,
                onCapture: function () {
                        webcam.save();
                },
                debug: function (type, string) {
                        console.log(type + ": " + string);
                },
                
                onLoad: function() {}
                
        });

});
</script>
</body>
</html>