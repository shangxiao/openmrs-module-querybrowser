<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<script data-main="${pageContext.request.contextPath}/moduleResources/querybrowser/app.js" src="${pageContext.request.contextPath}/moduleResources/querybrowser/require.js"></script>

<div id="querybrowserapp" ng-view>Loading...</div>

<script>

define('config', [], function() {
  return {
    resourceLocation: '${pageContext.request.contextPath}/moduleResources/querybrowser',
    contextPath: '${pageContext.request.contextPath}'
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
