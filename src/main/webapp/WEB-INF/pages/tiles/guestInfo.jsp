<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/WEB-INF/pages/tiles/localization.jsp" %>

<style>
   a {text-decoration: none; color:#800000}
   a:hover {text-decoration: underline; } 
   a:visited { text-decoration: none;color:#800000} 
</style>

<font color=#8B4513>${guest_info}</font>

<div class="body-title">
	<a href="controller?command=go_to_news_list">${all_news}</a>>>
	<a href="controller?command=go_to_base_page">${latest_news}</a>
	
</div>

<form action="command?method=delete" method="post">
	<c:forEach var="news" items="${requestScope.news}">
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
				<div class="news-title">
					<c:out value="${news.title}" />
				</div>
				<div class="news-date">
					<c:out value="${news.newsDate}" />
				</div>

				<div class="news-content">
					<c:out value="${news.briefNews}" />
				</div>
			</div>
		</div>

	</c:forEach>

	<div class="no-news">
		<c:if test="${requestScope.news eq null}">
        ${no_news}
	</c:if>
	</div>

</form>
