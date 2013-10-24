<%@ page import="com.objectpartners.reservation.Employee" %>



<div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="employee.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" required="" value="${employeeInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="employee.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" required="" value="${employeeInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'hireDate', 'error')} required">
	<label for="hireDate">
		<g:message code="employee.hireDate.label" default="Hire Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="hireDate" precision="day"  value="${employeeInstance?.hireDate}"  />
</div>

