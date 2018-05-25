<%--TODO: Terminar de implementar funcionalidad--%>
<%@ page import="main.java.model.Usuario" %>
<%@ page import="main.java.model.Cancion" %>
<%@page contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Cancion</title>
    <%@include file="includes/header.jsp" %>
</head>
<body>

<%@include file="includes/navbars.jsp" %>
<!-- CONTENIDO DE LA VISTA -->
<div class="container mb-3">
    <div class="col">
        <div class="row">
            <div class="col">
                <img class="img-fluid align-content-lg-end" src="/contenido/imagenes/canciones/${cancion.getIdCancion()}.png" alt="Placeholder">
            </div>
        </div>
        <div class="row">
            <div class="col-6">
                <h3>${cancion.getNombre()}</h3>
            </div>
            <c:if test="${sessionScope.username != null}">
                <form id="addToListForm" class="needs-validation form-row" action="${pageContext.request.contextPath}/addSongToList">
                    <input type="hidden" name="song" value="${cancion.getIdCancion()}" />
                    <div class="col-auto my-1">
                        <select name="list" class="custom-select mr-sm-2" id="anyadirLista" required>
                            <c:forEach items="${sessionScope.misListas}" var="lista">
                                <option value="${lista.getIdLista()}">${lista.getNombre()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-auto my-1">
                        <button type="submit" class="btn btn-primary">Añadir</button>
                    </div>
                </form>
            </c:if>
            <div class="col-auto my-1">
                <button type="button" class="btn btn-default " data-toggle="modal" data-target="#modalCrear">
                    <span class="glyphicon glyphicon-plus" style="font-size:20px; "></span>
                </button>
            </div>
        </div>
        <div class="row">
            <div class="col-4 text-left">
                <img class="img-fluid" src="/contenido/imagenes/usuarios/${cancion.getUsuarioByIdUser().getUsername()}Perfil.png?x=${rand}" alt="Usuario">
            </div>
            <div class="col-8 text-left">
                <a href="usuarioPublico.jsp?name=${cancion.getUsuarioByIdUser().getUsername()}">
                    <h5>${cancion.getUsuarioByIdUser().getUsername()}</h5>
                </a>
                <br>
                <h6>${cancion.getFechaSubida()}</h6>
            </div>
        </div>
        <!--comentarios-->
        <div class="media mt-2">
            <div class="media-body">
                <h4 class="media-heading"> Comentarios </h4>
            </div>

        </div>
        <div class = "row align-items-center">
            <div class="col ">
                <form class="needs-validation form-row" action="${pageContext.request.contextPath}/Coment" method="post" novalidate>
                    <div class="col" required>
                        <textarea class="text form-control" id="comentario"  name="texto" required></textarea>
                        <input type="hidden" value="${cancion.getIdCancion()}" name="cancion" required>
                    </div>
                    <div class="col">
                        <button type="submit" class="btn btn-primary">Comentar</button>
                    </div>
                </form>
            </div>
        </div>


        <ul class="list-unstyled">
            <c:forEach items="${comentarios}" var="com">

                <li class="media">
                    <div class="media-left">
                        <img class = "align-self-center mr-3" src="/contenido/imagenes/usuarios/${com.getUsuarioByIdUser().getIdUser()}Perfil.png" style="width:64px;height:64px;" alt="...">
                    </div>
                    <div class="media-body">
                        <h4 class ="media-heading">
                            <a href="artista.jsp?name=${com.getUsuarioByIdUser().getIdUser()}" >
                                <h5>${com.getUsuarioByIdUser().getIdUser()}</h5>
                            </a>
                        </h4>
                        <p>${com.getCuerpo()}</p>
                    </div>
                </li>

            </c:forEach>
        </ul>
    </div>
</div><!-- Container -->

<!-- Modal1 -->
<div class="modal fade" id="modalCrear" tabindex="-1" role="dialog" aria-labelledby="modalCrear" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">

            <div class="modal-body">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <form id="createAndAdd" class="needs-validation form-row" action="${pageContext.request.contextPath}/createList">
                    <div class="form-group">
                        <label for="listName">Crear Lista y añadir</label>
                        <div class="input-group">
                            <input type="hidden" name="song" value="${cancion.getIdCancion()}" />
                            <input type="text" class="form-control" name="listName" id="listName" placeholder="Nueva Lista" required>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Añadir</button>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- Exito al añadir a lista -->
<div class="modal fade" id="modalAdd" tabindex="-1" role="dialog" aria-labelledby="modalAdd" aria-hidden="true">
    <div class= "modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                Canci&oacute;n a&ntilde;adida correctamente.
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/scripts/addToList.js"></script>
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
<jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>
