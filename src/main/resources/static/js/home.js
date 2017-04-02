(function () {

    $('.watch-btn').on("click", function () {
        var $this = $(this);
        $this.attr('disabled', true);
        var username = $this.parent().find("span").text();
        var data = {
            username: username,
            watch: $this.text() == 'Watch'
        };
        data[_csrf_param_name] = _csrf_token;

        $.post('watch', data, function (response) {
            if (response == 'success') {
                $this.text(data.watch ? 'Unwatch' : 'Watch');
            }
            $this.attr('disabled', false);
        });
    });

}());