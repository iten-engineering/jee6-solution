package ch.itenengineering.migration.ejb;

import java.rmi.RemoteException;

public interface EJB2Remote extends javax.ejb.EJBObject {

	public String echo(String message) throws RemoteException;

}

