<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<spring:url value="/resources" var="resources" />

<script type="text/javascript" src="${resources}/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${resources}/js/jquery-ui-1.8.16.custom.min.js"></script> 
<script type="text/javascript" src="${resources}/js/jquery.validate.js"></script>
<script type="text/javascript">
    var jq = jQuery.noConflict();
</script>

			<div id="topBg"></div>
			<div id="topBar">
				<div id="topBarInside">
					<div class="staticLinks">
						<div id="globalNav">
							<ul id="global-actions">
								<spring:url value="/j_spring_security_logout" var="logout" />
								<li id="global9"><a href="${logout}">Logout</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			