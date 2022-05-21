$(function () {
    $('#photoSorting').click(function (){
        let catalogStart = $('#catalogStart').val();
        let catalogFinish = $('#catalogFinish').val();
        if (!catalogStart) {
            $('#catalogStart').css("border-color", "red");
        } else {
            $('#catalogStart').css("border", "");
        }

        if (!catalogFinish) {
            $('#catalogFinish').css("border-color", "red");
        } else {
            $('#catalogFinish').css("border", "");
        }

        if (!catalogStart || !catalogFinish) {
            alert('Укажите каталог!')
            return;
        }

        $.ajax({
            url: '/sorting?catalogStart=' + encodeURIComponent(catalogStart) + '&catalogFinish=' + encodeURIComponent(catalogFinish),
            type: 'POST',
            success: function (result) {
                $('#result').text(result.result);
                //$('#result').text('Готово!');
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $('#result').text('Ошибка сортировки!');
            }
        });
    });
});
