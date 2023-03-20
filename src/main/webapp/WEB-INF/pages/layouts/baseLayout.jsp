<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/WEB-INF/pages/tiles/localization.jsp" %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="script/validation.js"></script>
<title>locale.linkname.headertitle <!-- <bean:message key="locale.linkname.headertitle" /> -->
</title>

<style>
   a {text-decoration: none; color:#800000}
   a:hover {text-decoration: underline; } 
   a:visited { text-decoration: none;color:#800000} 
</style>

<link rel="stylesheet" type="text/css" href="styles/newsStyle.css">


</head>

<body bgcolor="F5FFFA">
	<div class="page">
	
	 	<div class="header">
			<c:import url="/WEB-INF/pages/tiles/header.jsp" />
		</div>

		<div class="base-layout-wrapper">
		 	<div class="menu">
		 	<div align=center>
				<c:if test="${not (sessionScope.user eq 'active')}">
				<br><font color=#8B4513 > ${welcome}</font>  
				</c:if>
			</div>
				<c:if test="${sessionScope.user eq 'active'}">
					<c:import url="/WEB-INF/pages/tiles/menu.jsp" />
				</c:if>
		</div>

		<div class="content">

				<c:if test="${not (sessionScope.user eq 'active')}">
					<c:import url="/WEB-INF/pages/tiles/guestDistributer.jsp" />
				</c:if>
				<c:if test="${sessionScope.user eq 'active'}">
					<c:import url="/WEB-INF/pages/tiles/body.jsp" />
				</c:if>


			</div>
		</div>

		<div class="footer">

			<c:import url="/WEB-INF/pages/tiles/footer.jsp" />
		</div>
	</div>
</body>
</html>