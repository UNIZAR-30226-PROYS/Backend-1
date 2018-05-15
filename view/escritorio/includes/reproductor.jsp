
		<!-- Bootstrap CSS 4.1.0 -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
		<!-- CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/escritorio/css/reproductor.css">

        <!-- Font Awesome CSS -->
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/escritorio/js/reprodutor_v2.js"></script>

<div class="row fixed-bottom" style="white-space: nowrap; background-color: black">
    <div class="col-4 mr-auto px-0 mx-0">
        <div class="text-center" style="line-height: 48px">
            <div class="col-2 btn">
                <i class="fa fa-step-backward" id="btnPrev" style="font-size:20px; color: white" ></i>
            </div>
            <div class="col-2 btn">
                <i class="fa fa-play" id="btnPlayStio" style="font-size:20px; color: white" ></i>
            </div>
            <div class="col-2 btn">
                <i class="fa fa-step-forward" id="btnNext" style="font-size:20px; color: white" ></i>
            </div>
            <div class="col-2 btn">
                <i class="fa fa-random" id="btnRandom" style="font-size:20px; color: white" ></i>
            </div>
            <div class="col-2 btn">
                <i class="fa fa-repeat" id="btnRepeat" style="font-size:20px; color: white" ></i>
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
                    <input class="w-100 custom-range" type="range" id="seek" min="0" value="0" max=""/>
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
			<img src="${pageContext.request.contextPath}/escritorio/images/prueba.JPG" style="max-width: 48px; max-height: 48px">

			<div class="col-4">
				<p class="cancion" style="color: white">Boom Boom</p>
				<p class="cancion" style="color: white">Jvst Say Yes</p>
			</div>
			<div class="col-2 btn">
				<i class="fa fa-heart" id="like" style="font-size:20px; color: white" ></i>
			</div>
			<div class="col-2 btn">
				<i class="fa fa-align-justify" id="list" style="font-size:20px; color: white" ></i>
			</div>
			<!--</div>-->
		</div>
	</div>
</div>
		<script>
		$('#btnRepeat, #like').click(function(){
			if($(this).hasClass('active')){
				$(this).removeClass('active')
			} else {
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
                    content: '<input class="custom-range volumen-web" type="range" min="0" max="1" step="0.01"  />'
                }

                $('.volume-btn').popover(popOverSettings_volume).on("mouseenter", function() {
                    var _this = this;
                    $(this).popover("show");
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
                    console.log($(".popover #volume-bar-value").val());
                });
            });
        </script>