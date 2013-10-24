<%@ page import="com.objectpartners.reservation.ReservationDate" %>



<div class="fieldcontain ${hasErrors(bean: reservationDateInstance, field: 'startDate', 'error')} required">
	<label for="startDate">
		<g:message code="reservationDate.startDate.label" default="Start Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="startDate" precision="day"  value="${reservationDateInstance?.startDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: reservationDateInstance, field: 'endDate', 'error')} required">
	<label for="endDate">
		<g:message code="reservationDate.endDate.label" default="End Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="endDate" precision="day"  value="${reservationDateInstance?.endDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: reservationDateInstance, field: 'property', 'error')} required">
	<label for="property">
		<g:message code="reservationDate.property.label" default="Property" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="property" name="property.id" from="${com.objectpartners.reservation.Property.list()}" optionKey="id" required="" value="${reservationDateInstance?.property?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: reservationDateInstance, field: 'reserved', 'error')} ">
	<label for="reserved">
		<g:message code="reservationDate.reserved.label" default="Reserved" />
		
	</label>
	<g:checkBox name="reserved" value="${reservationDateInstance?.reserved}" />
</div>

