
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ include file="header.jsp" %>


<% System.out.println("jsp: korisnik je: " + pageContext.findAttribute("asistenti"));%>


<h2>Пријави се:</h2>
<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>
<c:if test="${not empty msg}">
    <div class="msg">${msg}</div>
</c:if><br/>
<form class="form-horizontal" name='loginForm'
      action="<c:url value='/j_spring_security_check' />" method='POST'>
    <div class="form-group">
        <label class="col-sm-1 control-label">Корисник:</label>
        <div class="col-sm-4">
            <input class="form-control" type='text' name='username' autofocus/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label">Лозинка:</label>
        <div class="col-sm-4">
            <input class="form-control" type='password' name='password'/>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-1 col-sm-4">

            <button type="submit" class="btn btn-primary pull-right">Пријави се</button>

        </div>
    </div><br/>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<%@ include file="footer.jsp" %>
