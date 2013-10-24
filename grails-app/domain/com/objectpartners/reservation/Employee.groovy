package com.objectpartners.reservation

class Employee {
	String first
	String last
	Date hireDate
	
	static constraints = {
		first(blank:false)
		last(blank:false)
		hireDate(blank: false)
	}
}
