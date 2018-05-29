<%@page contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% if(request.getParameter("ajax")==null){ %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>WolfSound - Subir m&uacute;sica</title>
    <%@include file="includes/html_head.jsp" %>
</head>
<body>
    <div id="topbar-y-contenido" class="col-10 pl-0 pr-0">
        <%@include file="includes/topbar.jsp" %>
        <!-- CONTENIDO DE LA VISTA -->
        <div id="contenido">
<% } //if%>
            <div id="tituloNuevo" value="WolfSound - Subir m&uacute;sica" style="display:none;"></div>
            <!-- CONTENIDO DE LA VISTA -->
            <div class="col-12 d-block">

                <!-- Titulo de la pantalla -->
                <h2 class="text-left pl-4 pt-2">Subir una canción</h2>
                <div class="row pl-4 pt-2 pb-2">
                    <div class="col-7">
                        <div class="container mb-3">

                            <!-- Subir Cancion -->
                            <!-- TODO: ajax para enviar este formulario sin recargar la pagina -->
                            <form class="needs-validation" action = "${pageContext.request.contextPath}/uploadSong" method="post" enctype = "multipart/form-data">
                                <h4 class="text-center py-3">Sube tu canción</h4>
                                <div class="form-group">
                                    <label for="nombre_cancion">Nombre de la canción</label>
                                    <input type="text" class="form-control" id="nombre_cancion" name="nombre" maxlength="100" placeholder="Jailhouse Rock" required>
                                    <div class="invalid-feedback"> No puedes dejar este campo en blanco. </div>
                                </div>

                                <div class="form-group">
                                    <label for="genero_musical">Genero Musical</label>
                                    <input type="text" class="form-control" id="genero_musical" name="genero" maxlength="100" placeholder="Rock" required>
                                    <div class="invalid-feedback"> No puedes dejar este campo en blanco. </div>
                                </div>

                                <div class="form-group">
                                    <label for="album">Album</label>
                                    <input type="text" class="form-control" name = "album" id="album" maxlength="100" placeholder = "Opcional">
                                    </input>
                                </div>

                                <div class="form-group">
                                    <label for="cancion">Seleccione la cancion(Formato mp3)</label>
                                    <input type="file" class="form-control-file" name="cancion" id="cancion" accept=".mp3" required>
                                    <div class="invalid-feedback">Seleccione la canción.</div>
                                </div>

                                <div class="form-group">
                                    <label for="portada">Seleccione la portada(Formato de imagen)</label>
                                    <input type="file" class="form-control-file" name="portada" id="portada" accept="image/*" required>
                                    <div class="invalid-feedback">Seleccione la canción.</div>
                                </div>
                                <p class="text-danger">${requestScope.error}</p>
                                <div class="row">
                                    <div class="mx-auto col-6">
                                        <input type = "submit" value = "Upload Files" />
                                    </div>
                                </div>
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