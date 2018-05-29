<%@ page import="model.Usuario" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sun.org.apache.xpath.internal.operations.Bool" %>
<%@page contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Bootstrap CSS 4.1.0 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
<!-- CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/movil/css/reproductor.css">

<!-- Font Awesome CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/movil/js/reprodutor_v2.js"></script>


<%
	Integer ultimo_momento;
	String user_found;
	try {
		Usuario u = (Usuario) session.getAttribute("username");
		ultimo_momento = u.getUltRep();
		user_found = "true";

	}   catch (Exception e) {
		ultimo_momento = 0;
		user_found = "false";
	}
%>

<h1 class="d-none" id="ocultar_contenido">SE UTILIZA PARA LA OCULTAR LA LLAMADA AL SERVLET CON AJAX </h1>

<!-- CONTENIDO REPRODUCTOR -->
<div  class="container-fluid reproductor text-white fixed-bottom  py-1 px-1 ">

	<!-- Lista de reproduccion, contendi generado con jquery-->
	<div class="collapse container-fluid " id="reprodcutor_list">
		<ul class="list-group " id="plList">	</ul>
		<hr>

		<div class="container-fluid">
			<div class="row ">

				<div class="col">
					<span class="time_played" id="time_played_id" >00:00</span>
				</div>


				<div class="col text-right">
					<span class="full_time" id="full_time_id">00:00</span>
				</div>

			</div>
		</div>

		<form class="clase_ultimo_instante_reproduccion" id="ultimo_instante_reproduccion" action="${pageContext.request.contextPath}/reproductor_cont" method="post" novalidate>
			<div class="container-fluid">
				<%--
                <c:if test="${not empty sessionScope.ultimo_instante_reproduccion}">
                    <input class="w-100 custom-range" type="range" name="momento_cancion" id="seek" min="0" value="${sessionScope.ultimo_instante_reproduccion}" max=""/>
                </c:if>
                <c:if test="${empty sessionScope.ultimo_instante_reproduccion}">
                    <input class="w-100 custom-range" type="range" name="momento_cancion" id="seek" min="0" value="0" max=""/>
                </c:if>
                --%>
				<% if ( user_found.equals("false") )  { %>
				<%-- <h1>sin ultimo instante <%=ultimo_momento%> <%=user_found%> </h1> --%>
				<input class="w-100 custom-range" type="range" name="momento_cancion" id="seek" min="0" value="0" max=""/>
				<% } else {%>
				<%-- <h1>con ultimo instante <%=ultimo_momento%> <%=user_found%></h1> --%>
				<input class="w-100 custom-range" type="range" name="momento_cancion" id="seek" min="0" value="<%=ultimo_momento%>" max="${sessionScope.max_instante_reproduccion}"/>
				<% } %>
			</div>
		</form>

		<div class="container-fluid botones pt-1">
			<div class="row" >
				<div class="col col-2 ">
					<div class="btn" id="btnRandom">
						<!-- <i class="fa fa-random" id="btnRandom" style="font-size:15px;" ></i> -->
						<i class="fa fa-random"  style="font-size:15px;" ></i>
					</div>
				</div>
				
				<div class="col col-2">
					<div class="btn" id="btnPrev">
						<i class="fa fa-step-backward"  style="font-size:16px;" ></i>
					</div>
				</div>
				<div class="col col-4 text-center">
					<button type="button" class="btn btn-light" href="cancion.jsp">info</button>
				</div>

				<div class="col col-2 text-center">
					<div class="btn" id="btnNext">
						<i class="fa fa-step-forward"  style="font-size:16px;" ></i>
					</div>
				</div>
				<div class="col col-2" >
					<div class="btn" id="btnRepeat">
						<i class="fa fa-repeat"  style="font-size:15px;" ></i>
					</div>
				</div>
			</div>
		</div>

		<hr>
	</div>

	<!-- VOLUMEN, SE PUEDE AÑADIR
    <div class="container text-center">
        <i class="fa fa-volume-up"></i>
        <input class=" custom-range volumen" id="volumen" class="volumen" type="range" min="0" max="1" step="0.01"  />
    </div>
    -->

	<audio id="audio1"></audio>



	<div class="container-fluid">
		<div class="row ">
			<div class="col col-2">
				<div class="btn" id="btnPlayStio">
					<i class="fa fa-play"  id="btnPlayStio_v2" style="font-size:18px;" ></i>
				</div>
			</div>
			<div class="col col-8 text-center pt-2">
				<div id="nowPlay">
					<h6> <span class="npTitle" id="npTitle"></span> </h6>
				</div>
			</div>
			<div class="col col-2">
				<div class="btn ">
					<i class="fa fa-heart" id="like" style="font-size:16px;" ></i>
				</div>
			</div>

		</div>
	</div>


</div>	<!-- FIN CONTENIDO REPRODUCTOR -->

<script>
    $('#btnRepeat, #btnRandom, #like').click(function(){
        if($(this).hasClass('active')){
            $(this).removeClass('active')
        } else {

            /* Solo se puede seleccionar o la reproduccion aleatorio, o repetir canción al acabar */
            if ($(this).is('#btnRepeat')){
                if ($('#btnRandom').hasClass('active')){
                    $('#btnRandom').removeClass('active')
                }
            }
            else{
                if ($('#btnRepeat').hasClass('active')){
                    $('#btnRepeat').removeClass('active')
                }
            }

            $(this).addClass('active')
        }
    });
</script>
<script>
    $(document).ready(function () {
        $(".npTitle").click(function() {
            if ($('.collapse').hasClass('show')){
                $('.collapse').collapse('hide');
            }
            else{
                $('.collapse').collapse('show');
            }

        });
    });
</script>
<script>
    // document.getElementById("seek").submit();
    //    data: 'momento_cancion='+ $('#seek').val(),
</script>

<script>
	/*
    $("clase_ultimo_instante_reproduccion").on('submit',(function(e){
        e.preventDefault();
        $.ajax({
            url: {pageContext.request.contextPath}/reproductor_cont,
            type: "POST",
            data:  new FormData(this),
            contentType: false,
            cache: false,
            processData:false,
            success: function(data){
                alert("ok");
            },
            error: function(){
                alert("error");
            }
        })
    }));
    */
</script>


