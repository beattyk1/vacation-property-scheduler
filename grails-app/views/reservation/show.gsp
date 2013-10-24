
<%@ page import="com.objectpartners.reservation.Reservation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'reservation.label', default: 'Reservation')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-reservation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-reservation" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list reservation">
			
				<g:if test="${reservationInstance?.awarded}">
				<li class="fieldcontain">
					<span id="awarded-label" class="property-label"><g:message code="reservation.awarded.label" default="Awarded" /></span>
					
						<span class="property-value" aria-labelledby="awarded-label"><g:formatBoolean boolean="${reservationInstance?.awarded}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${reservationInstance?.balls}">
				<li class="fieldcontain">
					<span id="balls-label" class="property-label"><g:message code="reservation.balls.label" default="Balls" /></span>
					
						<span class="property-value" aria-labelledby="balls-label"><g:fieldValue bean="${reservationInstance}" field="balls"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${reservationInstance?.reservationResult}">
				<li class="fieldcontain">
					<span id="reservationResult-label" class="property-label"><g:message code="reservation.reservationResult.label" default="Reservation Result" /></span>
					
						<span class="property-value" aria-labelledby="reservationResult-label"><g:link controller="reservationResult" action="show" id="${reservationInstance?.reservationResult?.id}">${reservationInstance?.reservationResult?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${reservationInstance?.employee}">
				<li class="fieldcontain">
					<span id="employee-label" class="property-label"><g:message code="reservation.employee.label" default="Employee" /></span>
					
						<span class="property-value" aria-labelledby="employee-label"><g:link controller="employee" action="show" id="${reservationInstance?.employee?.id}">${reservationInstance?.employee?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${reservationInstance?.removed}">
				<li class="fieldcontain">
					<span id="removed-label" class="property-label"><g:message code="reservation.removed.label" default="Removed" /></span>
					
						<span class="property-value" aria-labelledby="removed-label"><g:formatBoolean boolean="${reservationInstance?.removed}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${reservationInstance?.reservationDate}">
				<li class="fieldcontain">
					<span id="reservationDate-label" class="property-label"><g:message code="reservation.reservationDate.label" default="Reservation Date" /></span>
					
						<span class="property-value" aria-labelledby="reservationDate-label"><g:link controller="reservationDate" action="show" id="${reservationInstance?.reservationDate?.id}">${reservationInstance?.reservationDate?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${reservationInstance?.selectionOrder}">
				<li class="fieldcontain">
					<span id="selectionOrder-label" class="property-label"><g:message code="reservation.selectionOrder.label" default="Selection Order" /></span>
					
						<span class="property-value" aria-labelledby="selectionOrder-label"><g:fieldValue bean="${reservationInstance}" field="selectionOrder"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${reservationInstance?.id}" />
					<g:link class="edit" action="edit" id="${reservationInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
