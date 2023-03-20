<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/WEB-INF/pages/tiles/localization.jsp" %>

<head>
<link rel="stylesheet" type="text/css" href="styles/registrationStyle.css">
</head>
<div class="color_header">
	<c:if test="${not (requestScope.RegistrationStatusBad eq null)}">
	<font color=red>
		${registration_unsuccess}<br>
	</font>
	</c:if>	
	<font color=red>
	${requestScope.messageUserRegistration}	
	</font>
</div>


	<form action="controller" method="post">
	
		<input type="hidden" name="command" value="do_registration" />
		
		<table class="news_text_format_registration">
	<tr>
			<td class="space_around_title_text_registration">${login}</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
			<input type="text" name="login" placeholder="from 6 to 15 symbols" value="${requestScope.newUser.login}" required />
			</div>
	</tr>
	
	<tr>
			<td class="space_around_title_text_registration">${password}</td>
			<td class="space_around_view_text_registration">
			<div class="word-breaker">
			<input type="password" name="password" placeholder="from 6 to 15 symbols" value="" required/>
			</div>
	</tr>
	
	<tr>
			<td class="space_around_title_text_registration">${email}</td>
			<td class="space_around_view_text_registration">
			<div class="word-breaker">
			<input type="email" name="email" value="${requestScope.newUser.email}" required/>
			</div>
	</tr>
	
		<tr>
			<td class="space_around_title_text_registration">${name}</td>
			<td class="space_around_view_text_registration">
			<div class="word-breaker">
			<input type="text" name="name" value="${requestScope.newUser.name}"  required/>
			</div>
	</tr>
	
	<tr>
			<td class="space_around_title_text_registration">${surname}</td>
			<td class="space_around_view_text_registration">
			<div class="word-breaker">
			<input type="text" name="surname" value="${requestScope.newUser.surname}" required />
			</div>
	</tr>
	
	<tr>
			<td class="space_around_title_text_registration">${birthday}</td>
			<td class="space_around_view_text_registration">
			<div class="word-breaker">
			<input type="date" name="birthday" value="${requestScope.newUser.birthday}" required/>
			</div>
	</tr>
	
	<tr>
			<td class="space_around_title_text_registration">${gender}</td>
			<td class="space_around_view_text_registration">
			<div class="word-breaker">
			<input type="radio" name="gender" value="male" required/> ${gender_male}
			<input type="radio" name="gender" value="female" required/>${gender_female}
			<input type="radio" name="gender" value="transformer" required />${gender_transformer}
			</div>
	</tr>
	
	<tr>
			<td class="space_around_title_text_registration"></td>
			<td class="space_around_view_text_registration">
			<div class="word-breaker">
			<input type="reset" value="${reset_but}" />
			<input type="submit" value="${sign_up_but}" />
			</div>
	</tr>
	
</table>
  	
	</form>



<div style="margin-left:356px">
	<form action="controller" method="get">
		<input type="hidden" name="command" value="go_to_base_page" />
		<input type="submit" value="${back_but}" >
	</form>
</div>



