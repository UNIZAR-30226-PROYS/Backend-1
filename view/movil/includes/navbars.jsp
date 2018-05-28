<!-- Cabecera y sidebar fijos -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav id="topbar" class="navbar sticky-top">
    <div class="col-4 mr-auto text-left">
        <span id="sidebarCollapse" style="font-size:20px; color:white">
            &#9776; <!-- Icono de side-menu -->
        </span>
    </div>
    <c:if test="${sessionScope.username != null}">
        <div class="col-4 mx-auto text-center">
            <a href="${pageContext.request.contextPath}/movil/explorar.jsp">    <!-- Brand/logo -->
                <img src="${pageContext.request.contextPath}/contenido/web/imagenes/wolfsound-white.png" style="height:40px;" alt="Wolfic">
            </a>
        </div>
        <div class="col-4 ml-auto text-right">
            <a href="${pageContext.request.contextPath}/user?id=${username.getIdUser()}"> <i class="fa fa-user pt-1" style="font-size:20px; color:white;"></i>  ${sessionScope.username.getUsername()}</a>
        </div>
    </c:if>
    <c:if test="${sessionScope.username == null}">
        <div class="col-4 mx-auto text-center">
            <a href="${pageContext.request.contextPath}/movil/wolfsound.jsp">    <!-- Brand/logo -->
                <img src="${pageContext.request.contextPath}/contenido/web/imagenes/wolfsound-white.png" style="height:40px;" alt="Wolfic">
            </a>
        </div>
        <div class="col-4 ml-auto text-right">
            <a href="${pageContext.request.contextPath}/movil/wolfsound.jsp">¡Regístrate!</a>
        </div>
    </c:if>
</nav>

<div id="sidebar" class="sidebar d-none">
    <c:if test="${sessionScope.username != null}">
        <ul class="list-unstyled components">
            <li><a href="${pageContext.request.contextPath}/movil/listas.jsp">Mis listas</a></li>
            <li><a href="${pageContext.request.contextPath}/movil/lista.jsp">Mi audio</a></li>
            <li><a href="${pageContext.request.contextPath}/suscriptions">Suscripciones</a></li>
            <li><a href="${pageContext.request.contextPath}/logout">Cerrar sesión</a></li>
        </ul>
        <div class="text-white text-center">
            <a href="subirCancion.jsp"><button type="button" class="btn btn-dark" >Sube tu música</button></a>
        </div>
    </c:if>
    <c:if test="${sessionScope.username == null}">
        <br><br>
        <div class="text-white text-center">
            <a href="${pageContext.request.contextPath}/movil/wolfsound.jsp"><button type="button" class="btn btn-dark" >¡Regístrate!</button></a>
        </div>
    </c:if>
</div>