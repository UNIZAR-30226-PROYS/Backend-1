/* Invocada al buscar */
function buscar(action,camposUrl){
    try {
        var origen = window.location.href;
        var destino = action+'?'+camposUrl;

        $('#gifLoad').removeClass("d-none");

        // Cargar contenido
        $("#contenido").load(destino, {ajax:1}, function(){
            $('#gifLoad').addClass("d-none");

            // Cambiar URL y titulo
            window.history.pushState({
                "html": destino,
                "titulo": $('#tituloNuevo').attr('value')
            }, "", destino);
            document.title = $('#tituloNuevo').attr('value');
        });
    } catch ($ex) {
        window.console && console.log($ex);
    } finally {
        return false;
    }
}

$(document).ready(function(){
    $('#formBuscar').submit(function(e){
        e.preventDefault();
        var campos = $('#formBuscar').serialize();
        buscar($('#formBuscar').attr('action'),campos);
    });
});