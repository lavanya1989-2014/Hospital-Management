<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="/Hospital-Management/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
  <link href="/Hospital-Management/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" type="text/css" href="/HospitalManagement/resources/plugins/cubeportfolio/css/cubeportfolio.min.css">
  <link href="/Hospital-Management/resources/css/nivo-lightbox.css" rel="stylesheet" />
  <link href="/Hospital-Management/resources/css/nivo-lightbox-theme/default/default.css" rel="stylesheet" type="text/css" />
  <link href="/Hospital-Management/resources/css/owl.carousel.css" rel="stylesheet" media="screen" />
  <link href="/Hospital-Management/resources/css/owl.theme.css" rel="stylesheet" media="screen" />
  <link href="/Hospital-Management/resources/css/animate.css" rel="stylesheet" />
  <link href="/Hospital-Management/resources/css/style.css" rel="stylesheet">
    <link href="/Hospital-Management/resources/css/home.css" rel="stylesheet">

  <!-- boxed bg -->
  <link id="bodybg" href="/Hospital-Management/bodybg/bg1.css" rel="stylesheet" type="text/css" />
  <!-- template skin -->
  <link id="t-colors" href="/Hospital-Management/resources/color/default.css" rel="stylesheet">
  
  
  <script src="/Hospital-Management/resources/js/jquery.min.js"></script>
  <script src="/Hospital-Management/resources/js/bootstrap.min.js"></script>
  <script src="/Hospital-Management/resources/js/jquery.easing.min.js"></script>
  <script src="/Hospital-Management/resources/js/wow.min.js"></script>
  <script src="/Hospital-Management/resources/js/jquery.scrollTo.js"></script>
  <script src="/Hospital-Management/resources/js/jquery.appear.js"></script>
  <script src="/Hospital-Management/resources/js/stellar.js"></script>
  <script src="/Hospital-Management/resources/plugins/cubeportfolio/js/jquery.cubeportfolio.min.js"></script>
  <script src="/Hospital-Management/resources/js/owl.carousel.min.js"></script>
  <script src="/Hospital-Management/resources/js/nivo-lightbox.min.js"></script>
  <script src="/Hospital-Management/resources/js/custom.js"></script>
  <script src="/Hospital-Management/resources/contactform/contactform.js"></script>


<script 
type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.0/jquery.min.js">
</script>


<script language="javascript">
$(function(){
	$("#selectall").click(function () {
		  $('.case').attr('checked', this.checked);
	});
	$(".case").click(function(){

		if($(".case").length == $(".case:checked").length) {
			$("#selectall").attr("checked", "checked");
		} else {
			$("#selectall").removeAttr("checked");
		}

	});
});

</script>

</head>


<body  >


	<tiles:insertAttribute name="header"></tiles:insertAttribute>

	<div id="page">

		<div id="body" style="margin-left: 0">


			<tiles:insertAttribute name="body"></tiles:insertAttribute>

		</div>

	</div>
	
<tiles:insertAttribute name="footer"></tiles:insertAttribute>

</body>
</html>