<%@ page import="com.objectpartners.reservation.Property" %>



<div class="fieldcontain ${hasErrors(bean: propertyInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="property.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${propertyInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: propertyInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="property.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" required="" value="${propertyInstance?.description}"/>
</div>

