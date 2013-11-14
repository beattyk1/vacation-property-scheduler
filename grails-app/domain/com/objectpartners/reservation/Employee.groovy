package com.objectpartners.reservation

class Employee {
	String login
	String password
	String firstName
	String lastName
	Date hireDate
	
	static constraints = {
		login blank: false, size:5..15,matches:/[\S]+/, unique:true
		password blank:false, size:5..15, matches:/[\S]+/
		firstName blank:false 
		lastName blank:false
		hireDate blank:false
	}
}
