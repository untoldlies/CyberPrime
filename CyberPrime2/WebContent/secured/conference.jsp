<%@page contentType="text/html" pageEncoding="UTF-8"%>
<title>Test Web Cam</title>
<style type="text/css">
#webcam, #canvas {
    width: 320px;
    border:20px solid #333;
    background:#eee;
    -webkit-border-radius: 20px;
    -moz-border-radius: 20px;
    border-radius: 20px;
}

#webcam {
    position:relative;
    margin-top:50px;
    margin-bottom:50px;
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
</head><body>
<p id="status" style="height:22px; color:#c00;font-weight:bold;"></p>
<div id="webcam">
    <span>Your WebCam should be here!</span>
</div>

<p style="width:360px;text-align:center; ">
    <a href="javascript:webcam.capture(3);changeFilter();void(0);">Take a picture after 3 seconds</a> |
    <a href="javascript:webcam.capture();changeFilter();void(0);">Take a picture instantly</a></p>

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
                                $.post("/TestWebCam/UploadServlet", {type: "data", image: canvas.toDataURL("image/png")});
                                pos = 0;
                        }
                };
        } else {
                saveCB = function(data) {
                        image.push(data);                        
                        pos+= 4 * 320;                        
                        if (pos >= 4 * 320 * 240) {
                                $.post("/TestWebCam/UploadServlet", {type: "pixel", image: image.join('|')});
                                pos = 0;
                        }
                };
        }
        $("#webcam").webcam({
                width: 320,
                height: 240,
                mode: "callback",
                swffile: "resources/jscam_canvas_only.swf",
                onSave: saveCB,
                onCapture: function () {
                        webcam.save();
                },
                debug: function (type, string) {
                        console.log(type + ": " + string);
                }
        });

});
</script>
</body>
</html>
<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" --%>
<%--     pageEncoding="ISO-8859-1"%> --%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<!-- <style type="text/css"> -->
/* body{ */
/* background-color:gray; */
/* width:775px; */
/* } */
/* #otherUsers */
/* { */
/* border:2px solid; */
/* height:400px; */
/* } */
/* #yourself { */
/* border:2px solid; */
/* height:150px; */
/* width:400px; */
/* } */
/* .navi { */
	
/* 	display:inline-block; */
/* 	background-color:white; */
/* 	border-radius:15px; */
/* 	font-family:"Lucida Calligraphy" Verdana; */
/* } */
/* } */
/* .naviwords:hover { */
/* 	color:white; */
/* } */
/* .navi:hover{ */
/* 	background-color:rgba(0,0,255,0.6); */
/* 	border-radius:15px; */
/* 	box-shadow:2px 2px 5px #3366CC; */
/* } */
/* .naviwords{ */
/* 	text-decoration:none; */
/* 	padding: 5px; */
/* 	color:black; */
/* } */
/* ul { */
/* position:relative; */
/* left:400px; */
/* bottom:100px; */
/* } */
<!-- </style> -->
<!-- </head> -->
<!-- <body> -->
<!-- <div id="otherUsers"> -->
<!-- <img src="../images/videoConferenceOthers.PNG" alt="Conference" height="400" width="800"> -->
<!-- </div> -->
<!-- <div id="yourself"> -->
<!-- <img src="../images/videoConferenceYourself.PNG" alt="Conference" height="150" width="400"> -->
<!-- </div> -->
<!-- <ul> -->
<!-- 					<li class="navi"><a class=naviwords href="">End Call</a> -->
	
<!-- 					</li> -->
<!-- 					<li class="navi"> -->
<!-- 						<form method="post" action="#"> -->
<!-- 						<input type="text" name="url" placeholder="Add user's ID here."> -->
<!-- 						<input type="submit" class=naviwords value="Add"> -->
<!-- 						</form> -->
<!-- 					</li> -->
					
<!-- </ul> -->

<!-- </body> -->
<!-- </html> -->