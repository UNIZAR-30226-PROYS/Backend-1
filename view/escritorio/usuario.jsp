<%@page contentType="text/html; UTF-8" %>
<%@ page import="main.java.model.Usuario" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% if(request.getParameter("ajax")==null){ %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Mi perfil - ${sessionScope.username}</title>
    <%@include file="includes/html_head.jsp" %>
</head>
<body>
    <div id="topbar-y-contenido" class="col-10 pl-0 pr-0">
        <%@include file="includes/topbar.jsp" %>
        <!-- CONTENIDO DE LA VISTA -->
        <div id="contenido">
<% } //if%>
            <%
                String user = (String)session.getAttribute("username");
                Usuario u = Usuario.getUser(user);
                String nombre = u.getNomAp();
                String email = u.getEmail();
            %>
            <div id="tituloNuevo" value="Mi perfil - ${sessionScope.username}" style="display:none;"></div>
            <div class="container mb-3">
                <div class="row pt-3">
                    <div class="col-4">
                        <img src="/contenido/imagenes/usuarios/${sessionScope.username}Perfil.svg"  style="width: 100%;max-height: 300px" alt="Usuario" data-toggle="modal" data-target="#modalImagen">
                    </div>
                    <div class="col-8">
                        <div class="row">
                            <h2 class="text-left pl-3 pt-2">${sessionScope.username}</h2>
                            <!-- TODO (JSP): boton de editar, seguir o dejar de seguir, segun quien visite el perfil -->
                            <a href="modificarPerfil.jsp">
                                <button type="button" class="btn btn-primary ml-1 mt-2 mb-3"><i class="fa fa-pencil"></i>&nbsp;Editar</button>
                            </a>
                        </div>
                        <div class="row">
                            <div class="col-4"><i class="fa fa-address-card"></i>&nbsp;<%=nombre%></div>
                            <div class="col-4"><i class="fa fa-address-card"></i>&nbsp;<%=email%></div>
                        </div>
                    </div>
                </div>
                <div class="border-bottom border-dark w-100 my-2 px-3"></div> <!-- Separador horizontal -->
                <!-- Navegacion en perfil de usuario -->
                <nav id="profileNav" class="navbar navbar-expand-lg navbar-light sticky-top pb-1 pt-0">
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarInternaPerfil" aria-controls="navbarInternaPerfil" aria-expanded="false" aria-label="Toggle profile nav">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarInternaPerfil">
                        <ul class="navbar-nav mx-auto">
                            <li class="nav-item"><a id="nav-explorar" class="nav-link nav-profile-link" href="actividad">Actividad</a></li>
                            <li class="nav-item"><a id="nav-listas" class="nav-link nav-profile-link" href="listas">Listas</a></li>
                            <li class="nav-item"><a id="nav-artistas" class="nav-link nav-profile-link" href="canciones">Canciones</a></li>
                            <li class="nav-item"><a id="nav-podcasts" class="nav-link nav-profile-link" href="siguiendo">Siguiendo</a></li>
                            <li class="nav-item"><a id="nav-podcasts" class="nav-link nav-profile-link" href="seguidores">Seguidores</a></li>
                        </ul>
                    </div>
                </nav>
                <div class="border-bottom border-dark w-100 px-3"></div> <!-- Separador horizontal -->
                <div id="contenido-perfil">
                    <!-- Aqui carga el contenido de la seccion del perfil corerspondiente -->
                    <script>
                        $("#contenido-perfil").load('includes/usuario_actividad.jsp');
                    </script>
                </div>
            </div> <!-- Container -->

            <!-- Modal2 -->
            <div class="modal fade" id="modalImagen" tabindex="-1" role="dialog" aria-labelledby="modalImagen" aria-hidden="true">
                <div class= "modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">

                        <div class="modal-body">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <form action = "${pageContext.request.contextPath}/changeImage" method="post" enctype = "multipart/form-data">
                                <div class="form-group">
                                    <label for="imagenPerfil">Introduzca su nueva imagen de perfil.</label>
                                    <div class="input-group">
                                        <input type="file" class="form-control-file" name="imagen_new" id="imagenPerfil" accept="image/*">
                                    </div>
                                </div>
                                <input type = "submit" value = "Upload" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <script src="../scripts/navegacion_usuario.js"></script>
    <% if(request.getParameter("ajax")==null){ %>
        </div>
    </div>
    <%@include file="includes/socialbar.jsp" %>
    <%@include file="includes/reproductor.jsp" %>
</body>
</html>
<% } %>