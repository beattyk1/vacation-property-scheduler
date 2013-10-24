package com.objectpartners.reservation

@TestFor(ReservationDateController)
@Mock([ReservationDate, Property])
class ReservationDateControllerTests {
	Property property
	
	@Override
	public void setUp() {
		property = new Property(name:'CABIN', description:'WI Cabin')
		property.save()
	}

    def populateValidParams(params) {
        assert params != null
        params["startDate"] = new Date()
		params["endDate"] = new Date() + 5
		params["reserved"] = false
		params["property"] = property
    }
	

    void testIndex() {
        controller.index()
        assert "/reservationDate/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.reservationDateInstanceList.size() == 0
        assert model.reservationDateInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.reservationDateInstance != null
    }

    void testSave() {
        controller.save()

        assert model.reservationDateInstance != null
        assert view == '/reservationDate/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/reservationDate/show/1'
        assert controller.flash.message != null
        assert ReservationDate.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/reservationDate/list'

        populateValidParams(params)
        def reservationDate = new ReservationDate(params)

        assert reservationDate.save() != null

        params.id = reservationDate.id

        def model = controller.show()

        assert model.reservationDateInstance == reservationDate
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/reservationDate/list'

        populateValidParams(params)
        def reservationDate = new ReservationDate(params)

        assert reservationDate.save() != null

        params.id = reservationDate.id

        def model = controller.edit()

        assert model.reservationDateInstance == reservationDate
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/reservationDate/list'

        response.reset()

        populateValidParams(params)
        def reservationDate = new ReservationDate(params)

        assert reservationDate.save() != null

        // test invalid parameters in update
        params.id = reservationDate.id
        params.startDate = null

        controller.update()

        assert view == "/reservationDate/edit"
        assert model.reservationDateInstance != null

        reservationDate.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/reservationDate/show/$reservationDate.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        reservationDate.clearErrors()

        populateValidParams(params)
        params.id = reservationDate.id
        params.version = -1
        controller.update()

        assert view == "/reservationDate/edit"
        assert model.reservationDateInstance != null
        assert model.reservationDateInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/reservationDate/list'

        response.reset()

        populateValidParams(params)
        def reservationDate = new ReservationDate(params)

        assert reservationDate.save() != null
        assert ReservationDate.count() == 1

        params.id = reservationDate.id

        controller.delete()

        assert ReservationDate.count() == 0
        assert ReservationDate.get(reservationDate.id) == null
        assert response.redirectedUrl == '/reservationDate/list'
    }
}
