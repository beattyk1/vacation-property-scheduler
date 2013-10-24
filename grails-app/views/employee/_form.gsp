<%@ page import="com.objectpartners.reservation.Employee" %>



<div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'first', 'error')} ">
	<label for="first">
		<g:message code="employee.first.label" default="First" />
		
	</label>
	<g:textField name="first" value="${employeeInstance?.first}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'hireDate', 'error')} required">
	<label for="hireDate">
		<g:message code="employee.hireDate.label" default="Hire Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="hireDate" precision="day"  value="${employeeInstance?.hireDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'last', 'error')} ">
	<label for="last">
		<g:message code="employee.last.label" default="Last" />
		
	</label>
	<g:textField name="last" value="${employeeInstance?.last}"/>
</div>

