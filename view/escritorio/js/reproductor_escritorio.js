/* Ideas obtenidas de https://codepen.io/markhillard/pen/Hjcwu?editors=1010	*/
/* Last Modified : 22/04/2018 Jorge	*/
var tracks = [];

    function actualizar_tracks() {
        alert("actualizando");
        // Si no envias un parametro por algun extraño motivo no funciona */
        $.ajaxSetup({async: false});
        $("#ocultar_contenido").load("/Reproductor_lista_v2", {max_num_canciones: 5}, function (caca) {
            //alert("aqui -> " + caca);
            var componentes = caca.split(",");
            //alert("aqui_v2 -> "+componentes[0] + " " +componentes[1]);
            for (var i = 0, len = componentes.length; i < len;) {
                tracks.push({track: ((i / 2) + 1), name: componentes[i], id: componentes[i + 1]});

                i = i + 2;
                // alert("aqui-v4 ->"+tracks[0].name +" "+ tracks[0].id);
            }
        });
        $.ajaxSetup({async: true});
        tracks.pop();
    }

jQuery(document).ready(function() {
    var supportsAudio = !!document.createElement('audio').canPlayType;
    if (supportsAudio) {
        var index = 0;
        playing = false;
        repeat_option = false;
        random_option = false;
        var mouseup = false;
        mediaPath = 'http://wolfsound.ddns.net:7000/contenido/canciones/';
        imagePath = 'http://wolfsound.ddns.net:7000/contenido/imagenes/canciones/';
        extension = '';

        var actualizar_reproduccion = 10;  /* Frecuencia de actualización del ultimo momento de reproduccion */

        /* Mostrar Path */
        // var loc = window.location.pathname; var dir = loc.substring(0, loc.lastIndexOf('/')); alert(dir);

        actualizar_tracks();

        //alert("aqui-v3 ->"+tracks[0].name +" "+ tracks[0].id);

        /*
         $.ajaxSetup({async: true});
        var file = "music/nombre_canciones.txt";
        var tracks_aux = [];
        $.ajaxSetup({async: false});
        $.get(file,function(txt){		// Llamada AJAX Asíncrona
            var lines = txt.split("\n");
            for (var i = 0, len = lines.length; i < len; i++) {
                var componentes = lines[i].split(",");
                tracks_aux.push({ track: (i+1), name: componentes[0], duration: componentes[1] });
            }
            tracks = tracks_aux;	//getFile(tracks_aux);
        });
        $.ajaxSetup({async: true});
        function getFile(f){	tracks = f;	};
        //alert(tracks[0].track + tracks[0].name + tracks[0].duration); alert(tracks[0].track + tracks[0].name + tracks[0].duration);	alert(tracks[1].track + tracks[1].name + tracks[1].duration);
		*/

        buildPlaylist = $(tracks).each(function(key, value) {
            var trackNumber = value.track,
                trackName = value.name;
            // trackDuration = value.duration;
            if (trackNumber.toString().length === 1) {
                trackNumber = '0' + trackNumber;
            }
            $('#plList').append(
                '<li class="list-group-item list-group-item-action text-white  reprodcutor_list_item d-flex justify-content-between" data-toggle="list" > ' +
                '<div>'+trackNumber+ '</div>  ' +
                '<div>'+trackName+'.mp3</div>  ' +
                //	'<div>'+ trackDuration +'</div> ' +
                '</div>');
        }),

            trackCount = tracks.length,
            npTitle = $('#npTitle'),

            audio = $('#audio1').on('play', function () {
                playing = true;
            }).on('pause', function () {
                playing = false;
            }).on('ended', function () {

                if (repeat_option == true ){
                    loadTrack(index);
                    audio.play();
                }
                else if (random_option == true){
                    var song_to_play = Math.floor(Math.random() * (trackCount ) ) ;
                    loadTrack(song_to_play);
                    audio.play();
                }
                else{
                    if ((index + 1) < trackCount) {
                        index++;
                        loadTrack(index);
                        audio.play();
                    } else {
                        audio.pause();
                        index = 0;
                        loadTrack(index);
                    }
                }
            }).get(0),

            /*
            $("#volumen").bind("change", function() {
                alert("a");
                audio.volume =  ($(this).val());
            });
            *(
            */

            $("#seek").on("mouseup", function () {	 mouseup = false;		});

        $("#seek").on("mousedown", function () { mouseup = true;		});


        $("#seek").bind("change", function() {
            mouseup = true;
            audio.currentTime = ($(this).val());
            mouseup = false;
        });


        $('#audio1').on('timeupdate', function() {
            var curMins = Math.floor(this.currentTime / 60);
            var curSecs = Math.floor(this.currentTime - curMins * 60);

            var mins = Math.floor(this.duration / 60);
            var secs = Math.floor(this.duration - mins * 60);

            if (curSecs < 10) { (curSecs = '0' + curSecs); }
            if (secs < 10) { (secs = '0' + secs); }
            $("#time_played_id").text(curMins + ':' + curSecs);
            if (secs >0 || mins > 0)  {
                $("#full_time_id").text(mins + ':' + secs);
            }
            if ( mouseup == true ){
                return;
            }

            if ( mouseup == false ){
                $("#seek").val(this.currentTime);

                // var form = document.getElementById("ultimo_instante_reproduccion"); form.submit();
                actualizar_reproduccion = actualizar_reproduccion - 1;
                if (actualizar_reproduccion == 0) {
                    actualizar_reproduccion = 12;
                    var tiempo_actual = this.currentTime;
                    var max_tiempo = this.duration;
                    $("#ocultar_contenido").load("/reproductor_cont", {momento_cancion: tiempo_actual, momento_cancion_max : max_tiempo}, function () {
                        //alert("Load was performed.");
                    });
                }
            }
            $("#seek").attr({ "max" : (this.duration) });
        });


        btnPlayStio = $('#btnPlayStio').on('click', function () {
            if (!playing) {
                audio.play();

                audio.currentTime =  $("#seek").val();   //alert("empiezo" + $("#seek").val());

                $('#btnPlayStio_v2').removeClass('fa-play');
                $('#btnPlayStio_v2').addClass('fa-pause');
            } else {
                audio.pause();
                $('#btnPlayStio_v2').removeClass('fa-pause');
                $('#btnPlayStio_v2').addClass('fa-play');
            }
        }),

            btnPrev = $('#btnPrev').on('click', function () {
                index= (index-1);
                if (index == -1 ) index = trackCount -1 ;
                loadTrack(index);
                if (playing) {
                    audio.play();
                }
            }),

            btnNext = $('#btnNext').on('click', function () {
                index= (index+1)%trackCount;
                loadTrack(index);
                if (playing) {
                    audio.play();
                }
                /*
                if ((index + 1) < trackCount) {
                    index++;
                    loadTrack(index);
                    if (playing) {
                        audio.play();
                    }
                } else {
                    audio.pause();
                    index = 0;
                    loadTrack(index);
                }
                */
            }),

            btnRandom = $('#btnRandom').on('click', function () {
                if (random_option == true){
                    random_option = false;
                }
                else{
                    random_option = true;
                }
            }),

            btnRepeat = $('#btnRepeat').on('click', function () {
                if (repeat_option == true){
                    repeat_option = false;
                }
                else{
                    repeat_option = true;
                }
            }),


            li = $('#plList li').on('click', function () {
                var id = parseInt($(this).index());
                if (id !== index) {
                    playTrack(id);
                }
            }),

            loadTrack = function (id) {
                $('.list-group  > .active').removeClass('active');
                $('#plList li:eq(' + id + ')').addClass('active');

                npTitle.text(tracks[id].name+'.mp3');
                index = id;
                audio.src = mediaPath + tracks[id].id +'.mp3';
                //alert("src "+audio.src);
                $('#imagen_cancion').attr('src', imagePath + tracks[id].id +'.jpg');

            },

            playTrack = function (id) {
                loadTrack(id);
                audio.play();
            };

        extension = audio.canPlayType('audio/mpeg') ? '.mp3' : audio.canPlayType('audio/ogg') ? '.ogg' : '';
        loadTrack(index);
    }
    else {
        alert("Su navegador no es capaz de reproducir canciones");
    }
});
