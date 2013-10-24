
<%@ page import="com.objectpartners.reservation.ReservationResult" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'reservationResult.label', default: 'ReservationResult')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-reservationResult" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-reservationResult" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list reservationResult">
			
				<g:if test="${reservationResultInstance?.reservationDate}">
				<li class="fieldcontain">
					<span id="reservationDate-label" class="property-label"><g:message code="reservationResult.reservationDate.label" default="Reservation Date" /></span>
					
						<span class="property-value" aria-labelledby="reservationDate-label"><g:link controller="reservationDate" action="show" id="${reservationResultInstance?.reservationDate?.id}">${reservationResultInstance?.reservationDate?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${reservationResultInstance?.resultDate}">
				<li class="fieldcontain">
					<span id="resultDate-label" class="property-label"><g:message code="reservationResult.resultDate.label" default="Result Date" /></span>
					
						<span class="property-value" aria-labelledby="resultDate-label"><g:formatDate date="${reservationResultInstance?.resultDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${reservationResultInstance?.totalBalls}">
				<li class="fieldcontain">
					<span id="totalBalls-label" class="property-label"><g:message code="reservationResult.totalBalls.label" default="Total Balls" /></span>
					
						<span class="property-value" aria-labelledby="totalBalls-label"><g:fieldValue bean="${reservationResultInstance}" field="totalBalls"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${reservationResultInstance?.id}" />
					<g:link class="edit" action="edit" id="${reservationResultInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
