
<%@ page import="com.objectpartners.reservation.ReservationDate" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'reservationDate.label', default: 'ReservationDate')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="list-reservationDate" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="table table-striped">
				<thead>
					<tr>
					
						<g:sortableColumn property="startDate" title="${message(code: 'reservationDate.startDate.label', default: 'Start Date')}" />
					
						<g:sortableColumn property="endDate" title="${message(code: 'reservationDate.endDate.label', default: 'End Date')}" />
					
						<th><g:message code="reservationDate.property.label" default="Property" /></th>
					
						<g:sortableColumn property="reserved" title="${message(code: 'reservationDate.reserved.label', default: 'Reserved')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${reservationDateInstanceList}" status="i" var="reservationDateInstance">
					<tr>
					
						<td><g:formatDate format="dd-MMM-yyy" date="${reservationDateInstance.startDate}" /></td>
						
						<td><g:formatDate format="dd-MMM-yyy" date="${reservationDateInstance.endDate}" /></td>
					
						<td>${reservationDateInstance.property.name}</td>
					
						<td><g:formatBoolean boolean="${reservationDateInstance.reserved}" /></td>
						
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${reservationDateInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
