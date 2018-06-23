
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="header.jsp" %>

<h2>Није вам дозвољен улаз на ову страну!</h2>
<c:choose>
    <c:when test="${empty username}">
        <h2>Пријави се</h2>
    </c:when>
    <c:otherwise>
        <h2>Корисниче ${username} морате имати одобрење за приступ!</h2>
        <a href="login">Пријави се</a>
    </c:otherwise>
</c:choose>
        <br/>

<%@ include file="footer.jsp" %>