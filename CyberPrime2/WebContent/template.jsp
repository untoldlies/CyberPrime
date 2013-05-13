<!DOCTYPE HTML>
<html>

<head>
  <title>template</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="css/style.css" />
  <!-- modernizr enables HTML5 elements and feature detects -->
  <script type="text/javascript" src="js/modernizr-1.5.min.js"></script>
</head>

<body>
  <div id="main">
    <header>
      <div id="logo">
        <div id="logo_text">
          <!-- class="logo_colour", allows you to change the colour of the text -->
          <h1><a href="index.html">cyber<span class="logo_colour">_Prime</span></a></h1>
          <h2>Secure, anonymous collaboration.</h2>
        </div>
      </div>
      <nav>
        <div id="menu_container">
          <ul class="sf-menu" id="nav">
            <li><a href="template.jsp">Home</a></li>
            <li><a href="templateFAQ.jsp">FAQ</a></li>
            <li><a href="templateRegister.jsp">Register</a></li>
			<li><a href="templateLogin.jsp">Login</a></li>
            <li><a href="#">Services</a>
              <ul>
                <li><a href="#">Chat</a></li>
                <li><a href="#">File Transfer</a></li>
                <li><a href="#">Video Streaming</a></li>
                <li><a href="#">Video Chat</a></li>
              </ul>
            </li>
           <li><a href="templateAbout.jsp">About Us</a></li>
          </ul>
        </div>
      </nav>
    </header>
    <div id="site_content">
      <div id="sidebar_container">
        <div class="sidebar">
          <h3>Introduction</h3>
          <h4>Welcome!</h4>
          <h5>May 9th, 2013</h5>
          <p>Welcome to Cyber Prime. If it's your first time here, please register with us to use our services.
		  Otherwise, please login.</p>
        </div>
        <div class="sidebar">
          <h3>Logo</h3>
          <p><img src="images/cplogosmall.png"></p>
        </div>
      </div>
      <div class="content">
        <h1>Welcome to the scenic_photo template</h1>
        <p>This simple, fixed width website template is released under a <a href="http://creativecommons.org/licenses/by/3.0">Creative Commons Attribution 3.0 Licence</a>. This means you are free to download and use it for personal and commercial projects. However, you <strong>must leave the 'design from css3templates.co.uk' link in the footer of the template</strong>.</p>
        <p>This template is written entirely in <strong>HTML5</strong> and <strong>CSS3</strong>. The photo used in the design was taken by me.</p>
        <p>You can view more free CSS3 web templates <a href="http://www.css3templates.co.uk">here</a>.</p>
        <p>This template is a fully documented 5 page website, with an <a href="examples.html">examples</a> page that gives examples of all the styles available with this design. There is also a working PHP contact form on the contact page.</p>
      </div>
    </div>
    <footer>
      <p>Cyber Prime | Created May 2013 | Secure, anonymous collaboration.</a></p>
    </footer>
  </div>
  <p>&nbsp;</p>
  <!-- javascript at the bottom for fast page loading -->
  <script type="text/javascript" src="js/jquery.js"></script>
  <script type="text/javascript" src="js/jquery.easing-sooper.js"></script>
  <script type="text/javascript" src="js/jquery.sooperfish.js"></script>
  <script type="text/javascript">
    $(document).ready(function() {
      $('ul.sf-menu').sooperfish();
    });
  </script>
</body>
</html>
