<%@page contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% if(request.getParameter("ajax")==null){ %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Wolfic - Explorar</title>
    <%@include file="includes/html_head.jsp" %>
</head>
<body>
    <!-- Menu superior y contenido -->
    <div id="topbar-y-contenido" class="col-10 pl-0 pr-0">
        <%@include file="includes/topbar.jsp" %>
        <!-- CONTENIDO DE LA VISTA -->
        <div id="contenido">
<% } //if%>
            <div id="tituloNuevo" value="Wolfic - Explorar" style="display:none;"></div>
            <div class="col-12 d-block">

                <!-- Titulo de la pantalla -->
                <h2 class="text-left pl-4 pt-2">Explorar</h2>
                <div class="row pl-4 pt-2 pb-2">
                    <div class="col-7">
                        <form class="search-button" action="${pageContext.request.contentType}/search" method="post">
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
                ${sessionScope.error}
                <c:remove var="error"></c:remove>
                <c:if test="${not empty sessionScope.username}">
                    <div class="row pl-4 pr-2">
                        <div class="col-auto pt-2 pb-3">
                            <h4>Mis listas</h4>
                        </div>
                        <div class="float-right mr-3 pt-2">
                            <a href="listas.jsp" class="btn btn-link" role="button" >
                                <span class="fa fa-chevron-right" style="font-size:20px;"></span>
                            </a>
                        </div>
                    </div>
                    <div class="row pl-4 pr-2">
                        <c:forEach items="${misListas}" var="lista" begin="0" end="3">
                            <div class="col-3">
                                <div class="img-thumbnail h-100">
                                    <a href="/list?id=${lista.getIdLista()}" target="_self">
                                        <img src="/contenido/web/imagenes/${lista.getIdLista()}.jpg" class="pt-3" alt="" style="width:20%">
                                        <div class="caption pt-3 pb-2">
                                            <p>${lista.getNombre()}</p>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>

                <div class="row pt-3 pl-4 pr-2">
                    <div class="col-auto pt-2 pb-3">
                        <h4>Listas recomendadas</h4>
                    </div>
                    <div class="float-right mr-3 pt-2">
                        <a href="listas.jsp" class="btn btn-link" role="button" >
                            <span class="fa fa-chevron-right" style="font-size:20px;"></span>
                        </a>
                    </div>
                </div>
                <div class="row pl-4 pr-2">
                    <div class="col-3">
                        <div class="img-thumbnail h-100">
                            <a href="lista.jsp" target="_self">
                                <img src="/contenido/web/imagenes/wolf.jpg" class="pt-3" alt="" style="width:20%">
                                <div class="caption pt-3 pb-2">
                                    <p>Nombre lista rec. 1</p>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-3">
                        <div class="img-thumbnail h-100">
                            <a href="lista.jsp" target="_self">
                                <img src="/contenido/web/imagenes/wolf.jpg" class="pt-3" alt="" style="width:20%">
                                <div class="caption pt-3 pb-2">
                                    <p>Nombre lista rec. 2</p>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-3">
                        <div class="img-thumbnail h-100">
                            <a href="lista.jsp" target="_self">
                                <img src="/contenido/web/imagenes/wolf.jpg" class="pt-3" alt="" style="width:20%">
                                <div class="caption pt-3 pb-2">
                                    <p>Nombre lista rec. 3</p>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-3">
                        <div class="img-thumbnail h-100">
                            <a href="lista.jsp" target="_self">
                                <img src="/contenido/web/imagenes/wolf.jpg" class="pt-3" alt="" style="width:20%">
                                <div class="caption pt-3 pb-2">
                                    <p>Nombre lista rec. 4</p>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>

                <c:if test="${not empty sessionScope.username}">
                    <div class="row pl-4 pt-3 pb-1 pr-2">
                        <div class="col-auto pt-2 pb-3">
                            <h4>Mi audio</h4>
                        </div>
                        <div class="float-right mr-3 pb-3 pt-2">
                            <button type="submit" class="btn btn-link">
                                <span class="fa fa-chevron-right" style="font-size:20px;"></span>
                            </button>
                        </div>
                    </div>
                    <div class="row pl-4 pr-2">
                        <div class="col-3">
                            <div class="img-thumbnail h-100">
                                <a href="" target="_self">
                                    <img src="/contenido/web/imagenes/wolf.jpg" class="pt-3" alt="" style="width:20%">
                                    <div class="caption pt-3 pb-2">
                                        <p>Nombre Mi audio 1</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="col-3">
                            <div class="img-thumbnail h-100">
                                <a href="" target="_self">
                                    <img src="/contenido/web/imagenes/wolf.jpg" class="pt-3" alt="" style="width:20%">
                                    <div class="caption pt-3 pb-2">
                                        <p>Nombre Mi audio 2</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="col-3">
                            <div class="img-thumbnail h-100">
                                <a href="" target="_self">
                                    <img src="/contenido/web/imagenes/wolf.jpg" class="pt-3" alt="" style="width:20%">
                                    <div class="caption pt-3 pb-2">
                                        <p>Nombre Mi audio 3</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="col-3">
                            <div class="img-thumbnail h-100">
                                <a href="" target="_self">
                                    <img src="/contenido/web/imagenes/wolf.jpg" class="pt-3" alt="" style="width:20%">
                                    <div class="caption pt-3 pb-2">
                                        <p>Nombre Mi audio 4</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </c:if>

                <div class="row pl-4 pt-3 pr-2">
                    <div class="col-auto pt-2 pb-3">
                        <h4>Audio recomendado</h4>
                    </div>
                    <div class="float-right mr-3 pt-2">
                        <button type="submit" class="btn btn-link">
                            <span class="fa fa-chevron-right" style="font-size:20px;"></span>
                        </button>
                    </div>
                </div>
                <div class="row pl-4 pb-4 pr-2">
                    <div class="col-3">
                        <div class="img-thumbnail h-100">
                            <a href="" target="_self">
                                <img src="/contenido/web/imagenes/wolf.jpg" class="pt-3" alt="" style="width:20%">
                                <div class="caption pt-3 pb-2">
                                    <p>Nombre audio rec. 1</p>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-3">
                        <div class="img-thumbnail h-100">
                            <a href="" target="_self">
                                <img src="/contenido/web/imagenes/wolf.jpg" class="pt-3" alt="" style="width:20%">
                                <div class="caption pt-3 pb-2">
                                    <p>Nombre audio rec. 2</p>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-3">
                        <div class="img-thumbnail h-100">
                            <a href="" target="_self">
                                <img src="/contenido/web/imagenes/wolf.jpg" class="pt-3" alt="" style="width:20%">
                                <div class="caption pt-3 pb-2">
                                    <p>Nombre audio rec. 3</p>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-3">
                        <div class="img-thumbnail h-100">
                            <a href="" target="_self">
                                <img src="/contenido/web/imagenes/wolf.jpg" class="pt-3" alt="" style="width:20%">
                                <div class="caption pt-3 pb-2">
                                    <p>Nombre audio rec. 4</p>
                                </div>
                            </a>
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