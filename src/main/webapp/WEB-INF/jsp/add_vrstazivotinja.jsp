<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>



<%@include file="header.jsp" %>
<h1>Додај врсту животиње</h1>
<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <c:url var="addVrstaZivotinja" value="/add_vrstazivotinja" ></c:url>
        <form:form method="POST" action="${addVrstaZivotinja}" modelAttribute="vrsta_zivotinje">
            <% String success = (String) request.getAttribute("successMsg");%>
            <%= (success != null) ? "<div class=\"alert alert-success\">" + success + "</div>" : ""%>
            <div class="form-group">
                <form:label path="ime">
                    Врста животиње
                </form:label>
                <form:input type="ime" class="form-control" id="ime" placeholder="Vrsta životinje" path="ime" />
            </div>

            <form:input type="hidden" id="id" class="form-control" placeholder="id" path="id" />
            <button type="submit" class="btn btn-primary">Унеси</button>
        </form:form>
    </div>
    <div class="col-md-8 col-md-offset-2">
        <c:if test="${!empty vrste_zivotinja}">

            <table class="table table-striped mojatabela">
                <thead>
                    <tr>
                        <th>Врста животиње</th>
                        <th>Измена</th>
                        <th>Брисање</th>
                    </tr>
                </thead>
                <tbody> 
                    <c:forEach items="${vrste_zivotinja}" var="vrsta_zivotinje">

                        <tr>
                            <td>${vrsta_zivotinje.ime}</td>
                            <td><a href="<c:url value='/edit_vrstazivotinja/${vrsta_zivotinje.id}' />">измена</a></td>
                            <td><a href="<c:url value='/delete_vrstazivotinja/${vrsta_zivotinje.id}' />">брисање</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</div>
<%@include file="footer.jsp" %>