<%@page contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
	<head>
		<title>Mi perfil - ${sessionScope.username}</title>
        <jsp:include page="includes/header.jsp"></jsp:include>
    </head>
	<body>
        <jsp:include page="includes/navbars.jsp"></jsp:include>
        <%
            List<Suscribir> suscripciones = Suscribir.searchSuscripciones((String)session.getAttribute("username"));
            pageContext.setAttribute("suscripciones", suscripciones);
        %>
        <!-- CONTENIDO DE LA VISTA -->
        <div class="container mb-3">
            <div class="row pt-3">
                <div class="col-4">
                    <a href= "subirCancion.jsp">
                        <button type="button" class="btn btn-primary mt-2" style="background: #2853DC;  color:white;">Sube<br/>tu<br/>musica</button>
                    </a>
                </div>
                <div class="col-4">
                    <div class="img-responsive text-center">
                        <img src="/images/${sessionScope.username}_perfil.svg" style="height: 80px;" alt="Usuario" data-toggle="modal" data-target="#modalImagen">
                        <p>${sessionScope.username}</p>
                    </div>
                </div>
                <div class="col-4">
                    <a href="modificarCuenta.jsp">
                        <button type="button" class="btn btn-default float-right mt-3" >
                            <span class="fa fa-cog" style="font-size:25px; padding:15px;" ></span>
                        </button>
                    </a>
                </div>
            </div>
            <div class="border-bottom border-dark w-100 my-2 px-3"></div> <!-- Separador horizontal -->
            <div class="row">
                <div class="col-auto mr-auto">
                    <h4>Mis listas</h4>
                </div>
                <div class="float-right mr-3">
                    <a href="mis_listas.jsp" class="btn btn-link" role="button" >
                        <span class="fa fa-chevron-right" style="font-size:20px;"></span>
                    </a>
                </div>
            </div>
            <div class="row">
                <div class="col-auto mr-auto">
                    <h4>Audio favorito</h4>
                </div>
                <div class="float-right mr-3">
                    <a href="lista.jsp" class="btn btn-link" role="button" >
                        <span class="fa fa-chevron-right" style="font-size:20px;"></span>
                    </a>
                </div>
            </div>
            <div class="row">
                <div class="col-auto mr-auto">
                    <h4>Mi audio</h4>
                </div>
                <div class="float-right mr-3">
                    <a href="lista.jsp" class="btn btn-link" role="button" >
                        <span class="fa fa-chevron-right" style="font-size:20px;"></span>
                    </a>
                </div>
            </div>

            <!-- Separador horizontal -->
            <div class="border-bottom border-dark w-100 px-3"></div>
            <div class="media mt-2">
                <div class="media-body">
                    <h4 class="media-heading"> Suscripciones </h4>
                </div>
                <div class="media-right">
                    <button type="button" class="btn btn-default " data-toggle="modal" data-target="#modalSuscribir">
                        <span class="fa fa-user-plus" style="font-size:20px; "></span>
                    </button>
                </div>
            </div>
            <p class="text-danger">${error}</p>

            <div class="list-group pt-2">
                <!--TODO: Crear lista con canciones subidas recientemente por suscripciones-->
                <c:forEach items="${sessionScope.suscripciones}" var="sus">
                    <a href="usuarioPublico.jsp" class="list-group-item list-group-item-action">
                        <div class="media">
                            <div class="media-left" style="padding-right:15px">
                                <img src="images/wolf.jpg" style="width:30px;" alt="...">
                            </div>
                            <div class="media-body">
                                <!--TODO: Nombre de usuario al que se esta suscrito-->
                                <h6 class="media-heading">${sus.getUsuarioByIdSuscrito().getIdUser()}</h6>
                                <!--TODO: Estado del usuario, o bien desconectado, o bien el nombre de la cancion que esta escuchando/ha escuchado mas recientemente-->
                                <h6 class="media-heading">Cancion 1</h6>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div> <!-- Container -->

        <!-- Modal1 -->
        <div class="modal fade" id="modalSuscribir" tabindex="-1" role="dialog" aria-labelledby="modalSuscribir" aria-hidden="true">
            <div class= "modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">

                    <div class="modal-body">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <form action="${pageContext.request.contextPath}/Suscribe" method="get" >
                            <div class="form-group">
                                <label for="nombreSus">Nombre del usuario</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" id="nombreSus" placeholder="Usuario" name="name" required>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary">Suscribirse</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal2 -->
        <div class="modal fade" id="modalImagen" tabindex="-1" role="dialog" aria-labelledby="modalImagen" aria-hidden="true">
            <div class= "modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">

                    <div class="modal-body">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <form action = "usuario.jsp">
                            <div class="form-group">
                                <label for="imagenPerfil">Introduzca su nueva imagen de perfil.</label>
                                <div class="input-group">
                                    <input type="file" class="form-control-file" id="imagenPerfil" accept="image/*">
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary">Aceptar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="includes/footer.jsp"></jsp:include>
    </body>
</html>


