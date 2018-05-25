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
    <c:forEach items="${username.getSuscribirsByIdUser()}" var="usuario">
            <a href="#" class="list-group-item list-group-item-action">
                <div class="media">
                    <div class="media-left" style="padding-right:15px">
                        <img src="/contenido/imagenes/usuarios/${usuario.getUsername()}.jpg" style="width:30px;" alt="...">
                    </div>
                    <div class="media-body">
                        <!--TODO: Sustituir por funcion getLastUploads-->
                        <h6 class="media-heading">${usuario.getNomAp()}</h6>
                        <h6 class="media-heading">${usuario.getUsername()}</h6>
                    </div>
                </div>
            </a>
    </c:forEach>
    </div>
</div> <!-- Container -->

    <jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>


