<!DOCTYPE html>
<html lang="en">
<head>
<!--
"Time-stamp: <Sun, 02-19-17, 12:24:47 Eastern Standard Time>"
//-->
<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="My online portfolio that illustrates skills acquired while working through various project requirements.">
	<meta name="author" content="Juan D Carballo Sanchez">
	<link rel="icon" href="../favicon.ico">

	<title>LIS4368 - Assignment1</title>

	<%@ include file="/css/include_css.jsp" %>

</head>
<body>

<!-- display application path -->
<% //= request.getContextPath()%>

<!-- can also find path like this...<a href="${pageContext.request.contextPath}${'/a5/index.jsp'}">A5</a> -->

	<%@ include file="/global/nav.jsp" %>

	<div class="container">
		<div class="starter-template">
			<div class="row">
				<div class="col-sm-8 col-sm-offset-2">

					<div class="page-header">
						<%@ include file="global/header.jsp" %>
					</div>

					<b>JDK Installation:</b><br />
					<img src="img/hello_java.png" class="img-responsive" alt="JDK Installation" />

					<br /> <br />
					<b>Tomcat Installation:</b><br />
					<img src="img/tomcat.png" class="img-responsive" alt="Tomcat Installation" />
				</div>
			</div>

	<%@ include file="/global/footer.jsp" %>

	</div> <!-- end starter-template -->
 </div> <!-- end container -->

 	<%@ include file="/js/include_js.jsp" %>

</body>
</html>
