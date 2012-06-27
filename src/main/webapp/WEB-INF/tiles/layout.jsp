<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<spring:url value="/resources" var="resources" />
<link rel="stylesheet" type="text/css" media="screen" href="${resources}/css/ui-lightness/jquery-ui-1.8.16.custom.css" />
<spring:url value="/resources/css/style.css" var="css" />
<link rel="stylesheet" type="text/css" href="${css}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="SKYPE_TOOLBAR" content="SKYPE_TOOLBAR_PARSER_COMPATIBLE" />
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
<div id="main">
<div id="header">
	<div id="headerTopOuter"><tiles:insertAttribute name="header" /></div>
</div>
<div id="sidebar">
	<tiles:insertAttribute name="sidebar" />
</div>
<div id="content">
	<tiles:insertAttribute name="content" />
</div>
<div id="footer">
	<tiles:insertAttribute name="footer" />
</div>
</div>



</body>
</html>