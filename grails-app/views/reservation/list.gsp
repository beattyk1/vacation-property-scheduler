
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
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="awarded" title="${message(code: 'reservation.awarded.label', default: 'Awarded')}" />
					
						<g:sortableColumn property="balls" title="${message(code: 'reservation.balls.label', default: 'Balls')}" />
					
						<th><g:message code="reservation.reservationResult.label" default="Reservation Result" /></th>
					
						<th><g:message code="reservation.employee.label" default="Employee" /></th>
					
						<g:sortableColumn property="removed" title="${message(code: 'reservation.removed.label', default: 'Removed')}" />
					
						<th><g:message code="reservation.reservationDate.label" default="Reservation Date" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${reservationInstanceList}" status="i" var="reservationInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${reservationInstance.id}">${fieldValue(bean: reservationInstance, field: "awarded")}</g:link></td>
					
						<td>${fieldValue(bean: reservationInstance, field: "balls")}</td>
					
						<td>${fieldValue(bean: reservationInstance, field: "reservationResult")}</td>
					
						<td>${fieldValue(bean: reservationInstance, field: "employee")}</td>
					
						<td><g:formatBoolean boolean="${reservationInstance.removed}" /></td>
					
						<td>${fieldValue(bean: reservationInstance, field: "reservationDate")}</td>
					
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
