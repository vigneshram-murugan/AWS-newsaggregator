var app = angular.module('app',['ui.materialize']);



app.controller('nyTimesController', function($scope, $http, $rootScope){
	$scope.nyTimes = [];
	$scope.showModal = false;
	$http.get('/get-nytimes-news').success(function(data){
		$scope.nyTimes = data;
	})
	
	$scope.openModal = function(data) {
		$scope.modalContent = data;
		$scope.showModal = !$scope.showModal;
	}
	
	$scope.finished = function() {
		$('.materialboxed').materialbox();
	}
})

app.controller('buzzFeedController', function($scope, $http, $rootScope){
	$http.get('/get-buzzfeed-news').success(function(data){
		$scope.buzzFeed = data;
	})
})

app.controller('newsApiController', function($scope, $http, $rootScope){
	$http.get('/get-newsapi-news').success(function(data){
		$scope.newApi = data;
	})
})

app.directive('modalCustom', function(){
	return {
		restrict : 'AE',
		templateUrl : '/modal.html',
		scope : {
			show : '@hideshow'
		},
		link : function(scope, ele, attr) {
			console.log(scope.show)
			scope.$watch('show',function(){
				console.log(scope.show)
			},true)
		}
	}
})

