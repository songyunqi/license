App.controller("ProjectController", ["$http", "$scope", "$q", "$state", "ProjectSvc", function ($http, $scope, $q, $state, $projectSvc) {

    var search = function (name) {
        var defer = $projectSvc.list(name);
        defer.done(function (data) {
            //$scope.ap
            $scope.$apply(function () {
                $scope.page.list = data.content.list;
                $scope.page.first = data.content.isFirstPage;
                $scope.page.last = data.content.isLastPage;
                $scope.page.number = data.content.pageNum;
                $scope.page.size = data.content.pageSize;
                $scope.page.numberOfElements = data.content.numberOfElements;
                $scope.page.sort = data.content.sort;
                $scope.page.totalElements = data.content.total;
                $scope.page.totalPages = data.content.pages;
                $scope.page.pages = data.content.pages;
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

                    $scope.$apply(function () {
                        $scope.page.list = data.content.list;
                        $scope.page.first = data.content.isFirstPage;
                        $scope.page.last = data.content.isLastPage;
                        $scope.page.number = data.content.pageNum;
                        $scope.page.size = data.content.pageSize;
                        $scope.page.numberOfElements = data.content.numberOfElements;
                        $scope.page.sort = data.content.sort;
                        $scope.page.totalElements = data.content.total;
                        $scope.page.totalPages = data.content.pages;
                        $scope.page.pages = data.content.pages;
                    });
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

    //参数
    $scope.prjParams = {
        "id":"",
        "projectId":"",
        "consumerAmount": "1",
        "consumerType": "",
        "issuer": "",
        "subject": "",
        "notBefore":"",
        "notAfter":"",
        "info":"",
        "extra":"",
        "extra1":"",
        "extra2":"",
        "extra3":""
    };

    $scope.page = {'content': {}};

    $scope.addpty = function () {
        $('#commentModal').modal('show');
    };

    $scope.editprj = function(prj){

        $.ajax({
            type: "POST",
            'url': "/project/queryById",
            dataType: 'json',
            data: {
                id: prj.id,
            }
        }).done(function(data){
            $scope.t_project = data.content;
            $scope.$apply();//需要手动刷新

            $('#commentModal').modal('show');
        });
    };

    $scope.editDetail = function(prj){

        $.ajax({
            type: "POST",
            'url': "/project/queryParamsById",
            dataType: 'json',
            data: {
                projectId: prj.id,
            }
        }).done(function(data){
            if(data.content){
                $scope.prjParams = data.content;
                if($scope.prjParams.extra&&$scope.prjParams.extra!=''){
                    var ext = JSON.parse($scope.prjParams.extra);
                    $scope.prjParams.extra1 = ext.extra1;
                    $scope.prjParams.extra2 = ext.extra2;
                    $scope.prjParams.extra3 = ext.extra3;
                }
            }
            $scope.prjParams.projectId = prj.id;
            $scope.$apply();//需要手动刷新

            $('#commentModal2').modal('show');
        });

    };

    $scope.savePrjParamer = function(){

        if(!$scope.myform.$valid){
            return;
        }

        var ext = {extra1:$scope.prjParams.extra1,
            extra2:$scope.prjParams.extra2,
            extra3:$scope.prjParams.extra3
        };

        $scope.prjParams.extra = JSON.stringify(ext);

        $.ajax({
            type: "POST",
            'url': "/project/saveParams",
            dataType: 'json',
            data:$scope.prjParams
        }).done(function(data){
            console.log(data.content);
            if(data.content&&data.content=='1'){
                $('#commentModal2').modal('hide');
                alert("数据保存成功");
            }else{
                alert("数据保存失败");
            }

        });

        console.log($scope.prjParams);
    };


    var flag = false;
    $scope.saveProject = function(){

        if(!$scope.myProject.$valid||flag){
            return;
        }

        flag = true;

        $.ajax({
            type: "POST",
            'url': "/project/saveProject",
            dataType: 'json',
            data:$scope.t_project
        }).done(function(data){
            flag = false;
            console.log(data.content);
            if(data.content&&data.content=='1'){
                $('#commentModal').modal('hide');
                alert("jar包打包成功");
            }else if(data.content&&data.content=='3'){
                alert("构件Id(artifactId) 已经存在需要手动删除磁盘上生成的文件！");
            }else{
                alert("数据保存失败");
            }
        });


    };
}]);