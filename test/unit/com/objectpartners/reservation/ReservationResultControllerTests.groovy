package com.objectpartners.reservation

import grails.test.mixin.Mock
import grails.test.mixin.TestFor

@TestFor(ReservationResultController)
@Mock([ReservationResult, ReservationDate, Property])
class ReservationResultControllerTests {
	
	ReservationDate reservationDate
	
	@Override
	public void setUp() {
		def today = new Date()
		Property cabin = new Property(name:'CABIN', description:'WI Cabin').save()
		reservationDate = new ReservationDate(property : cabin, reserved : new Boolean('false'), startDate : today+0, endDate : today+3).save()
	}
    def populateValidParams(params) {
        assert params != null
        params["reservationDate"] = reservationDate
		params["resultDate"] = new Date()
		params["totalBalls"] = 50
    }

    void testIndex() {
        controller.index()
        assert "/reservationResult/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.reservationResultInstanceList.size() == 0
        assert model.reservationResultInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.reservationResultInstance != null
    }

    void testSave() {
        controller.save()

        assert model.reservationResultInstance != null
        assert view == '/reservationResult/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/reservationResult/show/1'
        assert controller.flash.message != null
        assert ReservationResult.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/reservationResult/list'

        populateValidParams(params)
        def reservationResult = new ReservationResult(params)

        assert reservationResult.save() != null

        params.id = reservationResult.id

        def model = controller.show()

        assert model.reservationResultInstance == reservationResult
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/reservationResult/list'

        populateValidParams(params)
        def reservationResult = new ReservationResult(params)

        assert reservationResult.save() != null

        params.id = reservationResult.id

        def model = controller.edit()

        assert model.reservationResultInstance == reservationResult
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/reservationResult/list'

        response.reset()

        populateValidParams(params)
        def reservationResult = new ReservationResult(params)

        assert reservationResult.save() != null

        // test invalid parameters in update
        params.id = reservationResult.id
        // Add invalid values to params object
		params.resultDate = null

        controller.update()

        assert view == "/reservationResult/edit"
        assert model.reservationResultInstance != null

        reservationResult.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/reservationResult/show/$reservationResult.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        reservationResult.clearErrors()

        populateValidParams(params)
        params.id = reservationResult.id
        params.version = -1
        controller.update()

        assert view == "/reservationResult/edit"
        assert model.reservationResultInstance != null
        assert model.reservationResultInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/reservationResult/list'

        response.reset()

        populateValidParams(params)
        def reservationResult = new ReservationResult(params)

        assert reservationResult.save() != null
        assert ReservationResult.count() == 1

        params.id = reservationResult.id

        controller.delete()

        assert ReservationResult.count() == 0
        assert ReservationResult.get(reservationResult.id) == null
        assert response.redirectedUrl == '/reservationResult/list'
    }
}
