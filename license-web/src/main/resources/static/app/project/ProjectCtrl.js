App.controller("ProjectController", ["$http", "$scope", "$q", "$state", "ProjectSvc", function ($http, $scope, $q, $state, $projectSvc) {

    $scope.init = function () {

    };
    $scope.init();

    $scope.t_project = {
        "groupId": "com.wanmi",
        "artifactId": "license",
        "companyName": "",
        "defaultPassword": "",
        "licensingSubject": "",
    };

    $scope.addpty = function () {
        $('#commentModal').modal('show');
    };
}]);