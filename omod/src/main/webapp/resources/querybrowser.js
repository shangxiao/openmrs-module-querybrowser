define(['angular', 'config'], function(angular, config) {

  var querybrowser = angular.module('querybrowser', []);

  querybrowser.config(['$routeProvider', function($routeProvider){
    $routeProvider.
      when('/', {controller: 'QueryBrowserCtrl', templateUrl: config.resourceLocation + '/QueryBrowser.html'});
  }]);

  return querybrowser;
});
