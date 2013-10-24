package com.objectpartners.reservation

import org.springframework.dao.DataIntegrityViolationException

class ReservationDateController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [reservationDateInstanceList: ReservationDate.list(params), reservationDateInstanceTotal: ReservationDate.count()]
    }

    def create() {
        [reservationDateInstance: new ReservationDate(params)]
    }

    def save() {
        def reservationDateInstance = new ReservationDate(params)
        if (!reservationDateInstance.save(flush: true)) {
            render(view: "create", model: [reservationDateInstance: reservationDateInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'reservationDate.label', default: 'ReservationDate'), reservationDateInstance.id])
        redirect(action: "show", id: reservationDateInstance.id)
    }

    def show(Long id) {
        def reservationDateInstance = ReservationDate.get(id)
        if (!reservationDateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservationDate.label', default: 'ReservationDate'), id])
            redirect(action: "list")
            return
        }

        [reservationDateInstance: reservationDateInstance]
    }

    def edit(Long id) {
        def reservationDateInstance = ReservationDate.get(id)
        if (!reservationDateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservationDate.label', default: 'ReservationDate'), id])
            redirect(action: "list")
            return
        }

        [reservationDateInstance: reservationDateInstance]
    }

    def update(Long id, Long version) {
        def reservationDateInstance = ReservationDate.get(id)
        if (!reservationDateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservationDate.label', default: 'ReservationDate'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (reservationDateInstance.version > version) {
                reservationDateInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'reservationDate.label', default: 'ReservationDate')] as Object[],
                          "Another user has updated this ReservationDate while you were editing")
                render(view: "edit", model: [reservationDateInstance: reservationDateInstance])
                return
            }
        }

        reservationDateInstance.properties = params

        if (!reservationDateInstance.save(flush: true)) {
            render(view: "edit", model: [reservationDateInstance: reservationDateInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'reservationDate.label', default: 'ReservationDate'), reservationDateInstance.id])
        redirect(action: "show", id: reservationDateInstance.id)
    }

    def delete(Long id) {
        def reservationDateInstance = ReservationDate.get(id)
        if (!reservationDateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservationDate.label', default: 'ReservationDate'), id])
            redirect(action: "list")
            return
        }

        try {
            reservationDateInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'reservationDate.label', default: 'ReservationDate'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'reservationDate.label', default: 'ReservationDate'), id])
            redirect(action: "show", id: id)
        }
    }
}
