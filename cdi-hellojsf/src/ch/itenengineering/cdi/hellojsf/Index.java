package ch.itenengineering.cdi.hellojsf;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * <b>CDI Sample - Hello JSF</b>
 * <p>
 * Run the application and enter the following url: <br />
 * {@link http://localhost:8080/cdi-hellojsf}
 * </p>
 * References:
 * <ul>
 * <li>Oracle, Java EE 6 Tutorial</li>
 * <li>JBoss AS 7.0, Getting Started Developing Applications Guide</li>
 * </ul>
 */
@Named
@RequestScoped
public class Index {

	@Inject
	@Relaxed
	GreetingService service;

	String name;
	String greeting;

	public void sayHello() {
		this.greeting = service.createGreeting(this.name);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGreeting() {
		return this.greeting;
	}

}
