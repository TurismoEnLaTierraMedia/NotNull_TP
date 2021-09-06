package clases;

public class NoEsMismoTipoException extends Exception {

	public NoEsMismoTipoException() {
		super();
	}

	public NoEsMismoTipoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoEsMismoTipoException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoEsMismoTipoException(String message) {
		super(message);
	}

	public NoEsMismoTipoException(Throwable cause) {
		super(cause);
	}

}
