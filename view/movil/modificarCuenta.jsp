<%@page contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Bienvenido a Wolfic</title>
    <jsp:include page="includes/header.jsp"></jsp:include>
</head>
<body>
<jsp:include page="includes/navbars.jsp"></jsp:include>
<!-- CONTENIDO DE LA VISTA -->
<div class="container mb-3">
    <!-- Mosificar Cuenta -->
    <form class="needs-validation" action="${pageContext.request.contextPath}/modify" method="post" novalidate>
        <h4 class="text-center py-3">Modificar Información del perfil</h4>
        <label for="nombreAp">Nombre y Apellidos</label>
        <div class="form-group">
            <div class="form-row">
                <input type="text" class="form-control" name="nombre_Ap" id="nombreAp" placeholder="Nombre" required
                       value="${username.getNomAp()}">
            </div>
        </div>
        <div class="form-group">
            <label for="nombre_usuario">Nombre de usuario</label>
            <input type="text" class="form-control" name="new_user" id="nombre_usuario" placeholder="usuario_123"
                   required value="${username.getIdUser()}">

            <div class="invalid-feedback"> No puedes dejar este campo en blanco.</div>
        </div>

        <div class="form-group">
            <label for="correo">Direccion de correo</label>
            <input type="email" class="form-control" name="mail" id="correo" placeholder="user@mail .." required
                   value=<%=u.getEmail()%>>
            <div class="invalid-feedback"> No puedes dejar este campo en blanco.</div>
        </div>


        <div class="form-group">
            <label for="visperfil">Visibilidad del perfil</label>
            <select class="form-control" name="visibilidad" id="visperfil" required>
                <option disabled> Seleccione la visibilidad de su perfil</option>
                <c:if test="${username.isPublico()}">
                    <option>Privado</option>
                    <option selected>Público</option>
                </c:if>
                <c:otherwise>
                    <option selected>Privado</option>
                    <option>Público</option>
                </c:otherwise>
            </select>
        </div>

        <p class="text-danger">${error}</p>

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
    <div class="mx-auto col-6 text-center">
        <button type="submit" class="btn btn-danger" data-toggle="modal" data-target="#modalBorrar">Borrar Cuenta
        </button>
    </div>

</div>

<!-- Modal1 -->
<div class="modal fade" id="modalBorrar" tabindex="-1" role="dialog" aria-labelledby="modalBorrar" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5>¿Seguro que quieres borrar tu cuenta?</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
            <div class="modal-body">

                <div class="row align-items-center">
                    <div class="col align-items-center">
                        <form action="#">
                            <button type="button" class="btn btn-primary" data-dismiss="modal">Cancelar</button>
                        </form>
                    </div>
                    <div class="col align-items-center">
                        <form action="${pageContext.request.contextPath}/deleteUser" method="post">
                            <button type="submit" class="btn btn-danger">Aceptar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
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
