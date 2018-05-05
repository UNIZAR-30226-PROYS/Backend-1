<%@page contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Mis listas - ${sessionScope.username}</title>
    <jsp:include page="includes/header.jsp"></jsp:include>
</head>
<body>
    <jsp:include page="includes/navbars.jsp"></jsp:include>
<!-- CONTENIDO DE LA VISTA -->
<div class="container mb-3">

    <div class="media mt-2">
        <div class="media-body">
            <h4 class="media-heading">Listas de ${sessionScope.username}</h4>
        </div>

    </div>

    <div class="list-group pt-2">
        <div class="list-group-item list-group-item-action">
            <div class="media">
                    <div class="media-left" style="padding-right:15px">
                        <a href="lista.jsp">
                            <img src="images/wolf.jpg" style="width:64px;" alt="...">
                        </a>
                    </div>
                    <div class="media-body">
                        <h6 class="media-heading">Nombre lista</h6>
                    </div>
                    <div class="media-right">
                        <button type="button" class="btn btn-default ">
                            <span class="glyphicon glyphicon-remove" style="font-size:20px; "></span>
                        </button>
                    </div>
            </div>
        </div>
        <div class="list-group-item list-group-item-action">
            <div class="media">
                <div class="media-left" style="padding-right:15px">
                    <a href="lista.jsp">
                        <img src="images/wolf.jpg" style="width:64px;" alt="...">
                    </a>
                </div>
                <div class="media-body">
                    <h6 class="media-heading">Nombre lista</h6>
                </div>
                <div class="media-right">
                    <button type="button" class="btn btn-default ">
                        <span class="glyphicon glyphicon-remove" style="font-size:20px; "></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="list-group-item list-group-item-action">
            <div class="media">
                <div class="media-left" style="padding-right:15px">
                    <a href="lista.jsp">
                        <img src="images/wolf.jpg" style="width:64px;" alt="...">
                    </a>
                </div>
                <div class="media-body">
                    <h6 class="media-heading">Nombre lista</h6>
                </div>
                <div class="media-right">
                    <button type="button" class="btn btn-default ">
                        <span class="glyphicon glyphicon-remove" style="font-size:20px; "></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="list-group-item list-group-item-action">
            <div class="media">
                <div class="media-left" style="padding-right:15px">
                    <a href="lista.jsp">
                        <img src="images/wolf.jpg" style="width:64px;" alt="...">
                    </a>
                </div>
                <div class="media-body">
                    <h6 class="media-heading">Nombre lista</h6>
                </div>
                <div class="media-right">
                    <button type="button" class="btn btn-default ">
                        <span class="glyphicon glyphicon-remove" style="font-size:20px; "></span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div> <!-- Container -->
    <jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>


