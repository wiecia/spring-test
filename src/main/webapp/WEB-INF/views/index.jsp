<jsp:root version="2.0" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sf="http://www.springframework.org/tags/form"
	xmlns:s="http://www.springframework.org/tags">
	
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>
	
	<jsp:directive.page contentType="text/html;charset=UTF-8" />
	<jsp:directive.page pageEncoding="UTF-8" />
	
<html>
<head>
	<s:url value="/resources/js/jquery.pnotify.js" var="pnotify_js_url"/>
	<s:url value="/resources/js/jquery-ui-1.8.17.custom.min.js" var="jqueryui_js"/>
	<s:url value="/resources/js/core/messagesController.js" var="sjs_messages"/>
	
	<s:url value="/resources/css/jquery.pnotify.default.css" var="pnotify_css_url"/>
	<s:url value="/resources/css/cupertino/jquery-ui-1.8.17.custom.css" var="jqueryui_css"/>
	<s:url value="/resources/css/notification.css" var="notification_css"/>
	
	
	<link type="text/css" rel="stylesheet" href="${jqueryui_css}"/>
	<link type="text/css" rel="stylesheet" href="${pnotify_css_url}"/>
	<link type="text/css" rel="stylesheet" href="${notification_css}"/>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"> <!-- //fixme --> </script>
	<script type="text/javascript" src="${jqueryui_js}"> <!-- //fixme --> </script>
	<script type="text/javascript" src="${pnotify_js_url}"> <!-- //fixme --> </script>
	<script type="text/javascript" src="${sjs_messages}"> <!-- //fixme --> </script>
	
	<script type="text/javascript">
	$(document).ready(function() {
			$('#dialog').dialog({
				modal: true,
				autoOpen: false
			});
		});
	</script>
</head>
<body>
	<h1>It Works!</h1>
	
	<ul>
	<c:forEach items="${people}" var="p">
		<li><c:out value="${p.name}, ${p.age}, ${p.email}"/></li>
	</c:forEach>
	</ul>
	
	<s:url value="/getFords" var="fords_url"/>
	<script type="text/javascript">
		var mc = new SJS.MessagesController();
		var reqFailed = function(data, status) {
			//$('errors2').html('Login failed, please try again.');
			//mc.addMessage('error', ['Failure', 'Request failed']);
			mc.addError('Zonk!', 'Something wrooong happened!');
		};

		var showData = function(json) {
			//alert(json[0].carModel);
			var html = '<ul>';
			
			//for (var car in json) {
	    	$.each(json, function(index, value) {
	    		html += '<li>' + value.mark + ' ' + value.carModel + '</li>';
	    	});
			//var ford = jQuery.parseJSON(car);
			//html += '<li>' + ford.id + ', ' + ford.carModel + '</li>';
			html += '</ul>';
			$('#dialog').html(html).dialog('open');
		};
		
		$(document).ready(function() {
			var formData = 	$("#ajaxForm").serialize();
			getAjaxFords = function(data, status) {
				$.ajax({
					url: "${fords_url}",
					type: 'POST',
					/* data: formData, */
					success: function(data, status) {
						/* mc.addMessage('success', ['Success', 'You have successfuly logged in!']); */
						mc.addInfo('Success', 'Got JSON data!<br/><strong>Yuppie</strong>');
						showData(data);
					},
					error: reqFailed
				});
			};
		});
	</script>
	
	<div id="ajaxArea">
		<form id="ajaxForm" method="post" action="${fords_url}" onsubmit="getAjaxFords(); return false;">
			<input type="submit" value="Get Fords"/>
		</form>
		<button onclick="getAjaxFords();">Get Fords2</button>
	</div>
	
	<div id="dialog" title="Fords listing"></div>
</body>
</html>

</jsp:root>