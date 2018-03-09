App.controller("ProjectController", ["$http", "$scope", "$q", "$state", "ProjectSvc", function ($http, $scope, $q, $state, $projectSvc) {

    $scope.init = function () {

    };
    $scope.init();


    $scope.addpty = function () {
        $('#commentModal').modal('show');
    };
}]);