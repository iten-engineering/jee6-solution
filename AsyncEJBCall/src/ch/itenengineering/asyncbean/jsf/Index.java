package ch.itenengineering.asyncbean.jsf;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import ch.itenengineering.asyncbean.ejb.AsyncTestLocal;
import ch.itenengineering.asyncbean.ejb.MyAsyncTestException;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class Index implements Serializable {

	@EJB
	AsyncTestLocal test;

	String message = "My Message";

	String result;

	public Index() {
	}

	public void saveSync() {
		System.out.println("saveSync start");

		test.saveMsgSync(this.message);
		this.result = "saveSync done";

		System.out.println("saveSync end");
	}

	public void saveAsync() {
		System.out.println("saveAsync start");

		test.saveMsgAsync(this.message);
		this.result = "saveAsync done";

		System.out.println("saveAsync end");
	}

	public void saveAsyncFuture() {
		System.out.println("saveAsyncFuture start");

		Future<String> future = test.saveMsgAsyncFuture(this.message);
		System.out.println("saveAsyncFuture method call done");

		try {
			this.result = future.get();
		} catch (InterruptedException ie) {
			this.result = "saveAsyncFuture failed with InterruptedException "
					+ ie.toString();
		} catch (ExecutionException ex) {
			this.result = "saveAsyncFuture failed with ExecutionException "
					+ ex.toString();
		}

		System.out.println("saveAsyncFuture end");
	}

	public void saveAsyncException() {
		System.out.println("saveAsyncException start");

		try {
			Future<String> future = test.saveMsgAsyncException(this.message);
			System.out.println("saveAsyncException method call done");

			this.result = future.get();

		} catch (MyAsyncTestException te) {
			this.result = "saveAsyncFuture failed with InterruptedException "
					+ te.toString();
		} catch (InterruptedException ie) {
			this.result = "saveAsyncFuture failed with InterruptedException "
					+ ie.toString();
		} catch (ExecutionException ex) {
			this.result = "saveAsyncFuture failed with ExecutionException "
					+ ex.toString();
		}

		System.out.println("saveAsyncException end");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResult() {
		return result;
	}

} // end
