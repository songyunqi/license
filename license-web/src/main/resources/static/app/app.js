var App = angular.module('App', ['ngRoute', 'ui.router']);


App.config(['$httpProvider', function (httpProvider) {
    httpProvider.interceptors.push(['$rootScope', function ($rootScope) {
        return {
            request: function (config) {
                config.headers = config.headers || {};
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

    $urlRouterProvider.otherwise('/project-list');

    $stateProvider
        .state('/project-list', {
            url: '/project-list',
            templateUrl: "/project/list-view",
            controller: "ProjectController"
        })
        .state('/parameter-list', {
            url: '/parameter-list',
            templateUrl: "/parameter/list-view",
            controller: "ProjectController"
        })
    ;
}]);
