package com.objectpartners.reservation

import grails.test.mixin.TestFor

@TestFor(ExecuteLotteryService)
class ExecuteLotteryServiceTests {

    void testPickRandomNumberBasedOnTotalBallCount() {
		def totalBallCount = 5;
        def service = new ExecuteLotteryService()
		def foundZero = false;
		def foundMaxValue = false;
		(0..1000).each {
			Integer result = service.pickRandomNumberBasedOnTotalBallCount(totalBallCount) 
			if (!foundMaxValue && result == (totalBallCount-1)) {
				foundMaxValue = true;
			}
			else if (!foundZero && result == 0) {
				foundZero = true;
			}
			assertTrue("Must be >= 0", result >= 0)
			assertTrue("Must be <= ${totalBallCount-1}", result <= totalBallCount-1)
        }
		
		assertTrue("Should have found 0 during this test", foundZero)
		assertTrue("Should have found ${totalBallCount} during this test", foundMaxValue)
    }
	
	void testCalculateMonthsAtOPINewHire() {
		def service = new ExecuteLotteryService()
		Integer result = service.calculateMonthsAtOPI(new Date())
        /* You get one 'pity' ball during your first month
           This was done so that the system doesn't need to deal with "0" */
        assertEquals(1, result)
	}
	
	void testCalculateMonthsAtOPIOneYear() {
		def service = new ExecuteLotteryService()
		def today = new Date().toCalendar()
		def year = today.get(Calendar.YEAR);
		def month = today.get(Calendar.MONTH)
		def hireDate = new GregorianCalendar(year-1, month, 15).getTime() // halfway through the hire month
		Integer result = service.calculateMonthsAtOPI(hireDate)
		assertEquals(12, result)
	}
}
