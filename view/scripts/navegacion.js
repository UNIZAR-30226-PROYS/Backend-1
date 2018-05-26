/* Reemplazar enlaces por carga asíncrona */

var dominio = 'http://localhost/';

/* Invocada cada vez que hay que cambiar el contenido de la pagina */
function navegar(e){
    if(!$(this).hasClass('nav-profile-link')) {
        e.preventDefault();

        try {
            var origen = window.location.href;
            var destino = $(this).attr('href');

            if (origen !== destino) { //todo: revisar esto para no insertar nuevas entradas en el historial si vas a la misma pagina
                $('#gifLoad').removeClass("d-none");
                // Cargar contenido
                $("#contenido").load(destino, {ajax:1}, function(){$('#gifLoad').addClass("d-none")});
                //$.get(destino,{ajax:1},function(data){$('#contenido').html(data)});

                // Cambiar URL y titulo
                setTimeout(function () { //todo: mejorar esto para mostrar el titulo directamente y no depender de un timeout de 100ms
                    window.history.pushState({
                        "html": destino,
                        "titulo": $('#tituloNuevo').attr('value')
                    }, "", destino);
                    document.title = $('#tituloNuevo').attr('value');
                }, 100);
            }
        } catch ($ex) {
            window.console && console.log($ex);
        } finally {
            return false;
        }
    }
}

$(document).ready(function(){
    $('body').on('click','a',navegar);
});

/* Gestion del historial del navegador */
window.onpopstate = function(e){
    if(e.state){
        $('#gifLoad').removeClass("d-none");
        $("#contenido").load(e.state.html, {ajax:1}, function(){$('#gifLoad').addClass("d-none")});
        document.title = e.state.titulo;
    }
};