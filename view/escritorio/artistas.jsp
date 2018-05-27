<%@page contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% if(request.getParameter("ajax")==null){ %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>WolfSound - Artistas</title>
    <%@include file="includes/html_head.jsp" %>
</head>
<body>
    <div id="topbar-y-contenido" class="col-10 pl-0 pr-0">
        <%@include file="includes/topbar.jsp" %>
        <!-- CONTENIDO DE LA VISTA -->
        <div id="contenido">
<% } //if%>
            <div id="tituloNuevo" value="WolfSound - Artistas" style="display:none;"></div>
            <!-- CONTENIDO DE LA VISTA -->
            <div class="container mb-3">

                <div class="media mt-2">
                    <div class="media-body pt-2 pl-4">
                        <h2 class="media-heading">Actividad reciente de suscripciones</h2>
                    </div>
                </div>

                <c:forEach items="${novedades}" var="cancion">
                    <div class="list-group-item list-group-item-action">
                        <div class="media pt-2 pb-1 mt-auto">
                            <div class="media-left" style="padding-right:15px">
                                <a href="${pageContext.request.contextPath}/song?id=${cancion.getIdCancion()}">
                                    <img src="${pageContext.request.contextPath}/contenido/imagenes/canciones/${cancion.getIdCancion()}.png" style="width:64px;" alt="...">
                                </a>
                            </div>
                            <div class="media-body mt-auto">
                                <h6 class="media-heading pl-5">${cancion.getNombre()}</h6>
                                <h6 class="media-heading pl-5">${cancion.getUsuarioByIdUser().getUsername()}</h6>
                            </div>
                            <div class="media-body mt-auto">
                                <h6 class="media-heading pl-3">${cancion.getAlbumByIdAlbum().getNombre()}</h6>
                            </div>
                            <div class="media-body mt-auto">
                                <h6 class="media-heading pl-3">${cancion.getFechaSubida()}</h6>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div> <!-- Container -->
    <% if(request.getParameter("ajax")==null){ %>
        </div>
    </div>
    <%@include file="includes/socialbar.jsp" %>
    <%@include file="includes/reproductor.jsp" %>
</body>
</html>
<% } %>