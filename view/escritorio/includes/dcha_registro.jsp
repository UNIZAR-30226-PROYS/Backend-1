<%@page contentType="text/html; UTF-8" %>
<!DOCTYPE html>
<html lang="es">

<!-- Cabecera -->
<div class="socialbar-header">
    <div class="text-center pt-3 pb-3"> Registrarse </div>
</div>

<div class="socialbar-cuerpo">
    <form class="needs-validation" action="${pageContext.request.contextPath}/register" method="post" >
        <!-- Apartados -->
        <div class="form-group pt-2">
            <label for="nombre_usuario" style="color: black">Nombre de usuario</label>
            <input type="text" class="form-control socialbarM" name="register_user" id="nombre_usuario" placeholder="usuario_123" maxlength="50" required>
            <div class="invalid-feedback"> No puedes dejar este campo en blanco. </div>
        </div>
        <div class="form-group pb-2">
            <label for="contrasenya" style="color: black">Contraseña</label>
            <input type="password" class="form-control socialbarM" name="register_pass" id="contrasenya" placeholder="*****" required>
            <div class="invalid-feedback"> No puedes dejar este campo en blanco. </div>
        </div>
        <div class="form-group">
            <label for="confirmar_contrasenya" style="color: black"> Confirmar Contraseña</label>
            <input type="password" class="form-control socialbarM" id="confirmar_contrasenya" placeholder="*****" required>
            <div class="invalid-feedback"> No puedes dejar este campo en blanco. </div>
        </div>
        <div class="form-group">
            <label for="correo" style="color: black">Dirección de correo</label>
            <input type="email" class="form-control socialbarM" name="register_email" id="correo" placeholder="user@mail .." maxlength="100" required>
            <div class="invalid-feedback"> No puedes dejar este campo en blanco. </div>
        </div>
        <div class="form-group">
            <div class="form-check socialbarM">
                <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
                <label class="form-check-label" style="color: black;" for="invalidCheck">
                    Acepto los <a href="#">términos y condiciones</a> de uso.
                </label>
            </div>
        </div>
        <div>
            <button type="submit" class="col btn btn-primary mt-1 socialbarM" > Registrarse </button>
        </div>
        <div class="mt-3">
            <h6 class="socialbarM" style="color: black;"> ¿Ya tienes cuenta? <a href="#" onclick="mostrar_iniciar_sesion()"> Inicia sesión </a></h6>
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