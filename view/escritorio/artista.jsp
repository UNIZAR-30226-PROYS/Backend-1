<%@ page import="main.java.model.Usuario" %>
<%@ page import="main.java.model.Suscribir" %>
<%@page contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% if(request.getParameter("ajax")==null){ %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>WolfSound - Artista</title>
    <%@include file="includes/html_head.jsp" %>
</head>
<body>
<!-- Menu superior y contenido -->
    <div id="topbar-y-contenido" class="col-10 pl-0 pr-0">
        <%@include file="includes/topbar.jsp" %>
        <!-- CONTENIDO DE LA VISTA -->
        <div id="contenido">
            <% } //if%>
            <div id="tituloNuevo" value="WolfSound - Artista" style="display:none;"></div>
            <!-- CONTENIDO DE LA VISTA -->
            <div class="container mb-3">

                <div class="media mt-2">
                    <div class="row media-body pt-2 pl-4">
                        <div class="media-left pl-1" style="padding-right:15px">
                            <img src="/contenido/imagenes/usuarios/${usuario.getUsername()}Perfil.png?x=${rand}" style="width:300px;" alt="...">
                        </div>
                        <div class="row media-body pt-5 pl-5 mt-auto mb-auto">
                            <h1 class="media-heading">${usuario.getUsername()}</h1>
                            <c:if test="${!suscrito}">
                                <form action="${pageContext.request.contextPath}/Suscribe" method="get" >
                                    <div class="form-group">
                                        <div class="input-group">
                                            <input type="hidden" value="${usuario.getUsername()}" name="name" required>
                                        </div>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Suscribirse</button>
                                </form>
                            </c:if>
                        </div>
                    </div>
                </div>

                <div class="media mt-2">
                    <div class="media-body pt-2 pl-4">
                        <h5 class="media-heading">Populares</h5>
                    </div>
                </div>

                <div class="row pt-2 pl-4 pr-2">
                    <div class="col-auto pt-2 pb-1">
                        <a href = "/lists?id=${usuario.getIdUser()}"><h4>Listas</h4></a>
                    </div>
                </div>
<!--
                <div class="row pl-4 pr-2">
                    <c:forEach items="${listas}" var="lista" begin="0" end="3">
                        <div class="col-3">
                            <a href="/list?id=${lista.getIdLista()}" target="_self">
                                <div class="img-thumbnail h-100">
                                    <img src="<c:choose>
                                            <c:when test="${lista.getCancioneslistasByIdLista().isEmpty()}">
                                                ${pageContext.request.contextPath}/contenido/web/imagenes/wolf.jpg
                                            </c:when>
                                            <c:otherwise>
                                                ${pageContext.request.contextPath}/contenido/imagenes/canciones/${lista.getCancioneslistasByIdLista().get(0).getCancionByIdCancion().getIdCancion()}.png
                                            </c:otherwise>
                                        </c:choose>
                                        " alt="Imagen lista" class="pt-3" style="width:20%">
                                    <div class="caption pt-3 pb-2">
                                        <p>${lista.getNombre()}</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
                -->
            </div> <!-- Container -->
            <% if(request.getParameter("ajax")==null){ %>
        </div>
    </div>
    <%@include file="includes/socialbar.jsp" %>
    <%@include file="includes/reproductor.jsp" %>
</body>
</html>
<% } %>