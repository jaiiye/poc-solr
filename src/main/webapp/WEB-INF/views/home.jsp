<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

<form:form modelAttribute="solr_query">
	<form:label path="query">Query String:</form:label>
	<form:input path="query"/><br />
	<input type="submit" value="Search" />
</form:form>


<P>  The qTime was ${query_response.QTime}. </P>

<c:set var="myKey" value="${query_response['response']}"/>
<P>  The number found was ${num_found}. </P>

<c:if test="${!empty query_response.facetFields}">

<c:set var="myKey" value="id"/>

	<c:forEach var="doc" items="${query_response.results}">
		<div class="product">
			<div class="product_id"><c:out value="${doc['id']}"/></div>
			<div class="product_name"><c:out value="${doc['name']}"/></div>
			<div class="product_description"><c:out value="${doc['description']}"/></div>
		</div>
	</c:forEach>
</c:if>
<c:if test="${empty query_response.facetFields}">
	There are currently no payCodes scheduled.
</c:if>



</body>
</html>
