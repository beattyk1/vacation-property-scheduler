package com.objectpartners.reservation

class ReservationDate {
	Date startDate;
	Date endDate;
	Property property;
	Boolean reserved;
	
    static constraints = {
		startDate(blank : false)
		endDate(blank : false)
		property(blank : false)
    }
}
