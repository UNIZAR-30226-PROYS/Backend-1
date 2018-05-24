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

    <%--<div class="media mt-2">--%>
        <%--<div class="media-body">--%>
            <%--<h4 class="media-heading">${lista.getNombre()}</h4>--%>
        <%--</div>--%>
        <%--<div class="media-right">--%>
            <%--<a href="cancion.jsp">--%>
                <%--<button type="button" class="btn btn-default ">--%>
                    <%--<span class="fa fa-play" style="font-size:20px; "></span>--%>
                <%--</button>--%>
            <%--</a>--%>
            <%--<a href="cancion.jsp">--%>
                <%--<button type="button" class="btn btn-default ">--%>
                    <%--<span class="fa fa-random" style="font-size:20px; "></span>--%>
                <%--</button>--%>
            <%--</a>--%>
        <%--</div>--%>
    <%--</div>--%>

    <div class="row pt-3">
        <div class="col-8">
            <h4>${lista.getNombre()}</h4>
        </div>
        <div class="col-4">
            <%--TODO: reproducir normal--%>
            <a href="#">
                <button type="button" class="btn btn-default ">
                    <span class="fa fa-play" style="font-size:20px; "></span>
                </button>
            </a>
            <%--TODO: reproducir aleatorio--%>
            <a href="#">
                <button type="button" class="btn btn-default ">
                    <span class="fa fa-random" style="font-size:20px; "></span>
                </button>
            </a>
        </div>
    </div>

    <c:forEach items="${canciones}" var="cancion">
        <div class="list-group pt-2">
            <div class="list-group-item list-group-item-action">
                <div class="media">
                    <div class="media-left" style="padding-right:15px">
                        <a href="/song?id?${cancion.getIdCancion()}">
                            <img src="/contenido/imagenes/canciones/${cancion.getIdCancion()}.png" style="width:64px;" alt="...">
                        </a>
                    </div>
                    <div class="media-body">
                        <!-- Nombre cancion-->
                        <h6 class="media-heading">${cancion.getNombre()}</h6>
                        <!-- Usuario que ha subido la canción-->
                        <h6 class="media-heading">${cancion.getUsuarioByIdUser().getIdUser()}</h6>
                    </div>
                    <div class="media-right">
                        <button type="button" class="btn btn-default " data-toggle="modal" data-target="#modalOrden">
                            <span class="fa fa-list-ol" style="font-size:20px; "></span>
                        </button>
                        <button type="button" class="btn btn-default ">
                            <span class="fa fa-trash" style="font-size:20px; "></span>
                        </button>

                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div> <!-- Container -->

<!-- Modal1 -->
<div class="modal fade" id="modalOrden" tabindex="-1" role="dialog" aria-labelledby="modalOrden" aria-hidden="true">
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


