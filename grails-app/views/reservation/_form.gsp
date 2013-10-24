<%@ page import="com.objectpartners.reservation.Reservation" %>



<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'awarded', 'error')} ">
	<label for="awarded">
		<g:message code="reservation.awarded.label" default="Awarded" />
		
	</label>
	<g:checkBox name="awarded" value="${reservationInstance?.awarded}" />
</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'balls', 'error')} ">
	<label for="balls">
		<g:message code="reservation.balls.label" default="Balls" />
		
	</label>
	<g:field name="balls" type="number" value="${reservationInstance.balls}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'reservationResult', 'error')} ">
	<label for="reservationResult">
		<g:message code="reservation.reservationResult.label" default="Reservation Result" />
		
	</label>
	<g:select id="reservationResult" name="reservationResult.id" from="${com.objectpartners.reservation.ReservationResult.list()}" optionKey="id" value="${reservationInstance?.reservationResult?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'employee', 'error')} required">
	<label for="employee">
		<g:message code="reservation.employee.label" default="Employee" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="employee" name="employee.id" from="${com.objectpartners.reservation.Employee.list()}" optionKey="id" required="" value="${reservationInstance?.employee?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'removed', 'error')} ">
	<label for="removed">
		<g:message code="reservation.removed.label" default="Removed" />
		
	</label>
	<g:checkBox name="removed" value="${reservationInstance?.removed}" />
</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'reservationDate', 'error')} required">
	<label for="reservationDate">
		<g:message code="reservation.reservationDate.label" default="Reservation Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="reservationDate" name="reservationDate.id" from="${com.objectpartners.reservation.ReservationDate.list()}" optionKey="id" required="" value="${reservationInstance?.reservationDate?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'selectionOrder', 'error')} required">
	<label for="selectionOrder">
		<g:message code="reservation.selectionOrder.label" default="Selection Order" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="selectionOrder" type="number" value="${reservationInstance.selectionOrder}" required=""/>
</div>

