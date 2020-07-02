package ch.itenengineering.jms.p2p;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.NamingException;

public class Receiver implements javax.jms.MessageListener {

	//
	// fields
	//
	Connection connection;

	Session session;

	Queue queue;

	MessageConsumer messageConsumer;

	// ---------------------------------------------------------------------
	// methods
	// ---------------------------------------------------------------------

	public void start() throws NamingException, JMSException {

		Context ctx = Sender.getInitialContext();

		ConnectionFactory cf = (ConnectionFactory) ctx
				.lookup(Sender.JNDI_NAME_CONNECTION_FACTORY);

		connection = cf.createConnection();

		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		queue = (Queue) ctx.lookup(Sender.JNDI_NAME_QUEUE);

		messageConsumer = session.createConsumer(queue);

		messageConsumer.setMessageListener(this);

		connection.start();
	}

	/**
	 * @see javax.jms.MessageListener()
	 */
	public void onMessage(Message message) {
		try {

			TextMessage tm = (TextMessage) message;

			System.out.println("> message <" + tm.getText() + "> received");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		try {
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			// ignore
		}
	}

	// ---------------------------------------------------------------------
	// main
	// ---------------------------------------------------------------------

	public static void main(String[] args) {

		Receiver receiver = new Receiver();

		try {

			receiver.start();

			System.out
					.println("receiver starts listening (press <RETURN> to stop)...");
			(new BufferedReader(new InputStreamReader(System.in))).readLine();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			receiver.stop();
			System.out.println("receiver stopped!");
		}
		System.exit(0);
	}

} // end of class
