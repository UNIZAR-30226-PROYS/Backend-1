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
                            <h5>De <a href="#" style="color: black; text-underline: none;"> NombreUsuario </a></h5>
                            Descripci&oacute;n
                        </div>
                        <div class="pl-2">
                            <button type="button" class="btn btn-primary mt-1"><i class="fa fa-play"></i>&nbsp;Reproducir</button>
                            <button type="button" class="btn btn-primary mt-1"><i class="fa fa-random"></i>&nbsp;Aleatorio</button><br />
                            <button type="button" class="btn btn-primary mt-1"><i class="fa fa-rss"></i>&nbsp;Seguir</button>
                            <button type="button" class="btn btn-primary mt-1"><i class="fa fa-ellipsis-h"></i>&nbsp;Opciones</button>
                        </div>
                    </div>
                    <div class="col-8">
                        <div class="list-group">
                            <a href="cancion.jsp" class="list-group-item list-group-item-action">
                                <div class="media pt-2 pb-1 mt-auto">
                                    <div class="media-left pl-1" style="padding-right:15px">
                                        <img src="images/wolf.jpg" style="width:50px;" alt="...">
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-5">Nombre Cancion</h6>
                                        <h6 class="media-heading pl-5">Usuario</h6>
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-3">Artista</h6>
                                        <h6 class="media-heading pl-3">Perico feat cañete</h6>
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-3">Album</h6>
                                        <h6 class="media-heading pl-3">Mejores momentos</h6>
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-3">Fecha</h6>
                                        <h6 class="media-heading pl-3">19-04-2018</h6>
                                    </div>
                                    <button type="button" class="btn btn-default ">
                                        <span class="fa fa-plus mt-auto pt-1" style="font-size:20px; "></span>
                                    </button>
                                </div>
                            </a>
                            <a href="#" class="list-group-item list-group-item-action">
                                <div class="media pt-2 pb-1 mt-auto">
                                    <div class="media-left pl-1" style="padding-right:15px">
                                        <img src="images/wolf.jpg" style="width:50px;" alt="...">
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-5">Nombre Cancion</h6>
                                        <h6 class="media-heading pl-5">Usuario</h6>
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-3">Artista</h6>
                                        <h6 class="media-heading pl-3">Perico feat cañete</h6>
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-3">Album</h6>
                                        <h6 class="media-heading pl-3">Mejores momentos</h6>
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-3">Fecha</h6>
                                        <h6 class="media-heading pl-3">19-04-2018</h6>
                                    </div>
                                    <button type="button" class="btn btn-default ">
                                        <span class="fa fa-plus mt-auto pt-1" style="font-size:20px; "></span>
                                    </button>
                                </div>
                            </a>
                            <a href="#" class="list-group-item list-group-item-action">
                                <div class="media pt-2 pb-1 mt-auto">
                                    <div class="media-left pl-1" style="padding-right:15px">
                                        <img src="images/wolf.jpg" style="width:50px;" alt="...">
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-5">Nombre Cancion</h6>
                                        <h6 class="media-heading pl-5">Usuario</h6>
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-3">Artista</h6>
                                        <h6 class="media-heading pl-3">Perico feat cañete</h6>
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-3">Album</h6>
                                        <h6 class="media-heading pl-3">Mejores momentos</h6>
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-3">Fecha</h6>
                                        <h6 class="media-heading pl-3">19-04-2018</h6>
                                    </div>
                                    <button type="button" class="btn btn-default ">
                                        <span class="fa fa-plus mt-auto pt-1" style="font-size:20px; "></span>
                                    </button>
                                </div>
                            </a>
                            <a href="#" class="list-group-item list-group-item-action">
                                <div class="media pt-2 pb-1 mt-auto">
                                    <div class="media-left pl-1" style="padding-right:15px">
                                        <img src="images/wolf.jpg" style="width:50px;" alt="...">
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-5">Nombre Cancion</h6>
                                        <h6 class="media-heading pl-5">Usuario</h6>
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-3">Artista</h6>
                                        <h6 class="media-heading pl-3">Perico feat cañete</h6>
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-3">Album</h6>
                                        <h6 class="media-heading pl-3">Mejores momentos</h6>
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-3">Fecha</h6>
                                        <h6 class="media-heading pl-3">19-04-2018</h6>
                                    </div>
                                    <button type="button" class="btn btn-default ">
                                        <span class="fa fa-plus mt-auto pt-1" style="font-size:20px; "></span>
                                    </button>
                                </div>
                            </a>
                            <a href="#" class="list-group-item list-group-item-action">
                                <div class="media pt-2 pb-1 mt-auto">
                                    <div class="media-left pl-1" style="padding-right:15px">
                                        <img src="images/wolf.jpg" style="width:50px;" alt="...">
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-5">Nombre Cancion</h6>
                                        <h6 class="media-heading pl-5">Usuario</h6>
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-3">Artista</h6>
                                        <h6 class="media-heading pl-3">Perico feat cañete</h6>
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-3">Album</h6>
                                        <h6 class="media-heading pl-3">Mejores momentos</h6>
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-3">Fecha</h6>
                                        <h6 class="media-heading pl-3">19-04-2018</h6>
                                    </div>
                                    <button type="button" class="btn btn-default ">
                                        <span class="fa fa-plus mt-auto pt-1" style="font-size:20px; "></span>
                                    </button>
                                </div>
                            </a>
                            <a href="#" class="list-group-item list-group-item-action">
                                <div class="media pt-2 pb-1 mt-auto">
                                    <div class="media-left pl-1" style="padding-right:15px">
                                        <img src="images/wolf.jpg" style="width:50px;" alt="...">
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-5">Nombre Cancion</h6>
                                        <h6 class="media-heading pl-5">Usuario</h6>
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-3">Artista</h6>
                                        <h6 class="media-heading pl-3">Perico feat cañete</h6>
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-3">Album</h6>
                                        <h6 class="media-heading pl-3">Mejores momentos</h6>
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-3">Fecha</h6>
                                        <h6 class="media-heading pl-3">19-04-2018</h6>
                                    </div>
                                    <button type="button" class="btn btn-default ">
                                        <span class="fa fa-plus mt-auto pt-1" style="font-size:20px; "></span>
                                    </button>
                                </div>
                            </a>
                            <a href="#" class="list-group-item list-group-item-action">
                                <div class="media pt-2 pb-1 mt-auto">
                                    <div class="media-left pl-1" style="padding-right:15px">
                                        <img src="images/wolf.jpg" style="width:50px;" alt="...">
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-5">Nombre Cancion</h6>
                                        <h6 class="media-heading pl-5">Usuario</h6>
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-3">Artista</h6>
                                        <h6 class="media-heading pl-3">Perico feat cañete</h6>
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-3">Album</h6>
                                        <h6 class="media-heading pl-3">Mejores momentos</h6>
                                    </div>
                                    <div class="media-body mt-auto">
                                        <h6 class="media-heading pl-3">Fecha</h6>
                                        <h6 class="media-heading pl-3">19-04-2018</h6>
                                    </div>
                                    <button type="button" class="btn btn-default ">
                                        <span class="fa fa-plus mt-auto pt-1" style="font-size:20px; "></span>
                                    </button>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
            </div> <!-- Container -->
    <% if(request.getParameter("ajax")==null){ %>
        </div>
    </div>
    <%@include file="includes/socialbar.jsp" %>
    <%@include file="includes/reproductor.jsp" %>
</body>
</html>
<% } %>