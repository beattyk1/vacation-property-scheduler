package com.objectpartners.reservation

class ReservationResult {

	ReservationDate reservationDate
	Date resultDate
	Integer totalBalls
	static constraints = {
		reservationDate(blank : false)
		resultDate(blank : false)
		totalBalls(blank : false)
	}
	
}
