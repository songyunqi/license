/*上传控件*/
App.directive('fileOnChange',function(){
	return {
		restrict: 'A',
		link:function(scope,element,attrs){
			var onChangeFunc = scope.$eval(attrs.fileOnChange);
			element.bind('change', onChangeFunc);//将change绑定到ng上下文.
		}
	}
});
/*分页指令*/
App.directive('pagenation',function(){
	return {
		restrict: 'AE',
		replace: 'true',
		template: '<div style="float: right;">'
				+     '<div style="display:inline-block;text-align:center;vertical-align: middle;">'
				+         '<span>共{{page.totalElements}}条，分{{page.totalPages}}页，一页{{page.size}}条</span>'
				+     '</div>&nbsp;&nbsp;'
				+     '<div style="display:inline-block;text-align:center;vertical-align: middle;">'
				+	      '<ul class="pagination" style="margin-left:4px;margin-right:4px;">'
				+	          '<li ng-class="{true: \'disabled\', false: \'\'}[page.first]" style="cursor:pointer;"><a class="firstpage">«</a></li>'
				+	          '<li ng-class="{true: \'disabled\', false: \'\'}[page.first]" style="cursor:pointer;"><a class="prepage">‹</a></li>'
				+	          '<li><a>{{(page.number+1)}}</a></li>'
				+	          '<li ng-class="{true: \'disabled\', false: \'\'}[page.last]" style="cursor:pointer;"><a class="nextpage">›</a></li>'
				+	          '<li ng-class="{true: \'disabled\', false: \'\'}[page.last]" style="cursor:pointer;"><a class="lastpage">»</a></li>'
				+	      '</ul>'
				+     '</div>&nbsp;&nbsp;'
				+     '<div style="display:inline-block;text-align:center;vertical-align: middle;">'
				+	      '<span>跳至第</span>&nbsp;'
				+	      '<input type="text" class="pagenum" style="width: 40px;">&nbsp;'
				+	      '<span>页</span>&nbsp;&nbsp;'
				+	      '<button type="button" class="btn btn-sm btn-primary gotopage">Go</button>'
				+     '</div>'
				+ '</div>',
		link:function(scope,element,attrs){
			element.find('.firstpage').bind('click',function(){
				if(!scope.page){return;}
				if(scope.page.first){
					return;
				}
				var page = scope.page.number-1;
				var size = scope.page.size;
				if(scope.page&&scope.page.pagefunction){
					scope.page.pagefunction(0,size);
				}
				element.find('.pagenum').val('');
			});
			element.find('.prepage').bind('click',function(){
				if(!scope.page){return;}
				if(scope.page.first){
					return;
				}//pre
				var page = scope.page.number==0?0:(scope.page.number-1);
				var size = scope.page.size;
				if(page>=0){
					scope.page.pagefunction(page,size);
				}
				element.find('.pagenum').val('');
			});
			element.find('.nextpage').bind('click',function(){
				if(!scope.page){return;}
				if(scope.page.last){
					return;
				}//next
				var page = (!scope.page.last)?(scope.page.number+1):(scope.page.totalPages-1);
				var size = scope.page.size;
				if(scope.page&&scope.page.pagefunction){
					scope.page.pagefunction(page,size);
				}
				element.find('.pagenum').val('');
			});
			element.find('.lastpage').bind('click',function(){
				if(!scope.page){return;}
				if(scope.page.last){
					return;
				}//last
				var page = scope.page.totalPages-1;
				var size = scope.page.size;
				if(scope.page&&scope.page.pagefunction){
					scope.page.pagefunction(page,size);
				}
				element.find('.pagenum').val('');
			});
			element.find('.pagenum').bind('keydown',function(e){
				
			});
			element.find('.gotopage').bind('click',function(){
				if(!scope.page){return;}
				var pagenum = element.find('.pagenum').val();
				if(!pagenum){return;}
				if(parseInt(pagenum)!=pagenum){
					element.find('.pagenum').val('');
					return;
				}
				var page = (pagenum>scope.page.totalPages)?scope.page.totalPages:pagenum;
				var size = scope.page.size;
				if(scope.page&&scope.page.pagefunction){
					scope.page.pagefunction((page-1),size);
				}
				if(pagenum>scope.page.totalPages){
					element.find('.pagenum').val(scope.page.totalPages);
				}
			});
		}
	}
});

App.directive('pagermini',function(){
	return {
		restrict: 'AE',
		replace: 'true',
		template: '<div style="float: right;">'

				+     '<div style="display:inline-block;text-align:center;vertical-align: middle;">{{page.number+1}}/{{page.totalPages}}({{page.totalElements}})</div>&nbsp;&nbsp;'
				+     '<div style="display:inline-block;text-align:center;vertical-align: middle;">'
				+	      '<ul class="pagination" style="margin-left:4px;margin-right:4px;">'
				+	          '<li ng-class="{true: \'disabled\', false: \'\'}[page.first]" style="cursor:pointer;"><a class="firstpage">«</a></li>'
				+	          '<li ng-class="{true: \'disabled\', false: \'\'}[page.first]" style="cursor:pointer;"><a class="prepage">‹</a></li>'
				+	          '<li><a>{{(page.number+1)}}</a></li>'
				+	          '<li ng-class="{true: \'disabled\', false: \'\'}[page.last]" style="cursor:pointer;"><a class="nextpage">›</a></li>'
				+	          '<li ng-class="{true: \'disabled\', false: \'\'}[page.last]" style="cursor:pointer;"><a class="lastpage">»</a></li>'
				+	      '</ul>'
				+     '</div>'
				+ '</div>',
		link:function(scope,element,attrs){
			element.find('.firstpage').bind('click',function(){
				if(!scope.page){return;}
				if(scope.page.first){
					return;
				}
				var page = scope.page.number-1;
				var size = scope.page.size;
				if(scope.page&&scope.page.pagefunction){
					scope.page.pagefunction(0,size);
				}
				element.find('.pagenum').val('');
			});
			element.find('.prepage').bind('click',function(){
				if(!scope.page){return;}
				if(scope.page.first){
					return;
				}//pre
				var page = scope.page.number==0?0:(scope.page.number-1);
				var size = scope.page.size;
				if(page>=0){
					scope.page.pagefunction(page,size);
				}
				element.find('.pagenum').val('');
			});
			element.find('.nextpage').bind('click',function(){
				if(!scope.page){return;}
				if(scope.page.last){
					return;
				}//next
				var page = (!scope.page.last)?(scope.page.number+1):(scope.page.totalPages-1);
				var size = scope.page.size;
				if(scope.page&&scope.page.pagefunction){
					scope.page.pagefunction(page,size);
				}
				element.find('.pagenum').val('');
			});
			element.find('.lastpage').bind('click',function(){
				if(!scope.page){return;}
				if(scope.page.last){
					return;
				}//last
				var page = scope.page.totalPages-1;
				var size = scope.page.size;
				if(scope.page&&scope.page.pagefunction){
					scope.page.pagefunction(page,size);
				}
				element.find('.pagenum').val('');
			});
			element.find('.pagenum').bind('keydown',function(e){
				
			});
			element.find('.gotopage').bind('click',function(){
				if(!scope.page){return;}
				var pagenum = element.find('.pagenum').val();
				if(!pagenum){return;}
				if(parseInt(pagenum)!=pagenum){
					element.find('.pagenum').val('');
					return;
				}
				var page = (pagenum>scope.page.totalPages)?scope.page.totalPages:pagenum;
				var size = scope.page.size;
				if(scope.page&&scope.page.pagefunction){
					scope.page.pagefunction((page-1),size);
				}
				if(pagenum>scope.page.totalPages){
					element.find('.pagenum').val(scope.page.totalPages);
				}
			});
		}
	}
});


App.filter("projectRole",function(){
	return function (role) {
		if(role==0){return "项目管理者";}
	    return "项目成员";
	}
});
App.filter("currentCom",function(){
	return function (role) {
		if(role){return "是";}
	    return "否";
	}
});

App.filter("loadName",function(){
	return function(input,_srcData){
		for(var item in _srcData){
			var data = _srcData[item];
			if(data.id==input){
				return data.name || data.nameCn;
			}
		}
	}
});

//输入框与下拉组合控件
App.directive('combotree',function(){
	return {
		restrict: 'EA',
		replace: 'true',
		transclude : true,
        scope : {
        	//control:'='夫作用域
        	control:'@'
	    },
		template:"<div>" 
			    +    "<div class='form-group'>"
			    +        "<label class='col-sm-2 control-label'>{{control.name}}:</label>"
			    +        "<div class='col-sm-6'>"
			    +            "<input class='form-control' placeholder='{{control.placeholder}}' id='{{control.id}}'>"
			    +        "</div>"
			    +        "<div class='col-sm-2'>"
			    +            "<button type='button' class='btn btn-link'>{{control.btnText}}</button>"
			    +        "</div>"
				+    "</div>"
				+    "<div class='wrap' style='display:none;position:absolute;z-index:10001'>"
				+        "<ul class='ztree' style='margin-top:0;'></ul>"
				+    "</div>"
				+"</div>",
		link:function(scope,element,attrs){
						
			element.find("input").click(function(){
				var obj = element.find("input");
				var objOffset = obj.offset();
				element.find(".wrap.ztree").css({width:obj.outerWidth() + "px"});
				element.find(".wrap.ztree").parent().css({left:objOffset.left + "px", top:objOffset.top + obj.outerHeight() + "px"}).slideDown("fast");
			});
			
			var button = element.find("button");
			var input = element.find("input");
			var wrap = element.find("wrap");
			$("body").bind("mousedown", function(){
				//下拉框内部点击与输入框内部点击不消失
				if (($(event.target).parents(".wrap").length>0)||event.target.id==$(input).attr('id')){
					return;
				}
				element.find('.wrap').fadeOut("fast");
			});
		}
	}
});

App.filter("stateFilter", function() {
    var filterfun = function(item) {
        var ret = "未知";
        if(item.state==0){
        	ret = "进行中";
        }else if(item.state==1){
        	ret = "完成";
        }
        return ret;
    };
    return filterfun;
});

App.directive('modal',function(){
	return{
		restrict : 'AE',
		replace : true,
		template : '<div class="modal fade" id="commentModal" tabindex="-1" role="dialog">' +
						'<div class="modal-dialog" class="col-md-12">' +
							'<form>	' +
								'<div class="modal-content">	' +
									'<div class="modal-header">' +
										'<button type="button" class="close" data-dismiss="modal"></button>' +
										'<h4 class="modal-title">{{affirmTitle}}</h4>' +
									'</div>' +

									'<div class="modal-body">' +
										'<div class="form-horizontal" role="form">' +
											'<div class="modal-body">{{affirmContent}}</div>' +
										'</div>' +
									'</div>' +

									'<div class="modal-footer">' +
										'<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">取消</button>' +
										'<button type="submit" class="btn btn-sm btn-primary" ng-click="" id="submitBtn">确定</button>' +
									'</div>' +
								'</div>' +
							'</form>' +
						'</div>' +
					'</div>',
		link : function(scope,element,attr){
			element.find('#submitBtn').bind('click',function(){
				scope.$apply(attr.submitmethod);
				$('.modal').modal('hide');
			});
		}
	}
});
