<%@page contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% if (request.getParameter("ajax") == null) { %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Resultados de ${consulta}</title>
    <%@include file="includes/html_head.jsp" %>
</head>
<body>
<div id="topbar-y-contenido" class="col-10 pl-0 pr-0">
    <%@include file="includes/topbar.jsp" %>
    <!-- CONTENIDO DE LA VISTA -->
    <div id="contenido">
<% } //if%>
        <div id="tituloNuevo" value="Resultados de ${consulta}" style="display:none;"></div>
        <div class="container mb-3">
            <div class="media mt-2">
                <div class="media-body">
                    <h4 class="media-heading">Resultados de "${consulta}" </h4>
                </div>
            </div>
            <div class="row pl-4 pt-2 pb-2">
                <div class="col-7">
                    <form id="formBuscar" class="search-button" action="/search" method="get">
                        <div class="orm-group">
                            <div class="input-group">
                                <input id="textoBuscar" name="search_input" type="text" class="form-control" placeholder="Buscar ..." autocomplete="off">
                                <span class="input-group-btn search-button">
                                        <button type="submit" class="btn btn-secondary">
                                            <span class="fa fa-search" style="font-size:20px;"  ></span>
                                        </button>
                                    </span>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row pl-4 pt-2 pb-2">
                <div class="col-7">
                    <div class="input-group">
                        <select class="custom-select" name="seleccion" id="inputGroupSelect04">
                            <option id="canciones" selected="selected">Canciones</option>
                            <option id="usuarios">Usuarios</option>
                            <option id="listas">Listas</option>
                        </select>
                    </div>
                </div>
            </div>
            <div id="result_canciones" class="list-group pt-2">
                <c:forEach items="${canciones}" var="resultado">
                    <a href="/song?id=${resultado.getIdCancion()}" class="list-group-item list-group-item-action">
                        <div class="media">
                            <div class="media-left" style="padding-right:15px">
                                <img src="/contenido/imagenes/canciones/${resultado.getIdCancion()}.png" style="width:64px;" alt="...">
                            </div>
                            <div class="media-body">
                                <h6 class="media-heading">${resultado.getNombre()}</h6>
                                <h6 class="media-heading">${resultado.getUsuarioByIdUser().getIdUser()}</h6>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            </div>
            <div id="result_usuarios" class="list-group pt-2 d-none">
                <c:forEach items="${usuarios}" var="resultado">
                    <a href="/user?id=${resultado.getIdUser()}" class="list-group-item list-group-item-action">
                        <div class="media">
                            <div class="media-left" style="padding-right:15px">
                                <img src="/contenido/imagenes/usuarios/${resultado.getIdUser()}Perfil.png?x=${rand}" style="width:64px;" alt="...">
                            </div>
                            <div class="media-body">
                                <h6 class="media-heading">${resultado.getIdUser()}</h6>
                                <h6 class="media-heading">${resultado.getNomAp()}</h6>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            </div>
            <div id="result_listas" class="list-group pt-2 d-none">
                <c:forEach items="${listas}" var="resultado">
                    <a href="/list?id=${resultado.getIdLista()}" class="list-group-item list-group-item-action">
                        <div class="media">
                            <div class="media-left" style="padding-right:15px">
                                <img src="/contenido/web/imagenes/wolf.jpg" style="width:64px;" alt="...">
                            </div>
                            <div class="media-body">
                                <h6 class="media-heading">${resultado.getNombre()}</h6>
                                <h6 class="media-heading">${resultado.getNumElementos()} canciones</h6>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>
    </div> <!-- Container -->
    <script src="${pageContext.request.contextPath}/scripts/busqueda.js"></script>
    <script>
        $(document).ready(function () {
            $('#inputGroupSelect04').on('change',function () {
                // console.log("Entra en seleccionado");
                var seleccionado = $('#inputGroupSelect04 option').filter(':selected').text();
                // console.log(seleccionado);
                if (seleccionado === "Canciones") {
                    $('#result_canciones').removeClass("d-none");
                    $('#result_usuarios').addClass("d-none");
                    $('#result_listas').addClass("d-none");
                }
                if (seleccionado === "Usuarios") {
                    $('#result_canciones').addClass("d-none");
                    $('#result_usuarios').removeClass("d-none");
                    $('#result_listas').addClass("d-none");
                }
                if (seleccionado === "Listas") {
                    $('#result_canciones').addClass("d-none");
                    $('#result_usuarios').addClass("d-none");
                    $('#result_listas').removeClass("d-none");
                }
            })
        });
    </script>
    <% if (request.getParameter("ajax") == null) { %>
</div>
<%@include file="includes/socialbar.jsp" %>
<%@include file="includes/reproductor.jsp" %>
</body>
</html>
<% } %>