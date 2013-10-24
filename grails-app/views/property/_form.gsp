<%@ page import="com.objectpartners.reservation.Property" %>



<div class="fieldcontain ${hasErrors(bean: propertyInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="property.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${propertyInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: propertyInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="property.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${propertyInstance?.name}"/>
</div>

