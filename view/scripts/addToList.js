$(document).ready(function () {
    $('#addToListForm').submit(function(e){
        e.preventDefault();
        var campos = $('#addToListForm').serialize();
        var destino = $('#addToListForm').attr('action') + '?' + campos;

        $.ajax({
            url: destino,
            type: 'get',
            error: function(XMLHttpRequest, textStatus, errorThrown){
                alert('Ha ocurrido un error. Inténtalo de nuevo en unos minutos.');
            },
            success: function(data){
                $("#modalAdd").modal("show");

                setTimeout(function () {
                    $("#modalAdd").modal("hide");
                }, 2000);
            }
        });
    });
});
