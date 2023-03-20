<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/WEB-INF/pages/tiles/localization.jsp" %>


<style>
   a {text-decoration: none; color:#800000}
   a:hover {text-decoration: underline; } 
   a:visited { text-decoration: none;color:#800000} 
</style>

<div class="menu-wrapper">
	<div class="menu-title-wrapper">
		<div class="menu-title">
		  <font color=#8B4513><c:out value="${news_menu}"></c:out></font>     
		</div>
	</div>

	<div class="list-menu-invisible-wrapper">
		<div class="list-menu-wrapper" style="float: right;">
		
			<ul style="list-style-image: url(images/newImg.jpg); text-align: left;">
			
				<li style="padding-left: 15px;">				
				<a href="controller?command=go_to_news_list">${all_news}</a><br />				
				</li>
				
				<li style="padding-left: 15px;">				
				<a href="controller?command=go_to_latest_news_list">${latest_news}</a><br />				
				</li>				

				<c:if test="${sessionScope.role eq 'admin'}">
				   <li style="padding-left: 15px;">
				
				    <a href="controller?command=go_to_add_news_page">${add_news}</a>
                
                   <br />
					
				</li></c:if>
				
			</ul>
		</div>
		<div class="clear"></div>
	</div>

	<div style="height: 25px;"></div>
</div>

