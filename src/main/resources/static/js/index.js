var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            bookName: $('#bookName').val(),
            author: $('#author').val(),
            content: $('#content').val(),
            visibility: $('#visibility').val()
        };

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/log/writeo',
            contentType: 'application/json',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}