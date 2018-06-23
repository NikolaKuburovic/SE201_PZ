<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>



<%@include file="header.jsp" %>
<% System.out.println("jsp: vrste zivotinja " + pageContext.findAttribute("vrste_zivotinja"));%>
<h1>Додај животињу</h1>
<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <c:url var="addZivotinja" value="/add_zivotinja" ></c:url>
        <form:form method="POST" action="${addZivotinja}" modelAttribute="zivotinja">
            <% String success = (String) request.getAttribute("successMsg");%>
            <%= (success != null) ? "<div class=\"alert alert-success\">" + success + "</div>" : ""%>
            <div class="form-group ${status.error ? 'has-error' : ''}">    
                <form:label class="col-sm-1 control-label" for="rasaZivotinjaId" path="rasaZivotinjaId">
                    Раса животиње
                </form:label>
                <div class="col-sm-4">
                    <form:select class="form-control" path="rasaZivotinjaId" placeholder="">
                        <c:forEach var="rasazivotinje" items="${rase_zivotinja}">
                            <form:option value="${rasazivotinje.id}">
                                <c:out value="${rasazivotinje.ime}"/>
                            </form:option>  
                        </c:forEach>
                    </form:select>
                </div>
            </div><br/><br/><br/>
            <div class="form-group ${status.error ? 'has-error' : ''}">    
                <form:label class="col-sm-1 control-label" for="vrstaZivotinjaId" path="vrstaZivotinjaId">
                    Врста животиње
                </form:label>
                <div class="col-sm-4">
                    <form:select class="form-control" path="vrstaZivotinjaId" placeholder="">
                        <c:forEach var="vrstazivotinja" items="${vrste_zivotinja}">
                            <form:option value="${vrstazivotinja.id}">
                                <c:out value="${vrstazivotinja.ime}"/>
                            </form:option>  
                        </c:forEach>
                    </form:select>
                </div>
            </div><br/><br/>
            <div class="form-group">
                <form:label path="ime">
                    Име животиње
                </form:label>
                <form:input type="ime" class="form-control" id="ime" placeholder="Ime životinje" path="ime" />
            </div>
            <div class="form-group">
                <form:label path="opis">
                    Опис
                </form:label>
                <form:input type="opis" class="form-control" id="opis" placeholder="Opis" path="opis" />
            </div>

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
                        <th>Име</th>
                        <th>Опис</th>
                        <th>Датум регистровања</th>
                        <th>Резервисао</th>
                        <th>Измена</th>
                        <th>Брисање</th>
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
                            <td><a href="<c:url value='/edit_zivotinja/${zivotinja.id}' />">измена</a></td>
                            <td><a href="<c:url value='/delete_zivotinja/${zivotinja.id}' />">брисање</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</div>
<%@include file="footer.jsp" %>