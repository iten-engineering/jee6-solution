package ch.itenengineering.migration.ejb;

import javax.ejb.Remote;

@Remote
public interface EJB3Remote {
	
	public String echo(String message);
	
	public String echoFromEJB2(String message) throws Exception;
	
}
