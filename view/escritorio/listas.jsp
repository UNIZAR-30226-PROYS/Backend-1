<%@ page import="org.hibernate.Hibernate" %>
<%@page contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    if(request.getParameter("ajax")==null){ %>
<!DOCTYPE html>
<html lang="es">
<head>
    <c:if test="${usuario.getIdUser() == username.getIdUser()}">
        <title>Mis listas - ${username.getIdUser()}</title>
    </c:if>
    <c:if test="${usuario.getIdUser() != username.getIdUser()}">
        <title> Listas de ${usuario.getIdUser()}</title>
    </c:if>
    <%@include file="includes/html_head.jsp" %>
</head>
<body>
    <div id="topbar-y-contenido" class="col-10 pl-0 pr-0">
        <%@include file="includes/topbar.jsp" %>
        <!-- CONTENIDO DE LA VISTA -->
        <div id="contenido">
                <% } //if%>
            <div id="tituloNuevo" value="WolfSound - Listas" style="display:none;"></div>
            <div class="container mb-3">

                <div class="media mt-2">
                    <div class="media-body">
                        <h4 class="media-heading">Listas de ${usuario.getUsername()}</h4>
                    </div>

                </div>
                <div class="list-group pl-4 pt-4 pr-2">
                    <c:forEach items="${listas}" var="lista">
                        <div class="list-group-item list-group-item-action">
                            <a href="/list?id=${lista.getIdLista()}">
                                <div class="media">
                                    <div class="media-left" style="padding-right:15px">
                                        <img src="<c:choose>
                                            <c:when test="${lista.getCancioneslistasByIdLista().isEmpty()}">
                                                ${pageContext.request.contextPath}/contenido/web/imagenes/wolf.jpg
                                            </c:when>
                                            <c:otherwise>
                                                ${pageContext.request.contextPath}/contenido/imagenes/canciones/${lista.getCancioneslistasByIdLista().get(0).getCancionByIdCancion().getIdCancion()}.png
                                            </c:otherwise>
                                        </c:choose>
                                        " class="img-thumbnail" style="width:70px;" alt="Imagen lista">
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
                            </a>
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