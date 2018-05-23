<div id="socialbar" class="socialbar col-2" style="background-color: white">
    <div id="listadoEscuchas" class="text-white text-center">
        <% if(session.getAttribute("username")==null){ %>
            <%@include file="dcha_inicio_sesion.jsp"%>
        <% }else{ %>
            <%@include file="dcha_amigos_escuchando.jsp" %>
        <% } %>
    </div>
    <script>
        function mostrar_iniciar_sesion(){
            $("#listadoEscuchas").load('includes/dcha_inicio_sesion.jsp');
        }
        function mostrar_registrarse(){
            $("#listadoEscuchas").load('includes/dcha_registro.jsp');
        }
    </script>
</div>