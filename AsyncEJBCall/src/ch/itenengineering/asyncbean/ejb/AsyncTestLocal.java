package ch.itenengineering.asyncbean.ejb;

import java.util.concurrent.Future;
import javax.ejb.Asynchronous;
import javax.ejb.Local;
import javax.ejb.Remote;

@Local
public interface AsyncTestLocal {

	public void saveMsgSync(String msg);

	public void saveMsgAsync(String msg);

	public Future<String> saveMsgAsyncFuture(String msg);

	public Future<String> saveMsgAsyncException(String msg) throws MyAsyncTestException;

}
