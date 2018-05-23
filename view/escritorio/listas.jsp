<%@ page import="org.hibernate.Hibernate" %>
<%@page contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    if(request.getParameter("ajax")==null){ %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Wolfic - Listas</title>
    <%@include file="includes/html_head.jsp" %>
</head>
<body>
    <div id="topbar-y-contenido" class="col-10 pl-0 pr-0">
        <%@include file="includes/topbar.jsp" %>
        <!-- CONTENIDO DE LA VISTA -->
        <div id="contenido">
                <% } //if%>
            <div id="tituloNuevo" value="Wolfic - Listas" style="display:none;"></div>
            <div class="container mb-3">

                <div class="media mt-2">
                    <div class="media-body pt-2 pl-4">
                        <h2 class="media-heading">Listas de ${sessionScope.username.getIdUser()}</h2>
                    </div>
                </div>
                <div class="list-group pl-4 pt-4 pr-2">
                    <c:forEach items="${misListas}" var="lista">
                        <div class="list-group-item list-group-item-action">
                            <div class="media">
                                <div class="media-left" style="padding-right:15px">
                                    <a href="/list?id=${lista.getIdLista()}">
                                        <img src="/contenido/web/imagenes/${lista.getIdLista()}.jpg" style="width:70px;" alt="...">
                                    </a>
                                </div>
                                <div class="media-body">
                                    <h6 class="media-heading">${lista.getNombre()}</h6>
                                </div>
                                <div class="media-right">
                                    <button type="button" class="btn btn-default ">
                                        <span class="fa fa-trash" style="font-size:20px; "></span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div> <!-- Container -->
        <% if(request.getParameter("ajax")==null){ %>
        </div>
    </div>
        <%@include file="includes/socialbar.jsp" %>
        <%@include file="includes/reproductor.jsp" %>
</body>
</html>
<% } %>