
var App = angular.module('App', ['ngRoute','ui.router']);

App.controller("MainController",["$scope",function($scope){
    $scope.name = "asdasd";
}]);

App.config(['$httpProvider', function(httpProvider) {
	httpProvider.interceptors.push(['$rootScope', function($rootScope) {
		return {
            request: function(config) {
                config.headers = config.headers||{};
                config.headers['X-XSRF-TOKEN'] = _csrf.value;
                return config;
            }
        }
	}]);
}]);

jQuery.ajaxSetup({
	beforeSend: function (xhr) {
		xhr.setRequestHeader('X-XSRF-TOKEN', _csrf.value);
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
     .state('projectType', {
        url: '/projectType',
        templateUrl: "projectType",
	    controller:"ProjectTypeController"
    })
    .state('test', {
        url: '/projecttypemanage',
        templateUrl: "projecttypemanage",
	    controller:"ProjecttypemanageController" 
    })
    .state('modifypassword', {
        url: '/modifypassword',
        templateUrl: "account/modifypasswordui",
	    controller:"PasswordController"
    })
    .state('TestLogin', {
        url: '/Testlogin',
        templateUrl: "/Testloginui",
	    controller:"TestController" 
    })
    .state('monthlyexpense',{
    	url:'/monthlyexpense',
    	templateUrl: "/monthlyexpense",
    	controller:"ExpenseController"
    })
    .state('expenseitem',{
    	url:'/expenseitem',
    	templateUrl: "/expenseitem",
    	controller:"ExpenseCtrl"
    })
    
    .state('dailyreportmanage', {
        url: '/dailyreportmanage',
        templateUrl: "dailyreportex/selfreport",
        controller:"SelfReportController"
    })
    .state('subdailyreport', {
        url: '/subdailyreport',
        templateUrl: "dailyreportex/subdailyreport",
        controller:"SubDailyReportController"
    })
    .state('projectdailyreport', {
        url: '/projectdailyreport',
        templateUrl: "dailyreportex/projectdailyreport",
        controller:"ProjectReportController"
    })
    .state('profile', {
        url: '/profile',
        templateUrl: "profile",
	    controller:"ProfileController"
    })
    .state('profile.basic', {
        url: '/profile.basic',
        templateUrl: "profilebasic",
	    controller:"BasicInfoController"
    })
    .state('profile.project', {
        url: '/profile.project',
        templateUrl: "profileprojectinfo",
	    controller:"ProjectInfoController"
    })
    .state("/report/write", {
	    templateUrl: "report/write",
	    controller:"ReportController"
	})
	.state("/report/reportList", {
	    templateUrl: "report/reportList",
	    controller:"ReportController"
	})
	.state("/report/subordinateReportList", {
	    templateUrl: "report/subordinateReportList",
	    controller:"ReportController"
	})
	.state("/orgmember", {
		url: '/orgmember',
        templateUrl: "orgmember/orgmemberman",
	    controller:"OrgMemberController"
	})
	.state("/orgmemberman", {
		url: '/orgmemberman',
        templateUrl: "orgmember/orgmemberman1",
	    controller:"OrgMemberManController"
	})
	.state("/projectmemberman", {
		url: '/projectmemberman',
        templateUrl: "project/projectmemberman",
	    controller:"ProjectMemberController"
	})    	
	
    .state('404', {
        url: '/404',
        templateUrl: '404.htm'
    })
    .state("personalinformation", {
    	url: '/personalinformation',
        templateUrl: "personalinformation",
	    controller:"PersonalInformation"
	})
	.state("infocenter", {
    	url: '/infocenter',
        templateUrl: "infocenter",
	    controller:"InfoCenter"
	})
	.state("companyinfo", {
    	url: '/companyinfo',
        templateUrl: "companyinfo",
	    controller:"CompanyInfo"
	})
	.state("companytype", {
    	url: '/companytype',
        templateUrl: "companytype",
	    controller:"CompanyType"
	})
	.state("memberrole", {
		url: '/memberrole',
		templateUrl: "memberrole",
		controller:"MemberRole"
	})
	.state("orgmemberrole", {
		url: '/orgmemberrole',
		templateUrl: "orgmemberrole",
		controller:"OrgMemberRoles"
	})
	.state("employeemanage", {
		url: '/employeemanage',
	    templateUrl: "employeemanage",
	    controller:"EmployeeManageController"
	})
	.state("employeeInfo", {
		url: '/employeeInfo',
	    templateUrl: "employeeInfo",
	    controller:"EmployeeManageController"
	})
	.state("postmanage", {
    	url: '/postmanage',
	    templateUrl: "postmanage",
	    controller:"PostController"
	})
    .state("employsoumanage", {
    	url: '/employsoumanage',
	    templateUrl: "employsoumanage",
	    controller:"EmploySouController"
	})
	.state("employeeSource", {
    	url: '/employeeSource',
	    templateUrl: "employeeSource",
	    controller:"EmpSourceCtrl"
	})
	.state("menuManage",{
		url : '/menuManage',
		templateUrl : 'menu/menuManage',
		controller : 'MenuCtrl'
	})
	.state("roleManage",{
		url : '/roleManage',
		templateUrl : 'role/roleManage',
		controller : 'RoleCtrl'
	})
	.state("budgetitemlist", {
    	url: '/budgetitemlist',
	    templateUrl: "budget/budgetitemlist",
	    controller:"BudgetListCtrl"
	})
	.state("budgetitemman", {
    	url: '/budgetitemman',
	    templateUrl: "budget/budgetitemman",
	    controller:"BudgetCtrl"
	})
	.state("todolist", {
    	url: '/todolist',
	    templateUrl: "todolist",
	    controller:"TodoController"
	})
	.state("myreport", {
    	url: '/myreport',
	    templateUrl: "dailyreportex/myreportpage",
	    controller:"MyRptController"
	})
	.state("userRole", {
		url: '/userRole',
		templateUrl: "securityRole/accountRole",
		controller:"UserRoleCtrl"
	})
	.state("noRight", {
		url: '/noRight',
		templateUrl: "noRight"
	})
	.state("control-sidebar-home-tab", {
		url: '/control-sidebar-home-tab',
		templateUrl: "control-sidebar-home-tab",
	})
	.state("control-sidebar-settings-tab", {
		url: '/control-sidebar-settings-tab',
		templateUrl: "control-sidebar-settings-tab",
	})
	.state("Entry", {
		url: '/Entry',
		templateUrl: "Entry",
		controller:"EntryController"
	})
	.state("fillExpense", {
		url: '/fillExpense',
		templateUrl: "expenseFill/expensePage",
		controller:"ExpenseFillController"
	})
	.state("expensetrip", {
		url: '/expensetrip',
		templateUrl: "expensetrip",
		controller:"ExpensetripController"
	})
	.state("askforleave", {
		url: '/askforleave',
		templateUrl: "leavepage",
		controller:"LeaveController"
	})
	.state("overtime",{
		url : '/overtime',
		templateUrl : 'overtime/overTimePage',
		controller : 'OverTimeCtrl'
	})
	.state("positive", {
		url: '/positive',
		templateUrl: "positivepage",
		controller:"PositiveController"
	})
	.state("turnover",{
		url : '/turnover',
		templateUrl : 'turnover/turnoverPage',
		controller : 'TurnoverCtrl'
	})
	.state("trip",{
		url : '/trip',
		templateUrl : 'trip',
		controller : 'TripController'
	})
	.state("selfProcess",{
		url : '/selfProcess',
		templateUrl : 'selfProcess/selfProcessPage',
		controller : 'selfProcessCtrl'
	})
	.state("attdpage",{
		url : '/attdpage',
		templateUrl : 'attdpage',
		controller : 'AtdController'
	})
	.state("hire",{
		url : '/hire',
		templateUrl : 'hire/hireProcessPage',
		controller : 'hireCtrl'
	})
	.state("reportcount",{
		url : '/reportcount',
		templateUrl : 'dailyreportex/reportcount',
		controller : 'ReportcountController'
	})
	;
}]);


/*
App.config(['$routeProvider','$httpProvider', function ($routeProvider, $httpProvider) {
	$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
	$routeProvider
	.when("/modifypassword", {
	    templateUrl: "account/modifypasswordui",
	    controller:"PasswordController"
	})
	.when("/report/write", {
	    templateUrl: "report/write",
	    controller:"ReportController"
	})
	.when("/report/reportList", {
	    templateUrl: "report/reportList",
	    controller:"ReportController"
	})
	.when("/report/subordinateReportList", {
	    templateUrl: "report/subordinateReportList",
	    controller:"ReportController"
	})
	.otherwise({
	    redirectTo: "/index"
	});
}]);
*/
