<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>



<%@include file="header.jsp" %>
<h1>Додај корисника</h1>
<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <c:url var="addKorisnik" value="/add_korisnik" ></c:url>
        <form:form method="POST" action="${addKorisnik}" modelAttribute="korisnik">
            <% String success = (String) request.getAttribute("successMsg");%>
            <%= (success != null) ? "<div class=\"alert alert-success\">" + success + "</div>" : ""%>
            <div class="form-group">
                <form:label path="username">
                    Username
                </form:label>
                <form:input type="username" class="form-control" id="username" placeholder="username" path="username" />
            </div>
            <div class="form-group">
                <form:label path="password">
                    Password
                </form:label>
                <form:input type="password" class="form-control" id="password" placeholder="password" path="password" />
            </div>
            <div class="form-group">
                <form:label path="role">
                    Rola
                </form:label>
                <form:input type="role" class="form-control" id="role" placeholder="role" path="role" />
            </div>
            <div class="form-group">
                <form:label path="enabled">
                    Enabled
                </form:label>
                <form:input type="enabled" class="form-control" id="enabled" placeholder="enabled" path="enabled" />
            </div>

            <form:input type="hidden" id="id" class="form-control" placeholder="id" path="id" />
            <button type="submit" class="btn btn-primary">Унеси</button>
        </form:form>
    </div>
    <div class="col-md-8 col-md-offset-2">
        <c:if test="${!empty korisnici}">

            <table class="table table-striped mojatabela">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Rola</th>
                        <th>Enabled</th>
                    </tr>
                </thead>
                <tbody> 
                    <c:forEach items="${korisnici}" var="korisnik">

                        <tr>
                            <td>${korisnik.username}</td>
                            <td>${korisnik.password}</td>
                            <td>${korisnik.role}</td>
                            <td>${korisnik.enabled}</td>
                            <td><a href="<c:url value='/edit_korisnik/${korisnik.id}' />">измени</a></td>
                            <td><a href="<c:url value='/delete_korisnik/${korisnik.id}' />">обриши</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</div>
<%@include file="footer.jsp" %>