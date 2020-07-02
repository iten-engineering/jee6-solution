package ch.itenengineering.mex.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.itenengineering.mex.ejb.CurrencyType;
import ch.itenengineering.mex.ejb.MoneyExchange;

@ServletSecurity(@HttpConstraint(rolesAllowed = {"Customer", "VIP"}))
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	MoneyExchange mex;

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = null;

		try {

			// init
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();

			out.println("<h1>Money Exchange Sample</h1>");


			// get rates
			out.println("Rates for user " + request.getRemoteUser() + ": <br />");
			out.println("- 1 CHF = " + mex.getRate(CurrencyType.EUR)
					+ " EUR <br />");
			out.println("- 1 CHF = " + mex.getRate(CurrencyType.USD)
					+ " USD <br />");

			// Note:
			// - @RolesAllowed("VIP") does not protect methode correctly with JBoss AS 7.1.1.Final
			// - Details see https://community.jboss.org/thread/201423
			// get bonus
			// try {
			// 	out.println("with bonus of " + mex.getVIPBonus() + " percent");
			// } catch (EJBAccessException e) {
			// 	out.println("with no bonus, access denied with exception "
			// 			+ e.toString());
			// }

			// request successfully done
			out.println("<p>");
			out.println("Successfully called secured MoneyExchange EJB with authentication type : " + request.getAuthType());
			out.println("</p>");

		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 */
	public String getServletInfo() {
		return "Money Exchange Login Servlet";
	}
}
