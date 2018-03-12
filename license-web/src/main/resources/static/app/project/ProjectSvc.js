App.service('ProjectSvc', function ($http) {
    this.list_url = "project/list";
    this.list = function (name) {
        var url = "project/list";
        var defer = $.ajax({
            type: "POST",
            'url': url,
            dataType: 'json',
            data: {
                searchName: name,
            }
        });
        return defer;
    };
});