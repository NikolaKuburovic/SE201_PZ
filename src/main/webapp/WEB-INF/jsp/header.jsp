<%-- 
    Document   : header
    Created on : Jun 14, 2018, 4:15:08 PM
    Author     : Nikola Kuburovic 1095
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String uri = request.getRequestURI();
    String pageName = uri.substring(uri.lastIndexOf("/") + 1);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <script src="https://code.jquery.com/jquery-3.1.1.js" integrity="sha256-16cdPddA6VdVInumRGo6IbivbERE8p7CQR3HzTBuELA=" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.7/flatly/bootstrap.min.css"/>
        <c:url var="css"  value="/css/style.css" />
        <link rel="stylesheet" type="text/css" href="${css}"/>


        <script>
            $(document).ready(function () {
                $("table").DataTable();
            });
        </script>
        <title>SE201 PZ - Nikola Kuburovic 1095</title>
    </head>
    <body>
        <div class="container">
            <nav class="navbar navbar-default">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">SE201PZ</a>
                    </div>

                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <c:url var="index"  value="/" />
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <c:url var="add_zivotinja"  value="/add_zivotinja" />
                            <c:url var="add_vrstazivotinja"  value="/add_vrstazivotinja" />
                            <c:url var="add_rasazivotinja"  value="/add_rasazivotinja" />
                            <c:url var="add_korisnik"  value="/add_korisnik" />
                            
                            <li class="<%=  (pageName.equals("index.jsp")) ? "active" : ""%>"><a href="${index}">Index</a></li>
                            <li class="<%=  (pageName.equals("add_zivotinja.jsp")) ? "active" : ""%>"><a href="${add_zivotinja}">Додај животињу</a></li>
                            <li class="<%=  (pageName.equals("add_vrstazivotinja.jsp")) ? "active" : ""%>"><a href="${add_vrstazivotinja}">Додај врсту животиње</a></li>
                            <li class="<%=  (pageName.equals("add_rasazivotinja.jsp")) ? "active" : ""%>"><a href="${add_rasazivotinja}">Додај расу животиње</a></li>
                            <li class="<%=  (pageName.equals("add_korisnik.jsp")) ? "active" : ""%>"><a href="${add_korisnik}">Додај корисника</a></li>
                            </sec:authorize>
                            <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
                            <c:url var="lista_zivotinja"  value="/lista_zivotinja" />
                            <li class="<%=  (pageName.equals("lista_zivotinja.jsp")) ? "active" : ""%>"><a href="${lista_zivotinja}">Листа животиња</a></li>
                            </sec:authorize>
                            
                            <c:url var="login"  value="/login" />
                            <li class="<%=  (pageName.equals("login.jsp")) ? "active" : ""%>"><a href="${login}">Пријава</a></li>
                            <li class="" >

                                <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                                    <c:url value="/j_spring_security_logout" var="logoutUrl" />
                                    <form action="${logoutUrl}" method="post" id="logoutForm">
                                        <input type="hidden"
                                               name="${_csrf.parameterName}"
                                               value="${_csrf.token}" />
                                    </form>
                                    <script>
                                        function formSubmit() {
                                            document.getElementById("logoutForm").submit();
                                        }
                                    </script>
                                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                                        <a href="#" onclick="formSubmit();
                                                return false;">Одјава</a>
                                    </c:if>
                                </sec:authorize>

                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
