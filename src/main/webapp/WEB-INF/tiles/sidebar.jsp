<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="false" %>
<c:if test="${!empty query_response.facetFields}">
	<table class="table">
		<tr>
			<th>Name</th>
			<th>Count</th>
		</tr>
		
		<c:forEach items="${query_response.facetFields}" var="facetFields">
		<tr>
			<th><c:out value="${facetFields.name}" /></th>
			<th></th>
		</tr>
		<c:forEach items="${facetFields.values}" var="facetField">
		<tr>
		<c:set var="filterQuery" value=""/>
		<c:if test="${!empty filter_query}">
			<c:set var="filterQuery" value="&pfq=${filter_query}"/>
		</c:if>
		
				<spring:url value="/?q={other}&fq={field}:{payCodeCode}{previousFilter}" var="editUrl">
					<spring:param name="other" value="${solr_query.query}" />
					<spring:param name="field" value="${facetFields.name}" />
					<spring:param name="payCodeCode" value="${facetField.name}" />
					<spring:param name="previousFilter" value="${filterQuery}" />
				</spring:url>
				
			<td><a href="${fn:escapeXml(editUrl)}">${facetField.name}</a></td>
			<td><c:out value="${facetField.count}" /></td>
		</tr>
		</c:forEach>
		</c:forEach>

	
	<c:forEach var="entry" items="${query_response.facetQuery}">
				<spring:url value="/?q={other}&fq={payCodeCode}" var="priceUrl">
					<spring:param name="other" value="${solr_query.query}" />
					<spring:param name="payCodeCode" value="${entry.key}" />
				</spring:url>
				
<tr> <td> <a href="${fn:escapeXml(priceUrl)}">${entry.key}</a></td><td> ( <c:out value="${entry.value}"/> )</td></tr>
	</c:forEach>
	</table>


</c:if>
<c:if test="${empty query_response.facetFields}">
	There are currently no payCodes scheduled.
</c:if>
