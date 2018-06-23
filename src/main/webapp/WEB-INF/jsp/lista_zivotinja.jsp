<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>



<%@include file="header.jsp" %>
<% System.out.println("jsp: vrste zivotinja " + pageContext.findAttribute("vrste_zivotinja"));%>
<h1>Листа животиња</h1>
<div class="container">
    <% String success = (String) request.getAttribute("successMsg");%>
    <%= (success != null) ? "<div class=\"alert alert-success\">" + success + "</div>" : ""%>
    <% String error = (String) request.getAttribute("errorMsg");%>
    <%= (error != null) ? "<div class=\"alert alert-danger\">" + error + "</div>" : ""%>
    <div class="col-md-8 col-md-offset-2">
        <c:if test="${!empty rase_zivotinja}">

            <table class="table table-striped mojatabela">
                <thead>
                    <tr>
                        <th>Раса животиње</th>
                        <th>Врста животиње</th>
                        <th>Име</th>
                        <th>Опис</th>
                        <th>Датум постављања</th>
                        <th>Резервисао</th>
                        <th>Резервација</th>
                        <th>Одустајање</th>
                    </tr>
                </thead>
                <tbody> 
                    <c:forEach items="${zivotinje}" var="zivotinja">

                        <tr>
                            <td>${zivotinja.rasaZivotinja.ime}</td>
                            <td>${zivotinja.vrstaZivotinjaId}</td>
                            <td>${zivotinja.ime}</td>
                            <td>${zivotinja.opis}</td>
                            <td>${zivotinja.datumKreiranja}</td>
                            <td>${zivotinja.korisnikId.username}</td>
                            <td><a href="<c:url value='/prijavi_zivotinja/${zivotinja.id}' />">резервиши</a></td>
                            <td><a href="<c:url value='/odjavi_zivotinja/${zivotinja.id}' />">одустани</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</div>
<%@include file="footer.jsp" %>