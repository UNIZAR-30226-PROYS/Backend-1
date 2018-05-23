<!-- TODO (JSP): "Lista" en el titulo es el nombre de la lista visitada -->
<%@page contentType="text/html; UTF-8" %>
<%@ page import="main.java.model.Usuario" %>
<%@ page import="main.java.model.Cancion" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% if(request.getParameter("ajax")==null){ %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Wolfic - Lista</title>
    <%@include file="includes/html_head.jsp" %>
</head>
<body>
<%
    String idS = (String) request.getParameter("id");
    if (idS==null)idS="1";
    Integer id = Integer.parseInt(idS);
    Cancion cancion = (Cancion) Cancion.getCancion(id);
    String nombre = cancion.getNombre();
    String desc = main.java.controller.SongDescriptionController.getText(nombre);
    pageContext.setAttribute("cancion", cancion);
    pageContext.setAttribute("desc", desc);

%>
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
                        <img class = "img-fluid" src="/contenido/imagenes/usuarios/${cancion.getUsuarioByIdUser().getIdUser()}Perfil.png" alt="Usuario">
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
                        <h6 class="media-heading">${desc}</h6> <!-- PASAR EL TITULO DE LA CANCION-->
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
                        <form class="needs-validation form-row " action="cancion.html" novalidate >
                            <div class="col" required>
                                <textarea class="text form-control" id="comentario"  required></textarea>
                            </div>
                            <div class="col-auto my-1">
                                <button type="submit" class="btn btn-primary">Comentar</button>
                            </div>
                        </form>
                    </div>
                </div>


                <div class="list-group pt-2">
                    <a href="usuarioPublico.html" class="list-group-item list-group-item-action">
                        <div class="media">
                            <div class="media-left" style="padding-right:15px">
                                <img src="images/wolf.jpg" style="width:30px;" alt="...">
                            </div>
                            <div class="media-body">
                                <h5 class="media-heading">Nombre  User</h5>
                                <h6 class="media-heading">Hola probando Probando soy un comentario lalalalalalalalalalalala</h6>
                            </div>
                        </div>
                    </a>
                    <a href="usuarioPublico.html" class="list-group-item list-group-item-action">
                        <div class="media">
                            <div class="media-left" style="padding-right:15px">
                                <img src="images/wolf.jpg" style="width:30px;" alt="...">
                            </div>
                            <div class="media-body">
                                <h5 class="media-heading">Nombre User 2</h5>
                                <h6 class="media-heading">En un lugar de la Mancha, de cuyo nombre no quiero acordarme, no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero, adarga antigua, rocín flaco y galgo corredor. Una olla de algo más vaca que carnero, salpicón las más noches, duelos y quebrantos los sábados, lentejas los viernes, algún palomino de añadidura los domingos, consumían las tres partes de su hacienda. El resto della concluían sayo de velarte, calzas de velludo para las fiestas con sus pantuflos de lo mismo, los días de entre semana se honraba con su vellori de lo más fino. Tenía en su casa una ama que pasaba de los cuarenta, y una sobrina que no llegaba a los veinte, y un mozo de campo y plaza, que así ensillaba el rocín como tomaba la podadera. Frisaba la edad de nuestro hidalgo con los cincuenta años, era de complexión recia, seco de carnes, enjuto de rostro; gran madrugador y amigo de la caza. Quieren decir que tenía el sobrenombre de Quijada o Quesada (que en esto hay alguna diferencia en los autores que deste caso escriben), aunque por conjeturas verosímiles se deja entender que se llama Quijana; pero esto importa poco a nuestro cuento; basta que en la narración dél no se salga un punto de la verdad.</h6>
                            </div>
                        </div>
                    </a>
                </div>
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