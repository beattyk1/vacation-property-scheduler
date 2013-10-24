import grails.util.Environment;

import javax.media.j3d.EnvironmentSet;

import com.objectpartners.reservation.Employee
import com.objectpartners.reservation.Property
import com.objectpartners.reservation.Reservation
import com.objectpartners.reservation.ReservationDate

class BootStrap {

    def init = { servletContext ->
		if (Environment.current == Environment.DEVELOPMENT) {
			def today = new Date()
			
			def cabin = new Property(name:'CABIN', description:'WI Cabin')
			cabin.save()
			
			def condo = new Property(name:'CONDO', description:'WI Condo')
			condo.save()
						
			def resDate1 = new ReservationDate(property : cabin, reserved : new Boolean('false'), startDate : today+0, endDate : today+3).save()

			def resDate2 = new ReservationDate(property : cabin, reserved : new Boolean('false'), startDate : today+3, endDate : today+8).save()

			def resDate3 = new ReservationDate(property : condo, reserved : new Boolean('false'), startDate : today+10, endDate : today+15).save()

			def resDate4 = new ReservationDate(property : condo, reserved : new Boolean('false'), startDate : today+15, endDate : today+18).save()
			
			def resDate5 = new ReservationDate(property : condo, reserved : new Boolean('false'), startDate : today+20, endDate : today+25).save()

		
			def kevin = new Employee(firstName: "Kevin", lastName:"Beatty", hireDate : today-395).save()
			def moe = new Employee(firstName: "Moe", lastName:"Howard", hireDate : today-1500).save()
			def larry = new Employee(firstName: "Larry", lastName:"Fine", hireDate : today-3).save()
			def curly = new Employee(firstName: "Curly", lastName:"Howard", hireDate : today-3650).save()
			
			def removed = new Boolean(false);
			
			new Reservation(employee : kevin, reservationDate : resDate2, selectionOrder : 1, removed : removed).save()
			new Reservation(employee : kevin, reservationDate : resDate1, selectionOrder : 2, removed : removed).save()
			new Reservation(employee : kevin, reservationDate : resDate3, selectionOrder : 3, removed : removed).save()
			
			new Reservation(employee : moe, reservationDate : resDate1, selectionOrder : 2, removed : removed).save()
			new Reservation(employee : moe, reservationDate : resDate2, selectionOrder : 3, removed : removed).save()
			new Reservation(employee : moe, reservationDate : resDate3, selectionOrder : 4, removed : removed).save()
			
			new Reservation(employee : larry, reservationDate : resDate3, selectionOrder : 1, removed : removed).save()
			new Reservation(employee : larry, reservationDate : resDate1, selectionOrder : 2, removed : removed).save()
			new Reservation(employee : larry, reservationDate : resDate4, selectionOrder : 3, removed : removed).save()
			
			new Reservation(employee : curly, reservationDate : resDate1, selectionOrder : 1, removed : removed).save()
			new Reservation(employee : curly, reservationDate : resDate2, selectionOrder : 2, removed : removed).save()
			new Reservation(employee : curly, reservationDate : resDate4, selectionOrder : 3, removed : removed).save()
			
			
		}
		
    }
    def destroy = {
    }
}
