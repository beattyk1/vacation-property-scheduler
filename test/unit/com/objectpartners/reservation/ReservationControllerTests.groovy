package com.objectpartners.reservation

@TestFor(ReservationController)
@Mock([Reservation, Employee, ReservationDate, Property])
class ReservationControllerTests {
	Employee employee
	ReservationDate reservationDate
	
	@Override
	public void setUp() {
		def today = new Date()
		Property cabin = new Property(name:'CABIN', description:'WI Cabin').save()
		employee = new Employee(firstName: "Kevin", lastName:"Beatty", hireDate : today-395).save()
		reservationDate = new ReservationDate(property : cabin, reserved : new Boolean('false'), startDate : today+0, endDate : today+3).save()
	}

    def populateValidParams(params) {
        assert params != null
        params["employee"] = employee
		params["reservationDate"] = reservationDate
		params["selectionOrder"] = 1
		params["removed"] = false
    }

    void testIndex() {
        controller.index()
        assert "/reservation/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.reservationInstanceList.size() == 0
        assert model.reservationInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.reservationInstance != null
    }

    void testSave() {
        controller.save()

        assert model.reservationInstance != null
        assert view == '/reservation/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/reservation/show/1'
        assert controller.flash.message != null
        assert Reservation.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/reservation/list'

        populateValidParams(params)
        def reservation = new Reservation(params)

        assert reservation.save() != null

        params.id = reservation.id

        def model = controller.show()

        assert model.reservationInstance == reservation
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/reservation/list'

        populateValidParams(params)
        def reservation = new Reservation(params)

        assert reservation.save() != null

        params.id = reservation.id

        def model = controller.edit()

        assert model.reservationInstance == reservation
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/reservation/list'

        response.reset()

        populateValidParams(params)
        def reservation = new Reservation(params)

        assert reservation.save() != null

        // test invalid parameters in update
        params.id = reservation.id
        // Add invalid values to params object
		params.employee = null

        controller.update()

        assert view == "/reservation/edit"
        assert model.reservationInstance != null

        reservation.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/reservation/show/$reservation.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        reservation.clearErrors()

        populateValidParams(params)
        params.id = reservation.id
        params.version = -1
        controller.update()

        assert view == "/reservation/edit"
        assert model.reservationInstance != null
        assert model.reservationInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/reservation/list'

        response.reset()

        populateValidParams(params)
        def reservation = new Reservation(params)

        assert reservation.save() != null
        assert Reservation.count() == 1

        params.id = reservation.id

        controller.delete()

        assert Reservation.count() == 0
        assert Reservation.get(reservation.id) == null
        assert response.redirectedUrl == '/reservation/list'
    }
}
