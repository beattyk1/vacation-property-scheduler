package com.objectpartners.reservation

class ExecuteLotteryController {

	def executeLotteryService
	
    def index() { 
		render 'Lottery Contoller'
	}
	
	def execute(Long id) {
		if (id == 27) {
			render 'Attempting to run lottery.  Status=' + executeLotteryService.run()
		}
		else {
			render 'Permission Denied'
		}
	}
}
