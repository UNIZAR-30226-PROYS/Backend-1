<%@ page import="main.java.model.Suscribir" %>
<%@page contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
	<head>
		<title>Bienvenido a Wolfic</title>
		<jsp:include page="includes/header.jsp"></jsp:include>
	</head>
	<body>
    <%String username = (String) request.getParameter("name");%>

    <jsp:include page="includes/navbars.jsp"></jsp:include>
        <!-- CONTENIDO DE LA VISTA -->
        <div class="container mb-3">
            <div class="row pt-3">
                <div class="col col-md-offset-5">
                    <div class="img-responsive text-center">
                        <img src="/contenido/imagenes/usuarios/<%=username%>Perfil.png" style="height: 80px;" alt="Usuario">
                        <p><%=username%></p>
                    </div>
                </div>
            </div>
            <div class="border-bottom border-dark w-100 my-2 px-3"></div> <!-- Separador horizontal -->
            <div class="row">
                <div class="col-auto mr-auto">
                    <h4>Listas de <%=username%></h4>
                </div>
                <div class="float-right mr-3">
                    <a href="mis_listas.jsp" class="btn btn-link" role="button" >
                        <span class="fa fa-chevron-right" style="font-size:20px;"></span>
                    </a>
                </div>
            </div>
            <div class="row">
                <div class="col-auto mr-auto">
                    <h4>Audio de <%=username%></h4>
                </div>
                <div class="float-right mr-3">
                    <a href="lista.jsp" class="btn btn-link" role="button" >
                        <span class="fa fa-chevron-right" style="font-size:20px;"></span>
                    </a>
                </div>
            </div>
            <div class="border-bottom border-dark w-100 px-3"></div> <!-- Separador horizontal -->
            <%
                Boolean suscrito = Suscribir.existsSuscribir((String)session.getAttribute("username"),username);
                pageContext.setAttribute("sus", suscrito);
            %>
            <c:if test="${!sus}">
                <div class="media mt-2">
                    <div class="media-body">
                        <h4 class="media-heading"> Suscribirse a <%=username%></h4>
                    </div>
                    <div class="media-left">
                        <a href ="${pageContext.request.contextPath}/Suscribe?name=<%=username%>">
                            <button type="button" class="btn btn-default ">
                                <span class="fa fa-user-plus" style="font-size:20px; "></span>
                            </button>
                        </a>
                    </div>
                </div>
            </c:if>
            <c:if test="${sus}">
                <div class="media mt-2">
                    <div class="media-body">
                        <h4 class="media-heading"> Suscrito a <%=username%></h4>
                    </div>
                </div>
            </c:if>


        </div> <!-- Container -->

        <jsp:include page="includes/footer.jsp"></jsp:include>
    </body>
</html>


