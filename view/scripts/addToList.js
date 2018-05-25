function addToList(destino){
    $.ajax({
        url: destino,
        type: 'get',
        error: function(XMLHttpRequest, textStatus, errorThrown){
            alert('Ha ocurrido un error actualizando la lista. Inténtalo de nuevo en unos minutos.');
        },
        success: function(data){
            $("#modalAdd").modal("show");

            setTimeout(function () {
                $("#modalAdd").modal("hide");
            }, 2000);
        }
    });
}

$(document).ready(function () {
    $('#addToListForm').submit(function(e){
        e.preventDefault();
        var campos = $('#addToListForm').serialize();
        var destino = $('#addToListForm').attr('action') + '?' + campos;

        addToList(destino);
    });

    $('#createAndAdd').submit(function(e){
        e.preventDefault();
        var campos = $('#createAndAdd').serialize();
        var destino = $('#createAndAdd').attr('action') + '?' + campos;

        $.ajax({
            url: destino,
            type: 'get',
            error: function(XMLHttpRequest, textStatus, errorThrown){
                alert('Ha ocurrido un error creando la lista. Inténtalo de nuevo en unos minutos.');
            },
            success: function(data){
                var idListaNueva = data;
                var campos = $('#createAndAdd').serialize() + '&list='+idListaNueva;
                console.log(campos);

                destino = $('#addToListForm').attr('action') + '?' + campos;
                addToList(destino);
                $("#modalCrear").modal("hide");
            }
        });
    });
});
