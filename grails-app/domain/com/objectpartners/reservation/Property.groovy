package com.objectpartners.reservation

class Property {
	String name
	String description
	
	static constraints = {
		name(blank:false)
		description(blank:false)
	}
}
