<%--TODO: Terminar de implementar funcionalidad--%>
<%@page contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Explorar</title>
    <jsp:include page="includes/header.jsp"></jsp:include>
</head>
<body>
<jsp:include page="includes/navbars.jsp"></jsp:include>
<!-- CONTENIDO DE LA VISTA -->
<div class="container mb-3">

    <!-- Titulo de la pantalla -->
    <h2 class="text-left pt-2">Explorar</h2>
    <div class="row pt-1">
        <div class="col">
            <form id="formBuscar" class="search-button" action="${pageContext.request.contextPath}/search" method="get">
                <div class="form-group">
                    <div class="input-group">
                        <input type="text" class="form-control" name="search_input" placeholder="Buscar ..." required>
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

    <c:if test="${not empty sessionScope.username}">
        <div class="row">
            <div class="col-auto mr-auto">
                <h4>Mis listas</h4>
            </div>
            <div class="float-right mr-3">
                <a href="/lists?id=${username.getIdUser()}" class="btn btn-link" role="button">
                    <span class="fa fa-chevron-right" style="font-size:20px;"></span>
                </a>
            </div>
        </div>
        <div class="row">
            <%--${misListas}--%>
            <c:forEach items="${misListas}" var="lista">
                <div class="col-4">
                    <div class="img-thumbnail h-100">
                        <a href="/list?id=${lista.getIdLista()}" target="_self">
                            <img src="<c:choose>
                                <c:when test="${lista.getCancioneslistasByIdLista().isEmpty()}">
                                    ${pageContext.request.contextPath}/contenido/web/imagenes/wolf.jpg
                                </c:when>
                                <c:otherwise>
                                    ${pageContext.request.contextPath}/contenido/imagenes/canciones/${lista.getCancioneslistasByIdLista().get(0).getCancionByIdCancion().getIdCancion()}.png
                                </c:otherwise>
                            </c:choose>
                            " alt="Imagen lista" style="width:20%">
                            <div class="caption">
                                <p>${lista.getNombre()}</p>
                            </div>
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>

    <div class="row pt-3">
        <div class="col-auto mr-auto">
            <h4>Listas recomendadas</h4>
        </div>
        <div class="float-right mr-3">
            <a href="listas.jsp" class="btn btn-link" role="button">
                <span class="fa fa-chevron-right" style="font-size:20px;"></span>
            </a>
        </div>
    </div>
    <div class="row">
        <c:forEach items="${sessionScope.listasRecomendadas}" var="lista">
            <div class="col-4">
                <div class="img-thumbnail h-100">
                    <a href="lista.jsp" target="_self">
                        <img src="<c:choose>
                            <c:when test="${lista.getCancioneslistasByIdLista().isEmpty()}">
                                ${pageContext.request.contextPath}/contenido/web/imagenes/wolf.jpg
                            </c:when>
                            <c:otherwise>
                                ${pageContext.request.contextPath}/contenido/imagenes/canciones/${lista.getCancioneslistasByIdLista().get(0).getCancionByIdCancion().getIdCancion()}.png
                            </c:otherwise>
                        </c:choose>
                        " alt="Imagen lista" style="width:20%">
                        <div class="caption">
                            <p>${lista.getNombre()}</p>
                        </div>
                    </a>
                </div>
            </div>
        </c:forEach>
    </div>

    <c:if test="${not empty sessionScope.username}">
        <div class="row pt-3">
            <div class="col-auto mr-auto">
                <h4>Mi audio</h4>
            </div>
            <div class="float-right mr-3">
                <button type="submit" class="btn btn-link">
                    <span class="fa fa-chevron-right" style="font-size:20px;"></span>
                </button>
            </div>
        </div>
        <div class="row">
            <c:forEach items="${sessionScope.misAudios}" var="audio">
                <div class="col-4">
                    <div class="img-thumbnail h-100">
                        <a href="/song?id=${audio.getIdCancion()}" target="_self">
                            <img src="${pageContext.request.contextPath}/contenido/imagenes/canciones/${audio.getIdCancion()}.png" alt="" style="width:20%">
                            <div class="caption">
                                <p>${audio.getNombre()}</p>
                            </div>
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>

    <div class="row pt-3">
        <div class="col-auto mr-auto">
            <h4>Audio recomendado</h4>
        </div>
        <div class="float-right mr-3">
            <button type="submit" class="btn btn-link">
                <span class="fa fa-chevron-right" style="font-size:20px;"></span>
            </button>
        </div>
    </div>
    <div class="row">
        <c:forEach items="${sessionScope.audiosRecomendados}" var="audio">
            <div class="col-4">
                <div class="img-thumbnail h-100">
                    <a href="/song?id=${audio.getIdCancion()}" target="_self">
                        <img src="${pageContext.request.contextPath}/contenido/imagenes/canciones/${audio.getIdCancion()}.png" alt="" style="width:20%">
                        <div class="caption">
                            <p>${audio.getNombre()}</p>
                        </div>
                    </a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<jsp:include page="includes/footer.jsp"></jsp:include>
<%@include file="includes/reproductor.jsp" %>
</body>
</html>
