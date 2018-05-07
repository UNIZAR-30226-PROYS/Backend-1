<!-- TODO (JSP): "busqueda" en el titulo es la busqueda realizada -->
<% if(request.getParameter("ajax")==null){ %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Wolfic - Buscar: <%=request.getParameter("b")%></title>
    <%@include file="includes/html_head.jsp" %>
</head>
<body>
    <div id="topbar-y-contenido" class="col-10 pl-0 pr-0">
        <%@include file="includes/topbar.jsp" %>
        <!-- CONTENIDO DE LA VISTA -->
        <div id="contenido">
<% } //if%>
            <div id="tituloNuevo" value="Wolfic - Buscar: <%=request.getParameter("b")%>" style="display:none;"></div>
            <div class="container mb-3">

                <div class="media mt-2">
                    <div class="media-body">
                        <h4 class="media-heading">Resultados de "consulta"</h4>
                    </div>
                    <div class="media-right">
                        <button type="button" class="btn btn-default ">
                            <span class="fa fa-user-plus" style="font-size:20px; "></span>
                        </button>
                    </div>
                </div>

                <div class="list-group pt-2">
                    <a href="#" class="list-group-item list-group-item-action">
                        <div class="media">
                            <div class="media-left" style="padding-right:15px">
                                <img src="images/wolf.jpg" style="width:30px;" alt="...">
                            </div>
                            <div class="media-body">
                                <h6 class="media-heading">Nombre Cancion</h6>
                                <h6 class="media-heading">usuario</h6>
                            </div>
                        </div>
                    </a>
                    <a href="#" class="list-group-item list-group-item-action">
                        <div class="media">
                            <div class="media-left" style="padding-right:15px">
                                <img src="images/wolf.jpg" style="width:30px;" alt="...">
                            </div>
                            <div class="media-body">
                                <h6 class="media-heading">Nombre Cancion</h6>
                                <h6 class="media-heading">usuario</h6>
                            </div>
                        </div>
                    </a>
                    <a href="#" class="list-group-item list-group-item-action">
                        <div class="media">
                            <div class="media-left" style="padding-right:15px">
                                <img src="images/wolf.jpg" style="width:30px;" alt="...">
                            </div>
                            <div class="media-body">
                                <h6 class="media-heading">Nombre Cancion</h6>
                                <h6 class="media-heading">usuario</h6>
                            </div>
                        </div>
                    </a>
                    <a href="#" class="list-group-item list-group-item-action">
                        <div class="media">
                            <div class="media-left" style="padding-right:15px">
                                <img src="images/wolf.jpg" style="width:30px;" alt="...">
                            </div>
                            <div class="media-body">
                                <h6 class="media-heading">Nombre Cancion</h6>
                                <h6 class="media-heading">usuario</h6>
                            </div>
                        </div>
                    </a>
                </div>
            </div> <!-- Container -->
            <script src="../scripts/busqueda.js"></script>
    <% if(request.getParameter("ajax")==null){ %>
        </div>
    </div>
    <%@include file="includes/socialbar.jsp" %>
    <%@include file="includes/reproductor.jsp" %>
</body>
</html>
<% } %>