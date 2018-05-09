<%@page contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
	<head>
		<title>Bienvenido a Wolfic</title>
		<jsp:include page="includes/header.jsp"></jsp:include>
	</head>
	<body>
    <jsp:include page="includes/navbars.jsp"></jsp:include>

        <!-- CONTENIDO DE LA VISTA -->
        <div class="container mb-3">
            <div class="row pt-3">
                <div class="col col-md-offset-5">
                    <div class="img-responsive text-center">
                        <img src="images/user.svg" style="height: 80px;" alt="Usuario">
                        <p>USUARIO</p>
                    </div>
                </div>
            </div>
            <div class="border-bottom border-dark w-100 my-2 px-3"></div> <!-- Separador horizontal -->
            <div class="row">
                <div class="col-auto mr-auto">
                    <h4>Listas de "usuario"</h4>
                </div>
                <div class="float-right mr-3">
                    <a href="mis_listas.jsp" class="btn btn-link" role="button" >
                        <span class="fa fa-chevron-right" style="font-size:20px;"></span>
                    </a>
                </div>
            </div>
            <div class="row">
                <div class="col-auto mr-auto">
                    <h4>Audio de "usuario"</h4>
                </div>
                <div class="float-right mr-3">
                    <a href="lista.jsp" class="btn btn-link" role="button" >
                        <span class="fa fa-chevron-right" style="font-size:20px;"></span>
                    </a>
                </div>
            </div>
            <div class="border-bottom border-dark w-100 px-3"></div> <!-- Separador horizontal -->
            <div class="media mt-2">
                <div class="media-body">
                    <h4 class="media-heading"> Suscribirse a "usuario"</h4>
                </div>
                <div class="media-left">
                    <button type="button" class="btn btn-default ">
                        <span class="fa fa-user-plus" style="font-size:20px; "></span>
                    </button>
                </div>
            </div>


        </div> <!-- Container -->

        <jsp:include page="includes/footer.jsp"></jsp:include>
    </body>
</html>


