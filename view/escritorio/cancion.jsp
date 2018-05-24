<!-- TODO (JSP): "Lista" en el titulo es el nombre de la lista visitada -->
<%@page contentType="text/html; UTF-8" %>
<%@ page import="main.java.model.Usuario" %>
<%@ page import="main.java.model.Cancion" %>
<%@ page import="main.java.model.Comentario" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div id="tituloNuevo" value="Wolfic - Lista" style="display:none;"></div>
        <div class="container mb-3">
            <div class = "col-10">
                <div class = "row" >
                    <div class = "col">
                        <img class="img-fluid align-content-lg-end" src="/contenido/imagenes/canciones/${cancion.getIdCancion()}.png" alt="Placeholder">
                    </div>
                </div>
                <div class ="row">
                    <div class = "col-6">
                        <h3>${cancion.getNombre()}</h3>
                    </div>
                    <form class="needs-validation form-row" action="cancion.html" novalidate >
                        <div class="col-auto my-1">
                            <select class="custom-select mr-sm-2"  id="añadirLista" required>
                                <option value="1">Favoritos</option>
                                <option value="2">Lista 1</option>
                                <option value="3">Lista 2</option>
                            </select>
                        </div>
                        <div class="col-auto my-1">
                            <button type="submit" class="btn btn-primary">Añadir</button>
                        </div>
                    </form>
                    <div class = "col-auto my-1">
                        <button type="button" class="btn btn-default align-bottom" data-toggle="modal" data-target="#modalCrear">
                            <span class="fa fa-plus" style="font-size:20px; "></span>
                        </button>
                    </div>
                </div>
                <div class="border-bottom border-dark w-100 px-3"></div>
                <div class = "row">
                    <div class = "col-2 text-left">
                        <img class = "img-fluid" src="/contenido/imagenes/usuarios/${cancion.getUsuarioByIdUser().getIdUser()}Perfil.png?x=${rand}" alt="Usuario">
                    </div>
                    <div class = "col-10 text-left">
                        <a href="artista.jsp?name=${cancion.getUsuarioByIdUser().getIdUser()}">
                            <h5>${cancion.getUsuarioByIdUser().getIdUser()}</h5>
                        </a>
                        <br>
                        <h6>${cancion.getFechaSubida()}</h6>
                    </div>
                </div>
                <div class = "row">
                    <br>
                    <div class = "col">
                        <h6 class="media-heading">${descripcion}</h6> <!-- PASAR EL TITULO DE LA CANCION-->
                    </div>
                </div>
                <div class="border-bottom border-dark w-100 px-3"></div>
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
                            <img class = "align-self-center mr-3" src="/contenido/imagenes/usuarios/${com.getUsuarioByIdUser().getIdUser()}Perfil.png?x=${rand}" style="width:64px;height:64px;" alt="...">
                        </div>
                        <div class="media-body">
                            <h4 class ="media-heading">
                                <a href="/user?id=${com.getUsuarioByIdUser().getIdUser()}" >
                                    <h5>${com.getUsuarioByIdUser().getIdUser()}</h5>
                                </a>
                            </h4>
                            <p>${com.getCuerpo()}</p>
                        </div>
                    </li>

                </c:forEach>
                </ul>

            </div>
        </div> <!-- Container -->
<!-- Modal1 -->
<div class="modal fade" id="modalCrear" tabindex="-1" role="dialog" aria-labelledby="modalCrear" aria-hidden="true">
    <div class= "modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">

            <div class="modal-body">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <form action = "cancion.html">
                    <div class="form-group">
                        <label for="nombreSus">Crear Lista y añadir</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="nombreSus" placeholder="Nueva Lista" required>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Añadir</button>
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