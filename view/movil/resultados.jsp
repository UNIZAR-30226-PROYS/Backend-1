<%@page contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Resultados de ${sessionScope.consulta}</title>
    <jsp:include page="includes/header.jsp"></jsp:include>
</head>
<body>
<jsp:include page="includes/navbars.jsp"></jsp:include>
<!-- CONTENIDO DE LA VISTA -->
<div class="container mb-3">

    <div class="media mt-2">
        <div class="media-body">
            <h4 class="media-heading">Resultados de "consulta"</h4>
        </div>
    </div>
    <div class="row pt-1">
        <div class="col">
            <form class="search-button" action="resultados.jsp">
                <div class="form-group">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Buscar ...">
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
    <div class="row pt-1">
        <div class="col">
            <div class="input-group">
                <select class="custom-select" id="inputGroupSelect04">
                    <option selected>Canciones</option>
                    <option value="Usuarios">Usuarios</option>
                    <option value="Listas">Listas</option>
                </select>
            </div>
        </div>
    </div>

    <div class="list-group pt-2">
        <!--TODO: Crear lista Resultados (pueden ser canciones/usuarios/o listas)!!!-->
        <c:forEach items="${sessionScope.misListas}" var="lista">
            <a href="#" class="list-group-item list-group-item-action">
                <div class="media">
                    <div class="media-left" style="padding-right:15px">
                        <img src="images/wolf.jpg" style="width:64px;" alt="...">
                    </div>
                    <div class="media-body">
                        <h6 class="media-heading">Nombre Cancion</h6>
                        <h6 class="media-heading">usuario</h6>
                    </div>
                </div>
            </a>
        </c:forEach>
    </div>
</div> <!-- Container -->

<jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>


