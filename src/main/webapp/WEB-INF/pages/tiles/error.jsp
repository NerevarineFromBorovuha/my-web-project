<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
<div align="center">
<b >
Error page 
</b>
	<br><br>

	<c:if test="${not (sessionScope.errorMessage eq null)}">
		<font color=red>
		<c:out value="${sessionScope.errorMessage}" />
		</font>
	</c:if>
	<br>
	<br>
	
	<form action="controller" method="post">
		<input type="hidden" name="command" value="do_sign_out" />		 
		 <input type="submit" value="Back to main page" /><br />
	</form>
</div>
</body>

</html>




