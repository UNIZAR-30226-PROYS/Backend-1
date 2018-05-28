<%--TODO: Terminar de implementar funcionalidad--%>
<%@page contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Lista ${lista.getNombre()}</title>
    <jsp:include page="includes/header.jsp"></jsp:include>
</head>

<body>
<jsp:include page="includes/navbars.jsp"></jsp:include>

<!-- CONTENIDO DE LA VISTA -->
<div class="container mb-3">
    <div class="row pt-3">
        <%--TODO: reproducir normal--%>
        <div class="col-5 mr-auto">
            <div class="row">
                <div class="col-1 mr-auto">
                        <button type="button" id="play_button"  class="btn btn-default ">
                            <span class="fa fa-play" style="font-size:20px; "></span>
                        </button>
                    <script>
                        $("#play_button").click( function()
                            {
                                actualizar_lista();
                                alert("se ha actualizado la lista");
                            }
                        );
                    </script>
                </div>
                <%--TODO: reproducir aleatorio--%>
                <div class="col-1 mx-auto">
                    <a href="#">
                        <button type="button" class="btn btn-default ">
                            <span class="fa fa-random" style="font-size:20px; "></span>
                        </button>
                    </a>
                </div>
                <%--TODO: seguir la lista--%>
                <div class="col-1 ml-auto">
                    <a href="#">
                        <button type="button" class="btn btn-default ">
                            <span class="fa fa-rss" style="font-size:20px; "></span>
                        </button>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="pt-1">
        <h4 style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap">${lista.getNombre()}</h4>
    </div>


    <c:forEach items="${canciones}" var="cancion">
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
                    <c:if test="${propietario}">
                        <div class="media-right">
                            <button type="button" class="btn btn-default " data-toggle="modal"
                                    data-target="#modalOrden">
                                <span class="fa fa-list-ol" style="font-size:20px; "></span>
                            </button>
                            <a href="/deleteSongFromList?user=${lista.getUsuarioByIdUser().getIdUser()}&list=${lista.getNombre()}&song=${cancion.getIdCancion()}">
                                <button type="button" class="btn btn-default ">
                                    <span class="fa fa-trash" style="font-size:20px; "></span>
                                </button>
                            </a>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </c:forEach>
    ${requestScope.error}
</div> <!-- Container -->

<!-- Modal1 -->
<div class="modal fade" id="modalOrden" tabindex="-1" role="dialog" aria-labelledby="modalOrden"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">

            <div class="modal-body">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <form action="lista.jsp">
                    <div class="form-group">
                        <label for="posicion">Nueva posicion de la cancion</label>
                        <div class="input-group">
                            <input type="number" class="form-control" id="posicion" placeholder="3" required>
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


