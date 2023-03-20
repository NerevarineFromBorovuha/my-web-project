<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/WEB-INF/pages/tiles/localization.jsp" %>


<div class="body-title">
	<a href="controller?command=go_to_news_list">${all_news}</a>>>
	<a href="controller?command=go_to_latest_news_list">${latest_news}</a>
</div>

<form action="controller" method="post">
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
				
				<div class="news-link-to-wrapper">
					<div class="link-position">
						<c:if test="${sessionScope.role eq 'admin'}">
						      <a href="controller?command=go_to_edit_news&id=${news.idNews}">${edit_link}</a> 
						</c:if>
						
						<a href="controller?command=go_to_view_news&id=${news.idNews}">${view_link}</a> 
   					    
   					    <c:if test="${sessionScope.role eq 'admin'}">
   					         <input type="checkbox" name="id" value="${news.idNews}" />
   					    </c:if>
					</div>
				</div>

			</div>
		</div>

	</c:forEach>
	
	<c:if test="${ not (requestScope.news eq null)}">	
		<c:if test="${sessionScope.role eq 'admin'}">
			<div class="first-view-button">
				<input type="hidden" name="command" value="do_delete_news" /> 
				<input type="submit" value="${delete}" />
	    	</div>
		</c:if>	
	</c:if>	
	   	 	
	<c:if test="${requestScope.news eq null}">
		<div class="no-news">		
        ${no_news}		
		</div>
	</c:if>
</form>
