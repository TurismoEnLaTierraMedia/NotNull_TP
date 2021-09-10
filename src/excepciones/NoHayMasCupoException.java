package excepciones;

public class NoHayMasCupoException extends Exception {

	public NoHayMasCupoException() {
		super();
	}

	public NoHayMasCupoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoHayMasCupoException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoHayMasCupoException(String message) {
		super(message);
	}

	public NoHayMasCupoException(Throwable cause) {
		super(cause);
	}

}
