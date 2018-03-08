
var App = angular.module('App', ['ngRoute','ui.router']);

App.controller("MainController",["$scope",function($scope){
    $scope.name = "MainController";
}]);

App.config(['$httpProvider', function(httpProvider) {
	httpProvider.interceptors.push(['$rootScope', function($rootScope) {
		return {
            request: function(config) {
                config.headers = config.headers||{};
                //config.headers['X-XSRF-TOKEN'] = _csrf.value;
                return config;
            }
        }
	}]);
}]);

jQuery.ajaxSetup({
	beforeSend: function (xhr) {
		//xhr.setRequestHeader('X-XSRF-TOKEN', _csrf.value);
	}
})

App.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
 
	$urlRouterProvider.otherwise('/dailyreportmanage');
	
    $stateProvider
    .state('projectmembereole', {
        url: '/projectmemberrole',
        templateUrl: "projectmemberrole",
	    controller:"ProjectmemberroleController"
    })
    .state('projectmanage', {
        url: '/projectmanage',
        templateUrl: "projectmanage",
	    controller:"ProjectmanageController"
    })
	;
}]);
