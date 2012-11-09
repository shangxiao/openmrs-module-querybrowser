define(['querybrowser'], function(querybrowser) {
  querybrowser.controller('QueryBrowserCtrl', ['$scope', '$http', function($scope, $http) {

    $scope.results = {};

    $scope.submitQuery = function() {
      $http.post("/openmrs/ws/querybrowser/query", $scope.query).success(function(results){
        $scope.results = results;
      });
    };
  }]);
});
