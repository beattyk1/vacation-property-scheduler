package com.objectpartners.reservation

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
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
		// Don't get a ball until you have worked into the next month
		assertEquals(0, result)
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
