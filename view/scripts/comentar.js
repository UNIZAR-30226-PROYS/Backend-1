$(document).ready(function () {
    $('#formComentar').submit(function(e){
        e.preventDefault();
        var campos = $('#formComentar').serialize();
        var destino = $('#formComentar').attr('action') + '?' + campos;

        $.ajax({
            url: destino,
            type: 'post',
            error: function(XMLHttpRequest, textStatus, errorThrown){
                // alert('Ha ocurrido un error comentando.');
            },
            success: function(data){
                $("#contenido").load(window.location.href,{ajax:1});
            }
        });
    });
});
