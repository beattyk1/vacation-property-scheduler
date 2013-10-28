
<%@ page import="com.objectpartners.reservation.ReservationResult" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'reservationResult.label', default: 'ReservationResult')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="list-reservationResult" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="reservationResult.reservationDate.label" default="Reservation Date" /></th>
					
						<g:sortableColumn property="resultDate" title="${message(code: 'reservationResult.resultDate.label', default: 'Result Date')}" />
					
						<g:sortableColumn property="totalBalls" title="${message(code: 'reservationResult.totalBalls.label', default: 'Total Balls')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${reservationResultInstanceList}" status="i" var="reservationResultInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${reservationResultInstance.id}">${fieldValue(bean: reservationResultInstance, field: "reservationDate")}</g:link></td>
					
						<td><g:formatDate date="${reservationResultInstance.resultDate}" /></td>
					
						<td>${fieldValue(bean: reservationResultInstance, field: "totalBalls")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${reservationResultInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
