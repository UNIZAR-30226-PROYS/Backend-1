<%--TODO: Terminar de implementar funcionalidad--%>
<%@page contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Subir canción</title>
    <jsp:include page="includes/header.jsp"></jsp:include>
</head>
<body>
<jsp:include page="includes/navbars.jsp"></jsp:include>
<!-- CONTENIDO DE LA VISTA -->
<div class="container mb-3">

    <!-- Subir Cancion -->
    <form class="needs-validation" action="${pageContext.request.contextPath}/uploadSong" method="post"
          enctype="multipart/form-data" novalidate>
        <h4 class="text-center py-3">Sube tu canción</h4>
        <div class="form-group">
            <label for="nombre_cancion">Nombre de la canción</label>
            <input type="text" class="form-control" id="nombre_cancion" name="nombre" placeholder="Jailhouse Rock"
                   required>
            <div class="invalid-feedback"> No puedes dejar este campo en blanco.</div>
        </div>

        <div class="form-group">
            <label for="genero_musical">Genero Musical</label>
            <input type="text" class="form-control" id="genero_musical" name="genero" placeholder="Rock" required>
            <div class="invalid-feedback"> No puedes dejar este campo en blanco.</div>
        </div>

        <div class="form-group">
            <label for="album">Album</label>
            <input type="text" class="form-control" name="album" id="album" placeholder="Opcional">
            </input>
        </div>

        <div class="form-group">
            <label for="cancion">Seleccione la cancion(Formato mp3)</label>
            <input type="file" class="form-control-file" name="cancion" id="cancion" accept=".mp3" required>
            <div class="invalid-feedback">Seleccione la canción.</div>
        </div>

        <div class="form-group">
            <label for="portada">Seleccione la portada(Formato de imagen)</label>
            <input type="file" class="form-control-file" name="portada" id="portada" accept=image/*" required>
            <div class="invalid-feedback">Seleccione la canción.</div>
        </div>

        <div class="row">
            <div class="mx-auto col-6">
                <input type="submit" value="Upload Files"/>
            </div>
        </div>
    </form>

</div>

<jsp:include page="includes/footer.jsp"></jsp:include>
<script>
    /* Derechos de autor  : https://getbootstrap.com/docs/4.0/components/forms/#validation */

    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
</body>
</html>
