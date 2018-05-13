<!-- De ahora en adelante esta vista es una muestra y no debe ser utilizada -->
<!DOCTYPE html>
<html lang="es">
	<head>
		<title>Bienvenido a Wolfic</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1 ">

		<!-- Bootstrap CSS 4.0.0 -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

		<!-- CSS -->
		<link rel="stylesheet" href="/wolfsound/escritorio/css/marco.css">

        <!-- Font Awesome CSS -->
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <!-- jQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <script src="/wolfsound/scripts/navegacion.js"></script>
	</head>
	<body>
        <!-- Menu superior y contenido -->
        <div id="topbar-y-contenido" class="col-10 pl-0 pr-0">
            <nav id="topbar" class="navbar navbar-expand-lg navbar-light sticky-top pb-1 pt-0">
                <a href="explorar.jsp" class="navbar-brand"><!-- Brand/logo -->
                    <img src="/wolfsound/escritorio/images/wolfsound-white.png" style="height:40px;"  alt="Wolfic logo">
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item"><a id="nav-explorar" class="nav-link" href="explorar.jsp">Explorar</a></li>
                        <li class="nav-item"><a id="nav-listas" class="nav-link" href="listas.jsp">Listas</a></li>
                        <li class="nav-item"><a id="nav-artistas" class="nav-link" href="artistas.jsp">Suscripciones</a></li>
                        <li class="nav-item"><a href="subirMusica.jsp"><button type="button" class="btn btn-primary ml-1 mt-1">Sube tu música</button></a></li>
                    </ul>
                </div>
                <a href="usuario.jsp" > <i class="fa fa-user pt-1 pr-1" style="font-size:20px;"></i>Nombre Usuario</a>
            </nav>

            <!-- CONTENIDO DE LA VISTA -->
            <div id="contenido">
                <% String contenido = (String) request.getAttribute("page"); %>
                <jsp:include page="<%= contenido %>" ></jsp:include>
                <!-- Cargar aqui la pagina que corresponda -->
                <!--<script>
                    $("#contenido").load('explorar.jsp');
                    window.history.pushState({"html":'explorar.jsp',"titulo":'Wolfic - Explorar'}, "", 'explorar.jsp');
                </script>-->
            </div>
        </div>

        <!-- Socialbar o iniciar sesion
         TODO (JSP): si estas logueado mostrar socialbar, si no login/register -->
        <div id="socialbar" class="socialbar col-2" style="background-color: white">
            <div id="listadoEscuchas" class="text-white text-center">
                <!-- Temporal: Para cargar una de las 3 posibilidades en la barra de la derecha -->
              <script>
                  $("#listadoEscuchas").load('includes/dcha_registro.html');
              </script>
          </div>
        </div>

      <!-- Reproductor inferior -->
        <div id="divplayer">
            <audio controls id="player">
                <source src="/wolfsound/movil/music/Axwell_Ingrosso.mp3" type="audio/mpeg">
            </audio>
        </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>
