(function(){
var app = angular.module("myapp",['ngRoute'])
app.config(['$routeProvider',
			       function($routeProvider) {
			           $routeProvider.
			               when('/users', {
			                   templateUrl: 'users.html',
			                   controller: 'UserController'
			               }).
			               when('/question', {
			                   templateUrl: 'question.html',
			                   controller: 'QuestionsController'
			               }).
			               when('/response', {
			                   templateUrl: 'response.html',
			                   controller: 'ResponsesController'
			               }).
			               otherwise({
			                   redirectTo: '/'
			               });
}]);

app.factory('Data', function () {

    var data = {
        emailId: '',
        password: ''
    };

    return {
        getemailId: function () {
            return data.emailId;
        },
        setemailId: function (emailid) {
            data.emailId = emailid;
        },
        getpassword: function () {
            return data.password;
        },
        setpassword: function (pass) {
            data.password = pass;
        }
    };
});


app.controller("HelloController", function($scope) {
		$scope.helloTo = {};
		$scope.helloTo.title = "Hello World, AngularJS";
	});

app.controller("UserController", function($http, $log, $scope, $window, Data) {
	$scope.users=[];
//	$scope.loading=true;
//	$log.debug("Getting users...");
//	$http.get('rest/user').
//	success(function(data, status, headers, config) {
//		$scope.loading=false;
//		$scope.users = data;
//	}).
//	error(function(data,status,headers,config) {
//		$scope.loading=false;
//		$scope.error=status;
//	});
	$scope.showAddForm=true;
	$scope.successAddForm=false;
	$scope.activateLogin = function() {
		$scope.showLoginForm=true;
		$scope.showAddForm=false;
		$scope.successAddForm=false;
	}
	$scope.activateSignIn = function() {
		$scope.showLoginForm=false;
		$scope.showAddForm=true;
		$scope.successAddForm=false;
	}
	$scope.addUser = function(user) {
		$http.post("rest/user",user).
		success(function(data) {
			$log.debug(data);
			$scope.successAddForm=true;
			$scope.users = data.emailId;
			$scope.user.emailId = '';
			$scope.user.password = '';
			//$scope.users.push(user);
		}).
		error(function(data,status,headers,config) {
		});
	}
	
	$scope.validateUser = function(user) {
		$http.post("rest/user/validate",user).
		success(function(data) {
			if(typeof data.emailId === 'undefined'){
				$log.debug("Validation Failure");
				$window.location = "http://localhost:8080/vmongo/error.html"
			} else {
				$log.debug("Validation Success");
				//$log.debug(data.emailId);
				Data.setemailId(data.emailId);
				Data.setpassword(data.password);
//				$(function() {
//				    $.session.set("sessionuid", data.id);
//				});
				//var row = "<tr><td>"+data.emailId+"</td><td>"+data.password+"</td></tr>";
				//$("#userTable").append(row);
				$window.location = "http://localhost:8080/vmongo/index.html#question"
			}
		}).
		error(function(data,status,headers,config) {
		});
	}
});

app.controller("QuestionsController", function($http, $log, $scope, $window, Data) {
	$scope.users=[];
	$log.debug("Getting questions...");
	$http.get('rest/user').
	success(function(data, status, headers, config) {
	
//		Array.prototype.contains = function(v) {
//		    for(var i = 0; i < data.length; i++) {
//		        if(data[i].question === v) return true;
//		    }
//		    return false;
//		};
//		
//		var arr = [];
//	    for (var index=0; index<data.length; index++) {
//	        if(!arr.contains(data[index].question)) {
//	            arr.push(data[index]);
//	        }
//	    }
//		
//		
//		$log.debug(arr);
		
		$scope.users = data;
		
	}).
	error(function(data,status,headers,config) {
		$scope.error=status;
	});
	
	$scope.postQuerry = function(user) {
		user.emailId = Data.getemailId();
		user.password = Data.getpassword();
		$http.post("rest/user/question",user).
		success(function(data) {
			$window.location.reload();
		}).
		error(function(data,status,headers,config) {
		});
	}
});

app.controller("ResponsesController", function($http, $log, $scope, $window, $routeParams, Data) {
	$scope.users = [];
	$log.debug("Getting Responses...");
    
	$http.post("rest/user/responserequest",$routeParams.ques).
	success(function(data) {
		$log.debug(data);
		$scope.users = data;
		//$window.location.reload();
	}).
	error(function(data,status,headers,config) {
	});
	
	$scope.postResponse = function(user) {
		user.emailId = Data.getemailId();
		user.password = Data.getpassword();
		user.question = $routeParams.ques;
		$http.post("rest/user/response",user).
		success(function(data) {
			$window.location.reload();
		}).
		error(function(data,status,headers,config) {
		});
	}
	
});

app.directive('product', function() {
	var directive = {};
	directive.restrict = 'E';
	directive.templateUrl = "templates/product.html";
	directive.scope = {
		prod : "=product"
	}
	return directive;
});
})();