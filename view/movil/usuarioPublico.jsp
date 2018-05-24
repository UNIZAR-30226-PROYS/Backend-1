<%--TODO: Falta revisar codigo java dentro de la pagina--%>
<%@page contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Perfil de ${usuario.getIdUser()}</title>
    <jsp:include page="includes/header.jsp"></jsp:include>
</head>
<body>

<jsp:include page="includes/navbars.jsp"></jsp:include>
<!-- CONTENIDO DE LA VISTA -->
<div class="container mb-3">
    <div class="row pt-3">
        <div class="col col-md-offset-5">
            <div class="img-responsive text-center">
                <img src="/contenido/imagenes/usuarios/${usuario.getIdUser()}Perfil.png?x=${rand}" style="height: 80px;"
                     alt="Usuario">
                <p>${usuario.getIdUser()}</p>
            </div>
        </div>
    </div>
    <div class="border-bottom border-dark w-100 my-2 px-3"></div> <!-- Separador horizontal -->
    <c:if test="${suscrito || publico}">
        <div class="row">
            <div class="col-auto mr-auto">
                <h4>Listas de ${usuario.getIdUser()}</h4>
            </div>
            <div class="float-right mr-3">
                    <%--TODO: sustituir por mis_listas pero bien cargada--%>
                <a href="/mis_listas.jsp" class="btn btn-link" role="button">
                    <span class="fa fa-chevron-right" style="font-size:20px;"></span>
                </a>
            </div>
        </div>
        <div class="row">
            <div class="col-auto mr-auto">
                <h4>Audio de ${usuario.getIdUser()}</h4>
            </div>
            <div class="float-right mr-3">
                <a href="/list?id=${musica.getIdLista()}" class="btn btn-link" role="button">
                    <span class="fa fa-chevron-right" style="font-size:20px;"></span>
                </a>
            </div>
        </div>
        <div class="border-bottom border-dark w-100 px-3"></div><!-- Separador horizontal -->
    </c:if>
    <c:if test="${suscrito}">
        <p> Estás suscrito a ${usuario.getIdUser()}</p>
    </c:if>
    <c:if test="${publico || suscrito}">
        <div class="media mt-2">
            <div class="media-body">
                <h4 class="media-heading"> Suscripciones de ${usuario.getIdUser()}</h4>
            </div>
        </div>
        <%--<p class="text-danger">${error}</p>--%>
        <div class="list-group pt-2">
            <c:forEach items="${suscripciones}" var="sus">
                <a href="/user?id=${sus.getUsuarioByIdSuscrito().getIdUser()}"
                   class="list-group-item list-group-item-action">
                    <div class="media">
                        <div class="media-left" style="padding-right:15px">
                            <img src="/contenido/imagenes/usuarios/${sus.getUsuarioByIdSuscrito().getIdUser()}Perfil.png?x=${rand}"
                                 style="width:30px;" alt="...">
                        </div>
                        <div class="media-body">
                            <h6 class="media-heading">${sus.getUsuarioByIdSuscrito().getIdUser()}</h6>
                            <!--TODO: Estado del usuario, o bien desconectado, o bien el nombre de la cancion que esta escuchando/ha escuchado mas recientemente-->
                            <h6 class="media-heading">Cancion 1</h6>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </c:if>
    <c:if test="${!publico && !suscrito}">
        <p>${usuario.getIdUser()} tiene el perfil bloqueado.</p>
    </c:if>
    <c:if test="${!suscrito}">
        <div class="media mt-2">
            <div class="media-body">
                <h4 class="media-heading"> Suscribirse a ${usuario.getIdUser()}</h4>
            </div>
            <div class="media-left">
                <a href="${pageContext.request.contextPath}/Suscribe?name=${usuario.getIdUser()}">
                    <button type="button" class="btn btn-default ">
                        <span class="fa fa-user-plus" style="font-size:20px; "></span>
                    </button>
                </a>
            </div>
        </div>
    </c:if>


</div> <!-- Container -->

<jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>


