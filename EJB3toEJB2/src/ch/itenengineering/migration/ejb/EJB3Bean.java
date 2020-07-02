package ch.itenengineering.migration.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Hinweise:
 * 
 * <ul>
 * <li>Glassfish 3: <br />
 * Das direkte injizieren der Remote Interface beim Glassfish Server Version 3
 * fuehrt zu einem JNDI Lookup Failure, da kein JNDI Name fuer das Remote
 * Interface registirert ist.</li>
 * 
 * <li>JBoss 4.2.2.GA <br />
 * Das direkte injizieren des Remote Interface funktioniert einwandfrei.</li>
 * 
 * <li>JBoss 5.0.0.GA <br />
 * Das direkte injizieren des Remote Interface fuehrt zu einem Fehler, da das
 * Remote Interface nicht korrekt im globalen JNDI Namespace eingetragen wird.</li>
 * 
 * <li>JBoss 7.1.1.AS.Final <br />
 * Das direkte injizieren des Remote Interface funktioniert einwandfrei.</li>
 * </ul>
 */
@Stateless
public class EJB3Bean implements EJB3Remote {

	@EJB
	EJB2Remote ejb2;

	// @EJB
	// EJB2Home ejb2Home;
	//
	// EJB2Remote ejb2;

	// @SuppressWarnings("unused")
	// @PostConstruct
	// private void init() {
	// try {
	// ejb2 = ejb2Home.create();
	// } catch (Exception e) {
	// throw new EJBException(e);
	// }
	// }

	public String echoFromEJB2(String message) throws Exception {

		return ejb2.echo(message);
	}

	public String echo(String message) {

		return "echo from EJB3Bean - message <" + message + "> received!";
	}

} // end class
