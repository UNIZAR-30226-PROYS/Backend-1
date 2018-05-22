<%@page contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% if(request.getParameter("ajax")==null){ %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Wolfic - Artistas</title>
    <%@include file="includes/html_head.jsp" %>
</head>
<body>
    <div id="topbar-y-contenido" class="col-10 pl-0 pr-0">
        <%@include file="includes/topbar.jsp" %>
        <!-- CONTENIDO DE LA VISTA -->
        <div id="contenido">
<% } //if%>
            <div id="tituloNuevo" value="Wolfic - Artistas" style="display:none;"></div>
            <!-- CONTENIDO DE LA VISTA -->
            <div class="container mb-3">

                <div class="media mt-2">
                    <div class="media-body pt-2 pl-4">
                        <h2 class="media-heading">Actividad reciente de suscripciones</h2>
                    </div>
                </div>

                <div class="list-group pl-4 pt-4 pr-2">
                    <a href="cancion.jsp" class="list-group-item list-group-item-action">
                        <div class="media pt-1 pb-1">
                            <div class="media-left pl-1" style="padding-right:15px">
                                <img src="images/wolf.jpg" style="width:70px;" alt="...">
                            </div>
                            <div class="media-body mt-auto">
                                <!--TODO: Nombre cancion-->
                                <h6 class="media-heading">Nombre Cancion</h6>
                                <!--TODO: Usuario que ha subido la canción-->
                                <h6 class="media-heading">usuario</h6>
                            </div>
                            <div class="media-body mt-auto">
                                <h6 class="media-heading pl-3">19-04-2018</h6>
                            </div>
                        </div>
                    </a>
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