<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="jakarta.servlet.jsp.jstl.fmt.LocalizationContext" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />


<%-- header --%>
<fmt:message bundle="${loc}" key="local.header.text.login" var="login_text"/>
<fmt:message bundle="${loc}" key="local.header.text.password" var="password_text"/>
<fmt:message bundle="${loc}" key="local.header.text.header_name" var="header_name"/>
<fmt:message bundle="${loc}" key="local.header.text.header_name_add" var="header_name_add"/>
<fmt:message bundle="${loc}" key="local.header.button.logination" var="logination_but"/>
<fmt:message bundle="${loc}" key="local.header.button.registration" var="registration_but"/>
<fmt:message bundle="${loc}" key="local.header.button.en_local" var="en_local"/>
<fmt:message bundle="${loc}" key="local.header.button.ru_local" var="ru_local"/>
<fmt:message bundle="${loc}" key="local.header.button.sign_out" var="sign_out_but"/>

<%-- menu --%>
<fmt:message bundle="${loc}" key="local.menu.text.news" var="news_menu"/>
<fmt:message bundle="${loc}" key="local.menu.button.all_news" var="all_news"/>
<fmt:message bundle="${loc}" key="local.menu.button.latest_news" var="latest_news"/>
<fmt:message bundle="${loc}" key="local.menu.button.add_news" var="add_news"/>
<fmt:message bundle="${loc}" key="local.menu.text.welcome" var="welcome"/>

<%-- registration --%>
<fmt:message bundle="${loc}" key="local.body.registration.text.login" var="login"/>
<fmt:message bundle="${loc}" key="local.body.registration.text.password" var="password"/>
<fmt:message bundle="${loc}" key="local.body.registration.text.email" var="email"/>
<fmt:message bundle="${loc}" key="local.body.registration.text.name" var="name"/>
<fmt:message bundle="${loc}" key="local.body.registration.text.surname" var="surname"/>
<fmt:message bundle="${loc}" key="local.body.registration.text.birthday" var="birthday"/>
<fmt:message bundle="${loc}" key="local.body.registration.text.gender" var="gender"/>
<fmt:message bundle="${loc}" key="local.body.registration.text.gender.male" var="gender_male"/>
<fmt:message bundle="${loc}" key="local.body.registration.text.gender.female" var="gender_female"/>
<fmt:message bundle="${loc}" key="local.body.registration.text.gender.transformer" var="gender_transformer"/>
<fmt:message bundle="${loc}" key="local.body.registration.button.reset" var="reset_but"/>
<fmt:message bundle="${loc}" key="local.body.registration.button.sign_up" var="sign_up_but"/>
<fmt:message bundle="${loc}" key="local.body.registration.button.back" var="back_but"/>

<%-- add news page --%>
<fmt:message bundle="${loc}" key="local.body.addnews.text.title" var="title"/>
<fmt:message bundle="${loc}" key="local.body.addnews.text.date" var="date"/>
<fmt:message bundle="${loc}" key="local.body.addnews.text.brief" var="brief"/>
<fmt:message bundle="${loc}" key="local.body.addnews.text.content" var="content"/>
<fmt:message bundle="${loc}" key="local.body.addnews.button.save" var="save_but"/>
<fmt:message bundle="${loc}" key="local.body.addnews.button.back" var="back_but"/>

<%-- edit news page --%>
<fmt:message bundle="${loc}" key="local.body.editnews.text.title" var="title"/>
<fmt:message bundle="${loc}" key="local.body.editnews.text.date" var="date"/>
<fmt:message bundle="${loc}" key="local.body.editnews.text.brief" var="brief"/>
<fmt:message bundle="${loc}" key="local.body.editnews.text.content" var="content"/>
<fmt:message bundle="${loc}" key="local.body.editnews.button.save" var="save_but"/>
<fmt:message bundle="${loc}" key="local.body.editnews.button.back" var="back_but"/>

<%-- view news page --%>
<fmt:message bundle="${loc}" key="local.body.viewnews.text.title" var="title"/>
<fmt:message bundle="${loc}" key="local.body.viewnews.text.date" var="date"/>
<fmt:message bundle="${loc}" key="local.body.viewnews.text.brief" var="brief"/>
<fmt:message bundle="${loc}" key="local.body.viewnews.text.content" var="content"/>
<fmt:message bundle="${loc}" key="local.body.viewnews.button.edit" var="edit_but"/>
<fmt:message bundle="${loc}" key="local.body.viewnews.button.delete" var="delete_but"/>

<%-- common --%>
<fmt:message bundle="${loc}" key="local.body.listnews.text.all_news" var="all_news"/>
<fmt:message bundle="${loc}" key="local.body.listnews.text.latest_news" var="latest_news"/>
<fmt:message bundle="${loc}" key="local.body.listnews.text.edit_link" var="edit_link"/>
<fmt:message bundle="${loc}" key="local.body.listnews.text.view_link" var="view_link"/>
<fmt:message bundle="${loc}" key="local.body.listnews.button.delete" var="delete"/>
<fmt:message bundle="${loc}" key="local.body.listnews.text.view_text" var="view_text"/>
<fmt:message bundle="${loc}" key="local.body.listnews.text.no_news" var="no_news"/>
<fmt:message bundle="${loc}" key="local.body.listnews.text.guest_info" var="guest_info"/>

<%-- errors --%>
<fmt:message bundle="${loc}" key="local.header.error.authentication_error" var="authentication_error"/>

<%-- message --%>
<fmt:message bundle="${loc}" key="local.header.message.registration.success" var="registration_success"/>
<fmt:message bundle="${loc}" key="local.header.message.registration.unsuccess" var="registration_unsuccess"/>
<fmt:message bundle="${loc}" key="local.header.message.delete_status.unsuccess" var="delete_unsuccess"/>

<%-- footer --%>
<fmt:message bundle="${loc}" key="local.footer.text" var="footer_text"/>
