<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/WEB-INF/pages/tiles/localization.jsp" %>
<head>
<link rel="stylesheet" type="text/css" href="styles/registrationStyle.css">
</head>

<div class="wrapper">
	<div class="newstitle">${header_name} <br>
	<font size=3>${header_name_add}</font>
	
	</div>
	<div class="local-link">
		<div align="right">
			<a href="controller?command=do_change_locale&local=en"><font color=#800000> ${en_local}</font> </a> &nbsp;&nbsp; 
			<a href="controller?command=do_change_locale&local=ru"><font color=#800000> ${ru_local}</font> </a> <br /> <br />
		</div>

		<c:if test="${not (sessionScope.user eq 'active')}">

			<div align="right">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="do_sign_in" /> 
					${login_text} 
					<input type="text" name="login" value="" /><br /> 
					${password_text}
					 <input type="password" name="password" value="" /><br />

					<c:if test="${not (requestScope.AuthenticationError eq null)}">
						<font color="red"> 
						   <c:out value="${authentication_error}" />
						</font> 
					</c:if>
					<c:if test="${not (sessionScope.RegistrationStatusGood eq null)}">
						<font color=#006400> 
						   <c:out value="${registration_success}" />
						   <c:remove var="RegistrationStatusGood"/>
						</font> 
					</c:if>
					
					<button type="submit"  formaction="controller?command=go_to_registration_page">${registration_but}</button>
						
					<input type="submit" value="${logination_but}"  /><br />
				</form>
			</div>	
		</c:if>
		
		
		<c:if test="${sessionScope.user eq 'active'}">
		
		

			<div align="right">
			 
				<form action="controller" method="post">
				<c:if test="${not (requestScope.DeleteNewsStatus eq null)}">
						<font color="red"> 
						   <c:out value="${delete_unsuccess}" />
						</font> 
					</c:if>
				
					<input type="hidden" name="command" value="do_sign_out" /> 
					<input type="submit" value="${sign_out_but}" /><br />
				</form>
				
			</div>

		</c:if>
	</div>

</div>

