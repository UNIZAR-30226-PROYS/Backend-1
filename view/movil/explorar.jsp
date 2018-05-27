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
        <div class="row h-100">
            <c:forEach items="${misListas}" var="lista" varStatus="status">
                <c:if test="${status.count <= 3}">
                    <div class="col-4 h-100 align-content-center align-items-center">
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
                            " alt="Imagen lista" style="height: 68px">
                                <div class="caption">
                                    <p style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap"
                                       class="mx-0 my-0">${lista.getNombre()}</p>
                                </div>
                            </a>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </c:if>

    <div class="row pt-3">
        <div class="col-auto mr-auto">
            <h4>Listas recomendadas</h4>
        </div>
        <div class="float-right mr-3">
            <%--TODO: añadir listas de suscriptores aqui--%>
            <a href="#" class="btn btn-link" role="button">
                <span class="fa fa-chevron-right" style="font-size:20px;"></span>
            </a>
        </div>
    </div>
    <div class="row h-100">
        <c:forEach items="${sessionScope.listasRecomendadas}" var="lista" varStatus="status">
            <c:if test="${status.count <= 3}">
                <div class="col-4 h-100 align-content-center align-items-center">
                    <div class="img-thumbnail h-100">
                        <a href="#" target="_self">
                            <img src="<c:choose>
                            <c:when test="${lista.getCancioneslistasByIdLista().isEmpty()}">
                                ${pageContext.request.contextPath}/contenido/web/imagenes/wolf.jpg
                            </c:when>
                            <c:otherwise>
                                ${pageContext.request.contextPath}/contenido/imagenes/canciones/${lista.getCancioneslistasByIdLista().get(0).getCancionByIdCancion().getIdCancion()}.png
                            </c:otherwise>
                        </c:choose>
                        " alt="Imagen lista" style="height: 68px">
                            <div class="caption">
                                <p style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap"
                                   class="mx-0 my-0">${lista.getNombre()}</p>
                            </div>
                        </a>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>

    <c:if test="${not empty sessionScope.username}">
        <div class="row pt-3">
            <div class="col-auto mr-auto">
                <h4>Mi audio</h4>
            </div>
            <div class="float-right mr-3">
                <a href="/list?id=${misListas.get(1).getIdLista()}" class="btn btn-link" role="button">
                    <span class="fa fa-chevron-right" style="font-size:20px;"></span>
                </a>
            </div>
        </div>
        <div class="row h-100">
            <c:forEach items="${misAudios}" var="audio" varStatus="status">
                <c:if test="${status.count <= 3}">
                    <div class="col-4 h-100 align-content-center align-items-center">
                        <div class="img-thumbnail h-100">
                            <a href="/song?id=${audio.getIdCancion()}" target="_self">
                                <img src="${pageContext.request.contextPath}/contenido/imagenes/canciones/${audio.getIdCancion()}.png"
                                     alt="" style="height: 68px">
                                <div class="caption">
                                    <p style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap"
                                       class="mx-0 my-0">${audio.getNombre()}</p>
                                </div>
                            </a>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </c:if>

    <div class="row pt-3">
        <div class="col-auto mr-auto">
            <h4>Audio recomendado</h4>
        </div>
        <div class="float-right mr-3">
            <%--TODO: Añadir lista de audio recomendado--%>
            <a href="#" class="btn btn-link" role="button">
                <span class="fa fa-chevron-right" style="font-size:20px;"></span>
            </a>
        </div>
    </div>
    <div class="row h-100">
        <c:forEach items="${sessionScope.audiosRecomendados}" var="audio" varStatus="status">
            <c:if test="${status.count <= 3}">
                <div class="col-4 h-100 align-content-center align-items-center">
                    <div class="img-thumbnail h-100">
                        <a href="/song?id=${audio.getIdCancion()}" target="_self">
                            <img src="${pageContext.request.contextPath}/contenido/imagenes/canciones/${audio.getIdCancion()}.png"
                                 alt="" style="height: 68px">
                            <div class="caption">
                                <p style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap"
                                   class="mx-0 my-0">${audio.getNombre()}</p>
                            </div>
                        </a>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
</div>

<jsp:include page="includes/footer.jsp"></jsp:include>
<%@include file="includes/reproductor.jsp" %>
</body>
</html>
