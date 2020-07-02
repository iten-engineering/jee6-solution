package ch.itenengineering.interceptor.ejb;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.itenengineering.interceptor.domain.Reservation;

@Stateless
@Interceptors(ch.itenengineering.interceptor.interceptors.TraceInterceptor.class)
public class TicketAgencyBean implements TicketAgencyRemote {

	@PersistenceContext(unitName = "InterceptorPU")
	private EntityManager em;

	public void clear() {
		em.createQuery("delete from Reservation").executeUpdate();
	}

	@Interceptors(ch.itenengineering.interceptor.interceptors.BookingRulesInterceptor.class)
	public int book(String customer, String event, int quantity)
			throws Exception {

		Reservation reservation = new Reservation(customer, event, quantity);

		em.persist(reservation);

		return reservation.getId();

	}
} // end of class
