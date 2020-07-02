package ch.itenengineering.cdi.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * <b>CDI Sample - Hello Servlet</b>
 * <p>
 * Run the application and enter the following url: <br />
 * {@link http://localhost:8080/cdi-hello/Hello}
 * </p>
 * References:
 * <ul>
 * <li>JBoss AS 7.0, Getting Started Developing Applications Guide</li>
 * </ul>
 */
@SuppressWarnings("serial")
@WebServlet("/Hello")
public class HelloServlet extends HttpServlet {

	@Inject
	HelloService helloService;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();

		try {
			writer.println("<html><head /><body>");
			
			writer.println("<h1>CDI Sample - Hello Servlet</h1>");
			
			writer.print("<h2>");
			writer.println(helloService.createGreeting("CDI"));
			writer.print("</h2>");
			

		} catch (Exception ex) {
			writer.println("request failed with exception: " + ex.toString());

		} finally {
			writer.println("</body></html>");
			writer.close();
		}
	}

}
