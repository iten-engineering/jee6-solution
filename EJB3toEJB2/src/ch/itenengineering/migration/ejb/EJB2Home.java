package ch.itenengineering.migration.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

public interface EJB2Home extends javax.ejb.EJBHome {

	EJB2Remote create() throws CreateException, RemoteException;

} 
