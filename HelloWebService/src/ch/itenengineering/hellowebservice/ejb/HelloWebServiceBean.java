package ch.itenengineering.hellowebservice.ejb;

import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless
@WebService(serviceName = "HelloWebService", endpointInterface = "ch.itenengineering.hellowebservice.ejb.HelloWebService")
public class HelloWebServiceBean implements HelloWebService {

	public String echo(String message) {

		return "echo from HelloWebServiceBean - received message = " + message;
	}

}
