<%@ page import="main.java.model.Suscribir" %>
<%@ page import="main.java.model.Usuario" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html; UTF-8" %>
<!DOCTYPE html>
<html lang="es">
    <%
            Usuario user = (Usuario)session.getAttribute("username");
            List<Suscribir> suscripciones = Suscribir.searchSuscripciones(user.getIdUser());
            pageContext.setAttribute("suscripciones", suscripciones);
            pageContext.setAttribute("numSus", suscripciones.size());
        %>
<!-- Cabecera -->
<div class="socialbar-header" style="background-color: black">
    <div class="text-center pt-2">${numSus} Suscripciones&nbsp;
        <i class="pl-1 fa fa-users m-auto"></i>
    </div>
</div>

<!-- Listado -->
<br><br><br>
<ul class="list-group">
    <c:forEach items="${suscripciones}" var="sus">
        <li class="list-group-item media">
            <div class="media">
                <div class="media-left">
                    <a href="${pageContext.request.contextPath}/user?id=${sus.getUsuarioByIdSuscrito().getIdUser()}"
                       class="a">
                        <img src="/contenido/imagenes/usuarios/${sus.getUsuarioByIdSuscrito().getUsername()}Perfil.png?x=${rand}"
                             style="width:50px;" alt="...">
                    </a>
                </div>
                <div class="media-body" style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap">
                    <h6 class="media-heading"><a
                            href="${pageContext.request.contextPath}/user?id=${sus.getUsuarioByIdSuscrito().getIdUser()}">${sus.getUsuarioByIdSuscrito().getUsername()}</a>
                    </h6>
                    <c:if test="${!sus.getUsuarioByIdSuscrito().getPublico()}">
                        <h6 class="media-heading"><a href="#">Privado</a></h6> <!-- Link a la canción -->
                    </c:if>
                    <c:if test="${sus.getUsuarioByIdSuscrito().getPublico()}">
                        <c:if test="${sus.getUsuarioByIdSuscrito().estado() == 0}">
                            <h6 class="media-heading"><a href="#">Conectado</a></h6>
                            <h6 class="media-body" style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap">
                                <a
                                        href="/song?id=${Usuario.getLastHistorial(sus.getUsuarioByIdSuscrito()).getIdCancion()}">${Usuario.getLastHistorial(sus.getUsuarioByIdSuscrito()).getNombre()}
                                </a>
                            </h6>
                            <!-- Link a la canción -->
                        </c:if>
                        <c:if test="${sus.getUsuarioByIdSuscrito().estado() == 1}">
                            <h6 class="media-heading"><a href="#">Ausente</a></h6> <!-- Link a la canción -->
                        </c:if>
                        <c:if test="${sus.getUsuarioByIdSuscrito().estado() == 2}">
                            <h6 class="media-heading"><a href="#">Desconectado</a></h6> <!-- Link a la canción -->
                        </c:if>
                    </c:if>
                </div>
            </div>
        </li>
    </c:forEach>
</ul>


