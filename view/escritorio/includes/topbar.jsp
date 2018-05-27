<nav id="topbar" class="navbar navbar-expand-lg navbar-light sticky-top pb-1 pt-0">
    <a href="${pageContext.request.contextPath}/escritorio/explorar.jsp" class="navbar-brand"><!-- Brand/logo -->
        <img src="${pageContext.request.contextPath}/contenido/web/imagenes/wolfsound-white.png" style="height:40px;"  alt="Wolfic logo">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item"><a id="nav-explorar" class="nav-link" href="${pageContext.request.contextPath}/escritorio/explorar.jsp">Explorar</a></li>
            <c:if test="${sessionScope.username != null}">
                <li class="nav-item"><a id="nav-listas" class="nav-link" href="${pageContext.request.contextPath}/lists?id=${username.getIdUser()}">Listas</a></li>
                <li class="nav-item"><a id="nav-artistas" class="nav-link" href="${pageContext.request.contextPath}/suscriptions">Suscripciones</a></li>
                <li class="nav-item"><a href="${pageContext.request.contextPath}/escritorio/subirMusica.jsp"><button type="button" class="btn btn-primary ml-1 mt-1">Sube tu música</button></a></li>
            </c:if>
        </ul>
    </div>
    <c:if test="${username != null}">
        <a href="${pageContext.request.contextPath}/escritorio/usuario.jsp" >&nbsp;<i class="fa fa-user pt-1 pr-1" style="font-size:20px;"></i>${username.getUsername()}</a>&nbsp;
        <form action="${pageContext.request.contextPath}/logout" method="get" >
            <button type="submit" class="btn btn-primary" style="border-color:#C81E37;background-color:#C81E37;"><i class="fa fa-power-off"></i></button>
        </form>
    </c:if>
</nav>
<center><img id="gifLoad" class="d-none" src="${pageContext.request.contextPath}/contenido/imagenes/load.gif" style="position:fixed;z-index:5000;" /></center>