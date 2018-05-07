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
            <div id="tituloNuevo" value="Wolfic - Editar perfil" style="display:none;"></div>
            <!-- CONTENIDO DE LA VISTA -->
            <div class="col-12 d-block">

                <!-- Titulo de la pantalla -->
                <h2 class="text-left pl-4 pt-2">Subir una canción</h2>
                <div class="row pl-4 pt-2 pb-2">
                    <div class="col-7">
                        <div class="container mb-3">

                            <!-- Modificar Perfil -->

                            <div class="container mb-3">

                                <!-- Mosificar Cuenta -->
                                <!-- TODO: ajax para enviar este formulario sin recargar la pagina -->
                                <form action = "explorar.jsp">
                                    <label for="nombre">Nombre</label>
                                    <div class="form-group">
                                        <div class="form-row">
                                            <div class="col-6">
                                                <input type="text" class="form-control" id="nombre" placeholder="Nombre" value="Nombre actual" required>
                                            </div>
                                            <div class="col">
                                                <input type="text" class="form-control" id="apellidos" placeholder="Apellidos"  value="Apellidos actual" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="nombre_usuario">Nombre de usuario</label>
                                        <input type="text" class="form-control" id="nombre_usuario" placeholder="usuario_123" value="User actual" required>
                                        <div class="invalid-feedback"> No puedes dejar este campo en blanco. </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="correo">Direccion de correo</label>
                                        <input type="email" class="form-control" id="correo" placeholder="user@mail .." value="Correo@actual.com" required>
                                        <div class="invalid-feedback"> No puedes dejar este campo en blanco. </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="visperfil">Visibilidad del perfil</label>
                                        <select class="form-control" id="visperfil"required>
                                            <option value=""  disabled>Seleccione la visibilidad de su perfil</option>
                                            <option selected>Privado</option>
                                            <option>Público</option>
                                        </select>
                                    </div>


                                    <div class="row">
                                        <div class="mx-auto col-6">
                                            <button type="submit" class="col btn btn-primary ">Modificar</button>
                                        </div>
                                        <div class="mx-auto col-6">
                                            <button type="reset" class="col btn btn-outline-primary">Deshacer</button>
                                        </div>
                                    </div>
                                </form>
                                <br><br>
                                <div class="mx-auto col-6">
                                    <button type="submit" class="col btn btn-danger " data-toggle="modal" data-target="#modalBorrar">Borrar Cuenta</button>
                                </div>

                            </div>

                            <!-- Modal1 -->
                            <div class="modal fade" id="modalBorrar" tabindex="-1" role="dialog" aria-labelledby="modalBorrar" aria-hidden="true">
                                <div class= "modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5>¿Seguro que quieres borrar tu cuenta?</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>

                                        </div>
                                        <div class="modal-body">

                                            <div class = "row align-items-center">
                                                <div class = "col align-items-center">
                                                    <!-- TODO: ajax para enviar este formulario sin recargar la pagina -->
                                                    <form action = "modificarPerfil.html.html">
                                                        <button type="button" class="btn btn-primary" data-dismiss="modal">Cancelar</button>
                                                    </form>
                                                </div>
                                                <div class = "col align-items-center">
                                                    <form action = "explorar.jsp">
                                                        <button type="submit" class="btn btn-danger">Aceptar</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
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