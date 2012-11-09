define(['angular', 'QueryBrowserCtrl'], function(angular) {
  var appRoot = document.getElementById("querybrowserapp");
  appRoot.setAttribute("ng-app", "querybrowser");
  angular.element(appRoot).ready(function() {
    angular.bootstrap(appRoot, ["querybrowser"]);
  });
});
