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
		
			<!-- CONTENIDO REPRODUCTOR -->	
			<div  class="container-fluid reproductor text-white fixed-bottom  py-3">
				
				<!-- Lista de reproduccion, contendi generado con jquery-->	
				<div class="collapse container-fluid " id="reprodcutor_list">		
					<ul class="list-group " id="plList">	</ul>	 
					<hr>
				</div> 
	
				<div class="container ">
					<div class="row">
						<div class="col-2 ">
							<div class="btn">
								<i class="fa fa-heart" id="like" style="font-size:16px;" ></i> 
							</div>
						</div>
						<div class="col-8 text-center">
							<div id="nowPlay">
								<h6> <span class="npTitle" id="npTitle"></span> </h6>
							</div>
						</div>
						<div class="col-2">
							<a class="btn">	
								<i class="fa fa-align-justify" aria-controls="#reprodcutor_list" data-toggle="collapse" aria-expanded="false" href="#reprodcutor_list" style="font-size:16px;" ></i> 
							</a>
						</div>
					</div>
				</div>
				
				<div class="container text-center">
						<i class="fa fa-volume-up"></i>
						<input class=" custom-range volumen" id="volumen" class="volumen" type="range" min="0" max="1" step="0.01"  /> 		 <!--  VOLUMEN, SE PUEDE AÑADIR -->		
				</div>	
			
				<audio id="audio1"></audio>
			
				<div class="container-fluid">	
					  <div class="row justify-content-between">
						<div class="col-2">
						   <span class="time_played" id="time_played_id" >00:00</span>
						</div>
						<div class="col-2 ">
							<span class="full_time" id="full_time_id">00:00</span>
						</div>
					  </div>				  
					<input class="w-100 custom-range" type="range" id="seek" min="0" value="0" max=""/>			
				</div>
			
				<div class="container-fluid botones">
					<div class="row" >
						<div class="col offset-1 col-2">
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
						<div class="col   col-2">
							<div class="btn" id="btnPlayStio">
								<i class="fa fa-play"  id="btnPlayStio_v2" style="font-size:18px;" ></i>
							</div>
						</div>
						<div class="col col-2">
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
				
			</div>	<!-- FIN CONTENIDO REPRODUCTOR -->	
		</div>	<!-- FIN CONTENIDO DE LA VISTA -->

        <jsp:include page="includes/footer.jsp"></jsp:include>
        <script type="text/javascript" src="js/reprodutor_v2.js"></script>
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
	
	</body>
</html>
