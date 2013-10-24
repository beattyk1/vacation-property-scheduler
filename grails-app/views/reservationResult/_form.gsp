<%@ page import="com.objectpartners.reservation.ReservationResult" %>



<div class="fieldcontain ${hasErrors(bean: reservationResultInstance, field: 'reservationDate', 'error')} required">
	<label for="reservationDate">
		<g:message code="reservationResult.reservationDate.label" default="Reservation Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="reservationDate" name="reservationDate.id" from="${com.objectpartners.reservation.ReservationDate.list()}" optionKey="id" required="" value="${reservationResultInstance?.reservationDate?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: reservationResultInstance, field: 'resultDate', 'error')} required">
	<label for="resultDate">
		<g:message code="reservationResult.resultDate.label" default="Result Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="resultDate" precision="day"  value="${reservationResultInstance?.resultDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: reservationResultInstance, field: 'totalBalls', 'error')} required">
	<label for="totalBalls">
		<g:message code="reservationResult.totalBalls.label" default="Total Balls" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="totalBalls" type="number" value="${reservationResultInstance.totalBalls}" required=""/>
</div>

