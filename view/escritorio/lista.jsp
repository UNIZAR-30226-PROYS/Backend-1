<!-- TODO (JSP): "Lista" en el titulo es el nombre de la lista visitada -->
<% if(request.getParameter("ajax")==null){ %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Wolfic - Lista</title>
    <%@include file="includes/html_head.jsp" %>
</head>
<body>
    <div id="topbar-y-contenido" class="col-10 pl-0 pr-0">
    <%@include file="includes/topbar.jsp" %>
    <!-- CONTENIDO DE LA VISTA -->
        <div id="contenido">
<% } //if%>
            <div id="tituloNuevo" value="Wolfic - Lista" style="display:none;"></div>
            <div class="container mb-3">
                <div class="row pt-3">
                    <div class="col-4">
                        <img src="images/wolfsound.png" class="img-thumbnail" style="max-width: 100%;max-height: 500px" alt="Usuario">
                        <div class="pt-2 pl-2">
                            <h3 class="media-heading">NombreLista</h3>
                            <h5>De <a href="artista.html" style="color: black; text-underline: none;"> NombreUsuario </a></h5>
                            Descripci&oacute;n
                        </div>
                        <div class="pl-2">
                            <button type="button" class="btn btn-primary mt-1"><i class="fa fa-play"></i>&nbsp;Reproducir</button>
                            <button type="button" class="btn btn-primary mt-1"><i class="fa fa-random"></i>&nbsp;Aleatorio</button><br />
                            <button type="button" class="btn btn-primary mt-1"><i class="fa fa-rss"></i>&nbsp;Seguir</button>
                        </div>
                    </div>
                    <div class="col-8">
                        <div class="list-group">
                            <div class="list-group-item list-group-item-action">
                                <div class="media pt-2 pb-1 mt-auto">
                                    <div class="media-left" style="padding-right:15px">
                                        <a href="cancion.jsp">
                                            <img src="images/wolf.jpg" style="width:64px;" alt="...">
                                        </a>
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-5">Nombre Cancion</h6>
                                        <h6 class="media-heading pl-5">Usuario</h6>
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-3">Mejores momentos</h6>
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-3">19-04-2018</h6>
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
                    </div>
                </div>
            </div> <!-- Container -->
            <!-- Modal1 -->
            <div class="modal fade" id="modalOrden" tabindex="-1" role="dialog" aria-labelledby="modalOrden" aria-hidden="true">
                <div class= "modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">

                        <div class="modal-body">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <form action = "lista.jsp">
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
    <% if(request.getParameter("ajax")==null){ %>
        </div>
    </div>
    <%@include file="includes/socialbar.jsp" %>
    <%@include file="includes/reproductor.jsp" %>
</body>
</html>
<% } %>