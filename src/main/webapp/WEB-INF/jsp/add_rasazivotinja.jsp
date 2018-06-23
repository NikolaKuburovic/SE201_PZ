<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>



<%@include file="header.jsp" %>
<% System.out.println("jsp: vrste zivotinja " + pageContext.findAttribute("vrste_zivotinja"));%>
<h1>Додај расу животиња</h1>
<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <c:url var="addRasaZivotinja" value="/add_rasazivotinja" ></c:url>
        <form:form method="POST" action="${addRasaZivotinja}" modelAttribute="rasa_zivotinja">
            <% String success = (String) request.getAttribute("successMsg");%>
            <%= (success != null) ? "<div class=\"alert alert-success\">" + success + "</div>" : ""%>
            <div class="form-group">
                <form:label path="ime">
                    Раса животиња
                </form:label>
                <form:input type="ime" class="form-control" id="ime" placeholder="Rasa životinje" path="ime" />
            </div>
            <div class="form-group ${status.error ? 'has-error' : ''}">    
                <form:label class="col-sm-1 control-label" for="vrstaZivotinjaId" path="vrstaZivotinjaId">
                    Врста животиња
                </form:label>
                <div class="col-sm-4">
                    <form:select class="form-control" path="vrstaZivotinjaId" placeholder="">
                        <c:forEach var="vrstazivotinje" items="${vrste_zivotinja}">
                            <form:option value="${vrstazivotinje.id}">
                                <c:out value="${vrstazivotinje.ime}"/>
                            </form:option>  
                        </c:forEach>
                    </form:select>
                </div>
            </div><br/><br/><br/>

            <form:input type="hidden" id="id" class="form-control" placeholder="id" path="id" />
            <button type="submit" class="btn btn-primary">Унеси</button>
        </form:form>
    </div>
    <div class="col-md-8 col-md-offset-2">
        <c:if test="${!empty rase_zivotinja}">

            <table class="table table-striped mojatabela">
                <thead>
                    <tr>
                        <th>Раса животиње</th>
                        <th>Врста животиње</th>
                        <th>Измена</th>
                        <th>Брисање</th>
                    </tr>
                </thead>
                <tbody> 
                    <c:forEach items="${rase_zivotinja}" var="rasa_zivotinja">

                        <tr>
                            <td>${rasa_zivotinja.ime}</td>
                            <td>${rasa_zivotinja.vrstaZivotinjaId}</td>
                            <td><a href="<c:url value='/edit_rasazivotinja/${rasa_zivotinja.id}' />">измена</a></td>
                            <td><a href="<c:url value='/delete_rasazivotinja/${rasa_zivotinja.id}' />">брисање</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</div>
<%@include file="footer.jsp" %>