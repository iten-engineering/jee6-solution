package ch.itenengineering.helloworld.jsf;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.itenengineering.helloworld.ejb.HelloWorldRemote;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class Index implements Serializable {

	@EJB
	HelloWorldRemote remote;

	public Index() {
		super();
	}

	public String getGreeting() {
		return remote.helloWorld("Hello EJB 3.1 World from JSF");
	}

} // end
