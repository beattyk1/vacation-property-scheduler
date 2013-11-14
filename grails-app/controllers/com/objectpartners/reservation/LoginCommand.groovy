package com.objectpartners.reservation
import grails.validation.Validateable

@Validateable
class LoginCommand {
	
	String username
	String password
	private emp
	
	Employee getEmployee() {
		println 'getEmployee'
		if (!emp && username) {
			emp = Employee.findByLogin(username)
			println 'looking for emp'
			return emp
		}
	}
	static constraints = {
		username blank : false, validator:{val, cmd ->
			if(!cmd.employee)
				return "employee.not.found"
		}
		password blank : false, validator{val, cmd ->
			if (cmd.employee && cmd.employee.password != val)
				return "user.password.invalid"
		}
	}
}
