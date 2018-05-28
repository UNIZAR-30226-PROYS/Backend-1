<%@page contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>WolfSound</title>
    <jsp:include page="includes/header.jsp"></jsp:include>
</head>
<body>
<jsp:include page="includes/navbars.jsp"></jsp:include>

<!-- CONTENIDO DE LA VISTA -->
<div class="container mb-3">
    <div class="pt-1">
        <img class="d-block mx-auto" src="${pageContext.request.contextPath}/contenido/web/imagenes/wolfsound.png"
             style="max-width: 200px"/>
    </div>

    <!--  https://getbootstrap.com/docs/4.0/components/navs/ -->
    <ul class="nav nav-pills nav-fill" id="pills-tab">
        <li class="nav-item">
            <a class="nav-link active show" id="iniciar-sesion-tab" data-toggle="pill" href="#iniciar-sesion" role="tab"
               aria-controls="iniciar-sesion" aria-selected="true">Iniciar Sesion</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="registrarse-tab" data-toggle="pill" href="#registrarse" role="tab"
               aria-controls="registrarse" aria-selected="false">Registrarse</a>
        </li>
    </ul>

    <div class="tab-content" id="myTabContent">        <!-- Sino aparece uno debajo de otro -->

        <!-- Iniciar Sesion -->
        <div class="tab-pane fade active show" id="iniciar-sesion" role="tabpanel" aria-labelledby="iniciar-sesion-tab">
            <form class="needs-validation" action="${pageContext.request.contextPath}/login" method="post" novalidate>
                <h4 class="text-center py-3">Iniciar sesión en WolfSound</h4>
                <p class="text-danger">${requestScope.error}</p>
                <div class="form-group">
                    <label for="nombre_usuario">Nombre de usuario</label>
                    <input type="text" class="form-control" name="login_user" value="${user}" id="nombre_usuario"
                           placeholder="usuario_123" required>
                    <%--<c:if test="${error == 'El usuario no existe'}">--%>
                    <%--<p class="text-danger">${error}</p>--%>
                    <%--</c:if>--%>
                    <div class="invalid-feedback"> No puedes dejar este campo en blanco.</div>
                </div>

                <div class="form-group">
                    <label for="contrasenya">Contraseña</label>
                    <input type="password" class="form-control" name="login_pass" id="contrasenya" placeholder="*****"
                           required>
                    <%--<c:if test="${error == 'Contraseña errónea'}">--%>
                    <%--<p class="text-danger">${error}</p>--%>
                    <%--</c:if>--%>
                    <div class="invalid-feedback"> No puedes dejar este campo en blanco.</div>
                </div>
                <div class="row">
                    <div class="col-6">
                        <button type="submit" class="col btn btn-primary ">Iniciar sesión</button>
                    </div>
                    <div class="col-6">
                        <button type="reset" class="col btn btn-outline-primary">Limpiar</button>
                    </div>
                </div>
            </form>
        </div>

        <%--TODO: Mantener campos necesarios en caso de error e indicar el error arpopiado --%>
        <!-- Registrarse -->
        <div class="tab-pane fade" id="registrarse" role="tabpanel" aria-labelledby="registrarse-tab">

            <form class="needs-validation" action="${pageContext.request.contextPath}/register" method="post"
                  novalidate>
                <h4 class="text-center py-3">Registrarse en WolfSound</h4>
                <div class="form-group">
                    <label for="nombre_usuario">Nombre de usuario</label>
                    <input type="text" class="form-control" name="register_user" id="nombre_usuario"
                           placeholder="usuario_123" required>
                    <div class="invalid-feedback"> No puedes dejar este campo en blanco.</div>
                </div>

                <div class="form-group">
                    <label for="contrasenya">Contraseña</label>
                    <input type="password" class="form-control" name="register_pass" id="contrasenya"
                           placeholder="*****" required>
                    <div class="invalid-feedback"> No puedes dejar este campo en blanco.</div>
                </div>

                <div class="form-group">
                    <label for="confirmar_contrasenya"> Confirmar Contraseña</label>
                    <input type="password" class="form-control" id="confirmar_contrasenya" placeholder="*****" required>
                    <div class="invalid-feedback"> No puedes dejar este campo en blanco.</div>
                </div>

                <div class="form-group">
                    <label for="correo">Direccion de correo</label>
                    <input type="email" class="form-control" name="register_email" id="correo"
                           placeholder="user@mail .." required>
                    <div class="invalid-feedback"> Se debe introducir una direccion valida.</div>
                </div>


                <div class="form-group">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
                        <label class="form-check-label" for="invalidCheck">
                            Acepto los <a href="#">terminos y condiciones</a> de uso.
                        </label>
                    </div>
                </div>

                <div class="row">
                    <div class="mx-auto col-6">
                        <button type="submit" class="col btn btn-primary ">Enviar</button>
                    </div>
                    <div class="mx-auto col-6">
                        <button type="reset" class="col btn btn-outline-primary">Limpiar</button>
                    </div>
                </div>
            </form>
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
