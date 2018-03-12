App.controller("ProjectController", ["$http", "$scope", "$q", "$state", "ProjectSvc", function ($http, $scope, $q, $state, $projectSvc) {

    var search = function (name) {
        var defer = $projectSvc.list(name);
        defer.done(function (data) {
            //$scope.ap
            $scope.$apply(function () {
                $scope.page = data.content;
            });

            $scope.page.url = $projectSvc.list_url;
            $scope.page.pagefunction = function (page, size) {
                var deferred = $.ajax({
                    type: "POST",
                    'url': $projectSvc.list_url,
                    dataType: 'json',
                    data: {
                        searchName: name,
                        "pageSize": size,
                        "pageNum": page
                    }
                });
                deferred.done(function (data) {
                    $scope.page.content = data.content;
                    $scope.page.first = data.first;
                    $scope.page.last = data.last;
                    $scope.page.number = data.number;
                    $scope.page.numberOfElements = data.numberOfElements;
                    $scope.page.size = data.size;
                    $scope.page.sort = data.sort;
                    $scope.page.totalElements = data.totalElements;
                    $scope.page.totalPages = data.totalPages;
                }, function (data) {

                });
            }
        });
    };

    $scope.init = function () {
        search("");
    };
    $scope.init();

    $scope.t_project = {
        "groupId": "com.wanmi",
        "artifactId": "license",
        "companyName": "",
        "defaultPassword": "",
        "licensingSubject": "",
    };
    $scope.page = {'content': {}};

    $scope.addpty = function () {
        $('#commentModal').modal('show');
    };
}]);