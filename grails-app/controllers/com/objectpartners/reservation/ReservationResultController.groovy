package com.objectpartners.reservation

import org.springframework.dao.DataIntegrityViolationException

class ReservationResultController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [reservationResultInstanceList: ReservationResult.list(params), reservationResultInstanceTotal: ReservationResult.count()]
    }

    def create() {
        [reservationResultInstance: new ReservationResult(params)]
    }

    def save() {
        def reservationResultInstance = new ReservationResult(params)
        if (!reservationResultInstance.save(flush: true)) {
            render(view: "create", model: [reservationResultInstance: reservationResultInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'reservationResult.label', default: 'ReservationResult'), reservationResultInstance.id])
        redirect(action: "show", id: reservationResultInstance.id)
    }

    def show(Long id) {
        def reservationResultInstance = ReservationResult.get(id)
        if (!reservationResultInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservationResult.label', default: 'ReservationResult'), id])
            redirect(action: "list")
            return
        }

        [reservationResultInstance: reservationResultInstance]
    }

    def edit(Long id) {
        def reservationResultInstance = ReservationResult.get(id)
        if (!reservationResultInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservationResult.label', default: 'ReservationResult'), id])
            redirect(action: "list")
            return
        }

        [reservationResultInstance: reservationResultInstance]
    }

    def update(Long id, Long version) {
        def reservationResultInstance = ReservationResult.get(id)
        if (!reservationResultInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservationResult.label', default: 'ReservationResult'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (reservationResultInstance.version > version) {
                reservationResultInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'reservationResult.label', default: 'ReservationResult')] as Object[],
                          "Another user has updated this ReservationResult while you were editing")
                render(view: "edit", model: [reservationResultInstance: reservationResultInstance])
                return
            }
        }

        reservationResultInstance.properties = params

        if (!reservationResultInstance.save(flush: true)) {
            render(view: "edit", model: [reservationResultInstance: reservationResultInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'reservationResult.label', default: 'ReservationResult'), reservationResultInstance.id])
        redirect(action: "show", id: reservationResultInstance.id)
    }

    def delete(Long id) {
        def reservationResultInstance = ReservationResult.get(id)
        if (!reservationResultInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservationResult.label', default: 'ReservationResult'), id])
            redirect(action: "list")
            return
        }

        try {
            reservationResultInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'reservationResult.label', default: 'ReservationResult'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'reservationResult.label', default: 'ReservationResult'), id])
            redirect(action: "show", id: id)
        }
    }
}
