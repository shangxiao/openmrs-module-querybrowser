<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<script data-main="/openmrs/moduleResources/querybrowser/app.js" src="/openmrs/moduleResources/querybrowser/require.js"></script>

<div id="querybrowserapp" ng-view>Loading...</div>

<script>

define('config', [], function() {
  return {
    resourceLocation: '/openmrs/moduleResources/querybrowser'
  };
});

requirejs.config({
    shim: {
        'angular': {
        	deps: ['jquery'],
            exports: 'angular'
        }
    }
});

require(['app'], function() {});
</script>

<%@ include file="/WEB-INF/template/footer.jsp"%>