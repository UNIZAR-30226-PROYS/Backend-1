<%@ page import="main.java.model.Usuario" %>
<%@ page import="java.util.*" %>
<%@ page import="com.sun.org.apache.xpath.internal.operations.Bool" %>
<%@page contentType="text/html; UTF-8" %>

<!-- Bootstrap CSS 4.1.0 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="" crossorigin="anonymous">
<!-- CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/escritorio/css/reproductor.css">

<!-- Font Awesome CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/escritorio/js/reproductor_escritorio.js"></script>
<audio id="audio1"></audio>

<%
    Integer ultimo_momento;
    String user_found;
    try {
        Usuario u_rep = (Usuario) session.getAttribute("username");
        ultimo_momento = u_rep.getUltRep();
        user_found = "true";

    }   catch (Exception e) {
        ultimo_momento = 0;
        user_found = "false";
    }
%>

<h1 class="d-none" id="ocultar_contenido">SE UTILIZA PARA LA OCULTAR LA LLAMADA AL SERVLET CON AJAX </h1>

<div class="row fixed-bottom" style="white-space: nowrap; background-color: black">

    <div class="collapse container-fluid " id="reprodcutor_list">
        <ul class="list-group " id="plList">	</ul>
        <hr>
    </div>

    <div class="col-4 mr-auto px-0 mx-0">
        <div class="text-center" style="line-height: 48px">
            <div class="col col-2 btn">
                <i class="fa fa-step-backward" id="btnPrev" style="font-size:20px; color: white" ></i>
            </div>
            <div class="col col-2 btn" id="btnPlayStio">
                <i class="fa fa-play"  id="btnPlayStio_v2" style="font-size:20px; color: white" ></i>
            </div>
            <div class="col-2 btn">
                <i class="fa fa-step-forward" id="btnNext" style="font-size:20px; color: white" ></i>
            </div>

            <div class="col-2 btn" id="btnRandom">
                <!-- <i class="fa fa-random" id="btnRandom" style="font-size:15px;" ></i> -->
                <i class="fa fa-random"  style="font-size:20px; color: white" ></i>
            </div>
            <div class="col col-2 btn" id="btnRepeat">
                <i class="fa fa-repeat" style="font-size:20px; color: white" ></i>
            </div>
        </div>
    </div>

    <div class="col-4 mx-auto mx-0">
        <div class="text-center" style="line-height: 48px">
            <div class="row">
                <div class="col-2" id="contTime" >
                    <span class="time_played" id="time_played_id" style="color: white">00:00</span>
                </div>
                <div class="col-6 nopad">
                    <% if ( user_found.equals("false") )  { %>
                    <%-- <h1>sin ultimo instante <%=ultimo_momento%> <%=user_found%> </h1> --%>
                    <input class="w-100 custom-range" type="range" name="momento_cancion" id="seek" min="0" value="0" max=""/>
                    <% } else {%>
                    <%-- <h1>con ultimo instante <%=ultimo_momento%> <%=user_found%></h1> --%>
                    <input class="w-100 custom-range" type="range" name="momento_cancion" id="seek" min="0" value="<%=ultimo_momento%>" max="${sessionScope.max_instante_reproduccion}"/>
                    <% } %>
                </div>
                <div class="col-2">
                    <span class="full_time" id="full_time_id" style="color: white">00:00</span>
                </div>
                <div class="col-2">
                    <i class="fa fa-volume-up volume-btn volume-up" style="font-size:20px; color: white" ></i>
                </div>
            </div>
        </div>
    </div>
    <!--data-container="body" data-toggle="popover" data-trigger="hover" data-placement="top" data-content="Vivamus-->
    <!--sagittis lacus vel augue laoreet rutrum faucibus."-->

    <div class="col-4 ml-auto px-0 mx-0">
        <div class="row">
            <img id="imagen_cancion" src="" style="max-width: 48px; max-height: 48px">

            <div class="col col-4">
                <p class="npTitle align-middle text-center pt-2" id="npTitle" style="color: white"></p>
            </div>

            <div class="col col-2">
                <div class="btn ">
                    <i class="fa fa-heart" id="like" style="font-size:20px; color: white;" ></i>
                </div>
            </div>
            <div class="col col-2">
                <div class="btn btn-primary" style="white-space: nowrap; background-color: black" type="button" data-toggle="collapse" data-target="#reprodcutor_list" aria-expanded="false" aria-controls="reprodcutor_list">
                    <i class="fa fa-align-justify"></i>
                </div>
            </div>
            <!--</div>-->
        </div>
    </div>
</div>
<script>
    $('#btnRepeat, #btnRandom').click(function(){
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
    $('#like').click(function(){
        if($(this).hasClass('active')){
            $(this).removeClass('active')
        }
        else{
            $(this).addClass('active')
        }
    });
</script>

<script>
    $(document).ready(function(){
        $('[data-toggle="popover"]').popover();
    });
    $(function() {
        var volumeBar = $("#volume-bar");

        var popOverSettings_volume = {
            placement: 'top',
            container: '.volume-up',
            html: true,
            trigger: "manual",
            selector: '.volume-btn',
            content: '<input class="custom-range volumen-web" id="input_audio" type="range" min="0" max="1"  step="0.01"  />'
        }
        $('.volume-btn').popover(popOverSettings_volume).on("mouseenter", function() {
            var _this = this;
            $(this).popover("show");
            $("#input_audio").val( document.getElementById("audio1").volume);

            /*$("#input_audio").val( document.getElementById("audio1").volume); */
            $(this).siblings(".popover").on("mouseleave", function() {
                $(_this).popover('hide');
            });
        }).on("mouseleave", function() {
            var _this = this;
            setTimeout(function() {
                if (!$(".popover:hover").length) {
                    $(_this).popover("hide")
                }
            }, 100);

        });
        $('body').delegate(volumeBar, 'change', function(evt) {
            //console.log($(".popover #volume-bar-value").val());
            // // alert("llamanasdfdo"); myfunc("value");

            var aud_volume =  $('#input_audio').val();
            document.getElementById("audio1").volume = aud_volume;
            $("#input_audio").val( document.getElementById("audio1").volume);
            //// alert("cambiadzcvzxcvzxco" + aud_volume);

        });
    });
</script>

<script>
    $("#play_button").click( function()
        {
            // alert('button clicked');
            actualizar_tracks();
            //$("#ocultar_contenido").load("/AddAndPlay", {max_num_canciones: 5}, function (caca) {
            //   // alert('cancion loaded');
            //});
        }
    );
</script>



