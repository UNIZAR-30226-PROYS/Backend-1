<%@ page import="main.java.model.Usuario" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% if(request.getParameter("ajax")==null){ %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>WolfSound - Lista</title>
    <%@include file="includes/html_head.jsp" %>
</head>
<body>
    <div id="topbar-y-contenido" class="col-10 pl-0 pr-0">
        <%@include file="includes/topbar.jsp" %>
        <!-- CONTENIDO DE LA VISTA -->
        <div id="contenido">
<% } //if%>
            <div id="tituloNuevo" value="WolfSound - Editar perfil" style="display:none;"></div>
            <!-- CONTENIDO DE LA VISTA -->
            <div class="col-12 d-block">

                <!-- Titulo de la pantalla -->
                <h2 class="text-left pl-4 pt-2">Modificar el perfil</h2>
                <div class="row pl-4 pt-2 pb-2">
                    <div class="col-7">
                        <div class="container mb-3">

                            <!-- Modificar Perfil -->

                            <div class="container mb-3">
                                <!-- Mosificar Cuenta -->
                                <!-- TODO: ajax para enviar este formulario sin recargar la pagina -->
                                <form class="needs-validation" action="${pageContext.request.contextPath}/modify" method="post" novalidate>
                                    <label for="nombreAp">Nombre y Apellidos</label>
                                    <div class="form-group">
                                        <div class="form-row">
                                            <input type="text" class="form-control" name = "nombre_Ap" id="nombreAp" placeholder="Nombre" required value="${username.getNomAp()}" >
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="nombre_usuario">Nombre de usuario</label>
                                        <input type="text" class="form-control" name ="new_user" id="nombre_usuario" placeholder="usuario_123" required value =${username.getUsername()} >

                                        <div class="invalid-feedback"> No puedes dejar este campo en blanco. </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="correo">Direccion de correo</label>
                                        <input type="email" class="form-control" name = "mail" id="correo" placeholder="user@mail .."required  value =${username.getEmail()}>
                                        <div class="invalid-feedback"> No puedes dejar este campo en blanco. </div>
                                    </div>


                                    <div class="form-group">
                                        <label for="visperfil">Visibilidad del perfil</label>
                                        <select class="form-control" name="visibilidad" id="visperfil" required>
                                            <option disabled> Seleccione la visibilidad de su perfil</option>
                                            <c:if test="${username.getPublico()}">
                                                <option>Privado</option>
                                                <option selected>Público</option>
                                            </c:if>
                                            <c:if test="${!username.getPublico()}">
                                                <option selected>Privado</option>
                                                <option>Público</option>
                                            </c:if>
                                        </select>
                                    </div>
                                    <%--<p class="text-danger">${error}</p>--%>

                                    <div class="row">
                                        <div class="mx-auto col-6">
                                            <button type="submit" class="col btn btn-primary ">Modificar</button>
                                        </div>
                                        <div class="mx-auto col-6">
                                            <button type="reset" class="col btn btn-outline-primary">Limpiar</button>
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