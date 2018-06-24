package advance.job.thread;

import java.sql.SQLException;

public class Mocker<T extends Exception> {
	private void pleaseThrow(final Exception tException) throws T {
		throw (T) tException;
	}

	public static void main(String[] args) {
		new Mocker<RuntimeException>().pleaseThrow(new SQLException());
	}
}
