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
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
            success: (res) => {
                var msg = '글이 등록되었습니다.';
                alert(msg);
                location.reload();
            },
            error:(error) => {
                console.log(error)
                alert('에러가 발생했습니다.');
            }
        });
    }

};
console.log("1111??")
main.init();