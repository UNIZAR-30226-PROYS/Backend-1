<%@page contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<!-- Cabecera -->
<div class="socialbar-header">
    <div class="text-center pt-3 pb-3"> Iniciar sesión </div>
</div>

<div class="socialbar-cuerpo">
    <form class="needs-validation" action="${pageContext.request.contextPath}/login" method="post" >
        <!-- Apartados -->
        <div class="form-group pt-2">
            <label for="nombre_usuario" style="color: black">Nombre de usuario</label>
            <input type="text" class="form-control socialbarM" name="login_user" value="${user}" id="nombre_usuario" placeholder="usuario_123" required>
            <div class="invalid-feedback"> No puedes dejar este campo en blanco. </div>
        </div>
        <div class="form-group pb-2">
            <label for="contrasenya" style="color: black">Contraseña</label>
            <input type="password" class="form-control socialbarM" name="login_pass" id="contrasenya" placeholder="*****" required>
            <c:if test="${error == 'Contraseña errónea'}">
                <p class="text-danger">${error}</p>
            </c:if>
            <div class="invalid-feedback"> No puedes dejar este campo en blanco. </div>
            <button type="submit" class="col btn btn-primary socialbarM mt-3">Iniciar sesión</button>
        </div>
        <div>
            <h6 class="socialbarM"><a href="#" onclick="mostrar_registrarse()"> Regístrate ahora </a></h6>
        </div>
    </form>
    <script>
        /* Derechos de autor  : https://getbootstrap.com/docs/4.0/components/forms/#validation */

        // Example starter JavaScript for disabling form submissions if there are invalid fields
        (function() {
            'use strict';
            window.addEventListener('load', function() {
                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.getElementsByClassName('needs-validation');
                // Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function(form) {
                    form.addEventListener('submit', function(event) {
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
</div>