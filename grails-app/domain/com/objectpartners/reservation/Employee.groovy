package com.objectpartners.reservation

class Employee {
	String firstName
	String lastName
	Date hireDate
	
	static constraints = {
		firstName(blank:false)
		lastName(blank:false)
		hireDate(blank:false)
	}
}
