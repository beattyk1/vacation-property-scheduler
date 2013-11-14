
<%@ page import="com.objectpartners.reservation.Reservation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'reservation.label', default: 'Reservation')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="list-reservation" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="table table-striped">
				<thead>
					<tr>	
					
						<th><g:message code="reservation.employee.label" default="Name" /></th>
					
						<g:sortableColumn property="selectionOrder" title="Selection Order" />
						
						<th><g:message code="reservation.reservationDate.property" default="Property" /></th>			
					
						<g:sortableColumn property="removed" title="${message(code: 'reservation.removed.label', default: 'Already Reserved')}" />
					
						<th><g:message code="reservation.reservationDate.label" default="Reservation Date" /></th>
						
						
						
						<g:sortableColumn property="awarded" title="${message(code: 'reservation.awarded.label', default: 'Awarded')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${reservationInstanceList}" status="i" var="reservationInstance">
	
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${reservationInstance.employee.firstName} ${reservationInstance.employee.lastName}</td>
					
						<td>${reservationInstance.selectionOrder}</td>
						
						<td>${reservationInstance.reservationDate.property.name}</td>
					
						<td><g:formatBoolean boolean="${reservationInstance.removed}" /></td>
					
						<td><g:formatDate format="dd-MMM-yyy" date="${reservationInstance.reservationDate.startDate}" /> to <g:formatDate format="dd-MMM-yyyy" date="${reservationInstance.reservationDate.endDate}"/>
						</td>
						
						<td><g:link action="show" id="${reservationInstance.id}">${fieldValue(bean: reservationInstance, field: "awarded")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${reservationInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
