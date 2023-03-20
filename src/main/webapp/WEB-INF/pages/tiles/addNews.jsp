<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/WEB-INF/pages/tiles/localization.jsp" %>

<div class="add-table-margin">
	<form action="controller" method="post">
		<table class="news_text_format">
		<tr>
			<td class="space_around_title_text">${title}</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
			<input type="text" name="news_title" value="${requestScope.news.title}"/>
			</div>
		</tr>
		
		<tr>
			<td class="space_around_title_text">${date}</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
			<input type="date" name="date_news" value="${requestScope.news.newsDate}"/>
			</div>
		</tr>
		
		<tr>
			<td class="space_around_title_text">${brief}</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
			<textarea name="brief_news" rows="3" cols="45" style="resize: none">${requestScope.news.briefNews}</textarea>
			</div></td>
		</tr>
		
		<tr>
			<td class="space_around_title_text">${content}</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
			<textarea name="content_news" rows="15" cols="57" style="resize: none">${requestScope.news.content}</textarea>
			</div></td>
		</tr>
		</table>
		
		<div class="save-button">
			<input type="hidden" name="command" value="do_add_news" /> 
			<input type="submit" value="${save_but}" />
		</div>
		
	</form>
		
		
	<div class="cancel-button">
	<form action="controller" method="get">
		<input type="hidden" name="command" value="go_to_news_list" />	
		<input type="submit" value="${back_but}" >
	</form>
	</div><br>
	<div style=color:red>
	<c:out value="${requestScope.messageNewsValidation}"/>
	</div>
	
</div>
