package com.objectpartners.reservation

import java.math.MathContext

class ExecuteLotteryService {
	
	def sessionFactory
	
	def String run() {
		
		while(true) {
			List<ReservationDate> reservationDates = getAllAvailableReservationDates()
			if (!reservationDates) {
				return "All reservation dates have been taken"
			}

			List<Reservation> reservations = getCurrentRoundOfReservations() // highest priority of each employee
			if (!reservations) {
				return "All reservation requests have been processed"
			}

			Map<ReservationDate, List<Reservation>> reservationDateMap = associateReservationDateToReservations(reservationDates, reservations)

			executeLottery(reservationDateMap)
		}
	}
	
	def void executeLottery(Map<ReservationDate, List<Reservation>> reservationDateMap) {

		// For each date (key), run the lottery for those reservations (value)
		for (Map.Entry<ReservationDate, List<Reservation>> entry : reservationDateMap.entrySet()) {
			ReservationDate reservationDate = entry.getKey()
			List<Reservation> reservations = entry.getValue()
			if (reservations) {
				// Reserve the date
				reservationDate.setReserved(true);
				
				// Add lottery balls to each reservation
				addLotteryBalls(reservations)
				
				// Determine total balls in the lottery
				Integer totalBallCount = calculateTotalBallCount(reservations)
				
				// Determine the winner
				Reservation winningReservation = selectTheWinner(reservations, totalBallCount)
				
				// Create a Reservation Result
				def reservationResult = new ReservationResult(reservationDate : reservationDate, resultDate: new Date(), totalBalls : totalBallCount)
				reservationResult.save()
				
				// Update the Reservations, set winner, non-winner
				reservations.each {Reservation reservation ->
					if (reservation.id == winningReservation.id) {
						reservation.setAwarded(true)
					}
					else {
						reservation.setAwarded(false)
					}
					reservation.setReservationResult(reservationResult)
				}
				
				// Remove this reservationDate from consideration for all employees
				List<Reservation> removeReservations = Reservation.findAllByReservationDate(reservationDate);			
				removeReservations.each {Reservation reservation ->
					reservation.setRemoved(true)
				}		
				
				// Remove all the winning employee's other reservation choices; you can only win once
				List<Reservation> remainingWinningEmployeeReservations = Reservation.findAllByEmployee(winningReservation.employee);
				remainingWinningEmployeeReservations.each {Reservation reservation ->
					reservation.setRemoved(true)
				}
			}
		}
	}
	
	def void addLotteryBalls(List<Reservation> reservations) {
		reservations.each {Reservation reservation ->
			reservation.setBalls(calculateMonthsAtOPI(reservation.employee.hireDate))
		}
	}
	
	def Integer calculateTotalBallCount(List<Reservation> reservations) {
		Integer count = 0
		reservations.each {Reservation reservation ->
			count += reservation.balls
		}
		return count
	}
	
	def Reservation selectTheWinner(List<Reservation> reservations, Integer totalBallCount) {
		Map<Integer, Reservation> lotteryMachine = new HashMap<Integer, Long>()
		
		// Add all choices to the lotteryMachine
		reservations.each{Reservation reservation ->
			Integer ballsAlreadyInMachine = lotteryMachine.size()
			Integer balls = reservation.balls; // For this employee
			for (int i=ballsAlreadyInMachine; i < (ballsAlreadyInMachine + balls); i++) {
				lotteryMachine.put(i, reservation)
			}
		}
		
		Integer theWinner = pickRandomNumberBasedOnTotalBallCount(totalBallCount)
		return lotteryMachine.get(theWinner)	
	}
	
	def Integer pickRandomNumberBasedOnTotalBallCount(Integer totalBallCount) {
		/*
		* 	For totalBallCount = 200
		*   Min Desired value = 0; (Min index in List)
		*   Max Desired value = 199 (totalBallCount-1); (Max index in List)
		* 
		*  	Formula: Min + (int)(Math.random() * ((Max - Min) + 1))
		*
		*/ 
		(int) Math.random()*totalBallCount
	}
	
	def Integer calculateMonthsAtOPI(Date date) {
		Date today = new Date();
		def monthBetween = (today[Calendar.MONTH] - date[Calendar.MONTH]) 
		def yearsBetween = today[Calendar.YEAR] - date[Calendar.YEAR]
		def months = monthBetween + (yearsBetween * 12)
		months = (months >= 0 ? months : 0) // can put a default value here for new hires, if desired
		return months
	}
	
	def List<ReservationDate> getAllAvailableReservationDates() {
		def reservationDateCriteria = ReservationDate.createCriteria()
		reservationDateCriteria.list {
			eq('reserved', new Boolean(false))
		}
	}
		
	/* Find current round of Reservation requests
	 * 
	 * There will be multiple entries for each employee in this table, ordered by selectionOrder.
	 * 
	 * Must get a single entry for each employee where the selection order is lowest and removed = false
	 * 
	 * For the first round, everyone will have a selectionOrder of '1' available. (i.e. Their first choice)
	 * After the first iteration one or more 'ReservationDate's will be taken by the winners 
	 * As ReservationDates are taken in the lottery, the 'removed' flag will be flipped to 'true' effectively removing the choice from consideration.
	 * 
	 * For for second round, selectionOrder will be the 'min' that remains in the Reservation Table because someone's second choice (selectionOrder=2) may have removed='true'
	 * in this case, this person's third choice would be used in the second round.
	 * 
	 */
	def List<Reservation> getCurrentRoundOfReservations() {
		def session = sessionFactory.currentSession
		def tx = session.beginTransaction()
		 session.createQuery("from Reservation r where r.selectionOrder = (select min(ri.selectionOrder) from Reservation ri where ri.employee = r.employee and ri.removed='false')").list()
	}
	
	/* For each reservationDate, gather up the Employees who requested this date as their highest choice */
	def Map<ReservationDate, List<Reservation>> associateReservationDateToReservations(List<ReservationDate> reservationDates, List<Reservation> reservations) {
		Map reservationDateMap = new HashMap<ReservationDate, List<Reservation>>()
		def reservationList
		for (ReservationDate reservationDate : reservationDates) {
			reservationList = new ArrayList<Reservation>()
			for (Reservation reservation : reservations) {
				if (reservation.reservationDate.id == reservationDate.id) {
					reservationList.add(reservation);
				}
			}
			reservationDateMap.put(reservationDate, reservationList)
		}
		return reservationDateMap
	}
}
