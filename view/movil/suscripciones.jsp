<%--TODO: Terminar de implementar funcionalidad--%>
<%@page contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Mis suscripciones</title>
    <jsp:include page="includes/header.jsp"></jsp:include>
</head>
<body>
    <jsp:include page="includes/navbars.jsp"></jsp:include>
<!-- CONTENIDO DE LA VISTA -->
<div class="container mb-3">

    <div class="media mt-2">
        <div class="media-body">
            <h4 class="media-heading">Actividad de suscripciones</h4>
        </div>
    </div>

    <div class="list-group pt-2">
        <c:forEach items="${novedades}" var="cancion">
            <div class="list-group pt-2">
                <div class="list-group-item list-group-item-action">
                    <div class="media">
                        <div class="media-left" style="padding-right:15px">
                            <a href="/song?id=${cancion.getIdCancion()}">
                                <img src="/contenido/imagenes/canciones/${cancion.getIdCancion()}.png"
                                     style="width:64px;"
                                     alt="...">
                            </a>
                        </div>
                        <div class="media-body">
                            <!-- Nombre cancion-->
                            <h6 class="media-heading"
                                style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap">${cancion.getNombre()}</h6>
                            <!-- Usuario que ha subido la canción-->
                            <h6 class="media-heading">${cancion.getUsuarioByIdUser().getUsername()}</h6>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div> <!-- Container -->

    <jsp:include page="includes/footer.jsp"></jsp:include>
    <%@include file="includes/reproductor.jsp" %>
</body>
</html>


