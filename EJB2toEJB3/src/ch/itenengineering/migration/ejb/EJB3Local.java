package ch.itenengineering.migration.ejb;

import javax.ejb.Local;

@Local
public interface EJB3Local {

	public String echo(String message);

}
