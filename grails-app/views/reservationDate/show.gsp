
<%@ page import="com.objectpartners.reservation.ReservationDate" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'reservationDate.label', default: 'ReservationDate')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="show-reservationDate" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list reservationDate">
			
				<g:if test="${reservationDateInstance?.startDate}">
				<li class="fieldcontain">
					<span id="startDate-label" class="property-label"><g:message code="reservationDate.startDate.label" default="Start Date" /></span>
					
						<span class="property-value" aria-labelledby="startDate-label"><g:formatDate date="${reservationDateInstance?.startDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${reservationDateInstance?.endDate}">
				<li class="fieldcontain">
					<span id="endDate-label" class="property-label"><g:message code="reservationDate.endDate.label" default="End Date" /></span>
					
						<span class="property-value" aria-labelledby="endDate-label"><g:formatDate date="${reservationDateInstance?.endDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${reservationDateInstance?.property}">
				<li class="fieldcontain">
					<span id="property-label" class="property-label"><g:message code="reservationDate.property.label" default="Property" /></span>
					
						<span class="property-value" aria-labelledby="property-label"><g:link controller="property" action="show" id="${reservationDateInstance?.property?.id}">${reservationDateInstance?.property?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${reservationDateInstance?.reserved}">
				<li class="fieldcontain">
					<span id="reserved-label" class="property-label"><g:message code="reservationDate.reserved.label" default="Reserved" /></span>
					
						<span class="property-value" aria-labelledby="reserved-label"><g:formatBoolean boolean="${reservationDateInstance?.reserved}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${reservationDateInstance?.id}" />
					<g:link class="edit" action="edit" id="${reservationDateInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
