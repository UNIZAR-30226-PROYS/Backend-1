<%@page contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% if(request.getParameter("ajax")==null){ %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>WolfSound - Explorar</title>
    <%@include file="includes/html_head.jsp" %>
</head>
<body>
    <!-- Menu superior y contenido -->
    <div id="topbar-y-contenido" class="col-10 pl-0 pr-0">
        <%@include file="includes/topbar.jsp" %>
        <!-- CONTENIDO DE LA VISTA -->
        <div id="contenido">
<% } //if%>
            <div id="tituloNuevo" value="WolfSound - Explorar" style="display:none;"></div>
            <div class="col-12 d-block">

                <!-- Titulo de la pantalla -->
                <h2 class="text-left pl-4 pt-2">Explorar</h2>
                <div class="row pl-4 pt-2 pb-2">
                    <div class="col-7">
                        <form id="formBuscar" class="search-button" action="${pageContext.request.contextPath}/search" method="get">
                            <div class="orm-group">
                                <div class="input-group">
                                    <input id="textoBuscar" name="search_input" type="text" class="form-control" placeholder="Buscar ..." autocomplete="off" required>
                                    <span class="input-group-btn search-button">
                                        <button type="submit" class="btn btn-secondary">
                                            <span class="fa fa-search" style="font-size:20px;"  ></span>
                                        </button>
                                    </span>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                ${requestScope.error}
                <c:remove var="error"></c:remove>
                <c:if test="${not empty sessionScope.username}">
                    <div class="row pl-4 pr-2">
                        <div class="col-auto pt-2 pb-3">
                            <h4>Mis listas</h4>
                        </div>
                        <div class="float-right mr-3 pt-2">
                            <a href="${pageContext.request.contextPath}/lists?id=${sessionScope.username.getIdUser()}" class="btn btn-link" role="button" >
                                <span class="fa fa-chevron-right" style="font-size:20px;"></span>
                            </a>
                        </div>
                    </div>
                    <div class="row pl-4 pr-2">
                        <c:forEach items="${misListas}" var="lista" begin="0" end="3">
                            <div class="col-3 h-100 align-content-center align-items-center">
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
                                        " alt="Imagen lista" class="thumbnail-cuadrado" >
                                        <div class="caption pt-3 pb-2">
                                            <p style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap" class="mx-0 my-0">${lista.getNombre()}</p>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>

                <div class="row pt-3 pl-4 pr-2">
                    <div class="col-auto pt-2 pb-3">
                        <h4>Listas recomendadas</h4>
                    </div>
                    <div class="float-right mr-3 pt-2">
                        <a href="listas.jsp" class="btn btn-link" role="button" >
                            <span class="fa fa-chevron-right" style="font-size:20px;"></span>
                        </a>
                    </div>
                </div>
                <div class="row pl-4 pr-2">
                    <c:forEach items="${sessionScope.listasRecomendadas}" var="lista"  begin="0" end="3">
                        <div class="col-3 h-100 align-content-center align-items-center">
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
                                    " alt="Imagen lista" class="thumbnail-cuadrado" >
                                    <div class="caption pt-3 pb-2">
                                        <p style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap" class="mx-0 my-0">${lista.getNombre()}</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>

                <c:if test="${not empty sessionScope.username}">
                    <div class="row pl-4 pt-3 pb-1 pr-2">
                        <div class="col-auto pt-2 pb-3">
                            <h4>Mi audio</h4>
                        </div>
                        <div class="float-right mr-3 pb-3 pt-2">
                            <button type="submit" class="btn btn-link">
                                <span class="fa fa-chevron-right" style="font-size:20px;"></span>
                            </button>
                        </div>
                    </div>
                    <div class="row pl-4 pr-2">
                        <c:forEach items="${sessionScope.misAudios}" var="audio"  begin="0" end="3">
                            <div class="col-3 h-100 align-content-center align-items-center">
                                <a href="/song?id=${audio.getIdCancion()}" target="_self">
                                    <div class="img-thumbnail h-100">
                                        <img src="${pageContext.request.contextPath}/contenido/imagenes/canciones/${audio.getIdCancion()}.png" class="thumbnail-cuadrado" alt="Imagen cancion" >
                                        <div class="caption pt-3 pb-2">
                                            <p style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap" class="mx-0 my-0">${audio.getNombre()}</p>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>

                <div class="row pl-4 pt-3 pr-2">
                    <div class="col-auto pt-2 pb-3">
                        <h4>Audio recomendado</h4>
                    </div>
                    <div class="float-right mr-3 pt-2">
                        <button type="submit" class="btn btn-link">
                            <span class="fa fa-chevron-right" style="font-size:20px;"></span>
                        </button>
                    </div>
                </div>
                <div class="row pl-4 pb-4 pr-2">
                    <c:forEach items="${sessionScope.audiosRecomendados}" var="audio"  begin="0" end="3">
                        <div class="col-3 h-100 align-content-center align-items-center">
                            <a href="/song?id=${audio.getIdCancion()}" target="_self">
                                <div class="img-thumbnail h-100">
                                    <img src="${pageContext.request.contextPath}/contenido/imagenes/canciones/${audio.getIdCancion()}.png" class="thumbnail-cuadrado" alt="Imagen cancion" >
                                    <div class="caption pt-3 pb-2">
                                        <p style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap"
                                           class="mx-0 my-0">${audio.getNombre()}</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <script src="${pageContext.request.contextPath}/scripts/busqueda.js"></script>
        <% if(request.getParameter("ajax")==null){ %>
        </div>
    </div>
    <%@include file="includes/socialbar.jsp" %>
    <%@include file="includes/reproductor.jsp" %>
</body>
</html>
<% } %>