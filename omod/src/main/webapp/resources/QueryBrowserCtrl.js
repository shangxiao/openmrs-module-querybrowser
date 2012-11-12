define(['querybrowser', 'config'], function(querybrowser, config) {
  querybrowser.controller('QueryBrowserCtrl', ['$scope', '$http', function($scope, $http) {
    $scope.results = {};
    $scope.submitQuery = function() {
      $http.post(config.contextPath + "/ws/querybrowser/query", $scope.query).success(function(results){
        $scope.results = results;
      });
    };
  }]);
});
