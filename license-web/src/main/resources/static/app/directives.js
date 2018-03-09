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
