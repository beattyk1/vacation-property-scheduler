package com.objectpartners.reservation

class Reservation {
	Employee employee
	ReservationDate reservationDate
	Integer selectionOrder
	ReservationResult reservationResult
	Boolean removed
	Boolean awarded
	Integer balls
	
    static constraints = {
		awarded(blank:true, nullable:true)
		balls(blank:true, nullable:true)
		reservationResult(blank:true, nullable:true)	
    }
}
