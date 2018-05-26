<%@page contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Resultados de ${consulta}</title>
    <jsp:include page="includes/header.jsp"></jsp:include>
</head>
<body>
<jsp:include page="includes/navbars.jsp"></jsp:include>
<!-- CONTENIDO DE LA VISTA -->
<div class="container mb-3">

    <div class="media mt-2">
        <div class="media-body">
            <h4 class="media-heading">Resultados de "${consulta}"</h4>
        </div>
    </div>
    <div class="row pt-1">
        <div class="col">
            <form id="formBuscar" class="search-button" action="/search" method="get">
                <div class="form-group">
                    <div class="input-group">
                        <input type="text" class="form-control" name="search_input" placeholder="Buscar ...">
                        <span class="input-group-btn search-button">
                            <button type="submit" class="btn btn-secondary">
                                <span class="fa fa-search" style="font-size:20px;"></span>
                            </button>
                        </span>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="row pt-1">
        <div class="col">
            <div class="input-group">
                <select class="custom-select" name="seleccion" id="inputGroupSelect04">
                    <option id="canciones" selected="selected">Canciones</option>
                    <option id="usuarios">Usuarios</option>
                    <option id="listas">Listas</option>
                </select>
            </div>
        </div>
    </div>
    <%--Mensajes de depuracion para saber que resultados devuelve--%>
    <%--${listas}--%>
    <%--${canciones}--%>
    <%--${usuarios}--%>
    <div id="result_canciones" class="list-group pt-2">
        <c:forEach items="${canciones}" var="cancion">
            <a href="/song?id=${cancion.getIdCancion()}" class="list-group-item list-group-item-action">
                <div class="media">
                    <div class="media-left" style="padding-right:15px">
                        <img src="/contenido/imagenes/canciones/${cancion.getIdCancion()}.png" style="width:64px;"
                             alt="...">
                    </div>
                    <div class="media-body">
                        <h6 class="media-heading">${cancion.getNombre()}</h6>
                        <h6 class="media-heading">${cancion.getUsuarioByIdUser().getUsername()}</h6>
                    </div>
                </div>
            </a>
        </c:forEach>
    </div>
    <div id="result_usuarios" class="list-group pt-2 d-none">
        <c:forEach items="${usuarios}" var="usuario">
            <a href="/user?id=${usuario.getIdUser()}" class="list-group-item list-group-item-action">
                <div class="media">
                    <div class="media-left" style="padding-right:15px">
                        <img src="/contenido/imagenes/usuarios/${usuario.getIdUser()}.png?x=${rand}" style="width:64px;" alt="...">
                    </div>
                    <div class="media-body">
                        <h6 class="media-heading">${usuario.getNomAp()}</h6>
                        <h6 class="media-heading">${usuario.getUsername()}</h6>
                    </div>
                </div>
            </a>
        </c:forEach>
    </div>
    <div id="result_listas" class="list-group pt-2 d-none">
        <c:forEach items="${listas}" var="lista">
            <a href="/list?id=${lista.getIdLista()}" class="list-group-item list-group-item-action">
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
                        " alt="Imagen lista" class="pt-3" style="width:64px">
                    </div>
                    <div class="media-body">
                        <h6 class="media-heading">${lista.getNombre()}</h6>
                        <h6 class="media-heading">${lista.getNumElementos()} canciones</h6>
                    </div>
                </div>
            </a>
        </c:forEach>
    </div>
</div> <!-- Container -->

<jsp:include page="includes/footer.jsp"></jsp:include>
<script>
    $(document).ready(function () {
        $('#inputGroupSelect04').on('change', function () {
            // console.log("Entra en seleccionado");
            var seleccionado = $('#inputGroupSelect04 option').filter(':selected').text();
            // console.log(seleccionado);
            if (seleccionado === "Canciones") {
                $('#result_canciones').removeClass("d-none");
                $('#result_usuarios').addClass("d-none");
                $('#result_listas').addClass("d-none");
            }
            if (seleccionado === "Usuarios") {
                $('#result_canciones').addClass("d-none");
                $('#result_usuarios').removeClass("d-none");
                $('#result_listas').addClass("d-none");
            }
            if (seleccionado === "Listas") {
                $('#result_canciones').addClass("d-none");
                $('#result_usuarios').addClass("d-none");
                $('#result_listas').removeClass("d-none");
            }
        })
    });
</script>
</body>
</html>


