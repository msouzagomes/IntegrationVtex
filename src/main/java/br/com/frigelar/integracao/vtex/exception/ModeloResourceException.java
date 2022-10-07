package br.com.frigelar.integracao.vtex.exception;

public class ModeloResourceException extends Exception {

	private static final long serialVersionUID = -7307786686718761787L;

	public ModeloResourceException() {
		super();
	}

	public ModeloResourceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ModeloResourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ModeloResourceException(String message) {
		super(message);
	}

	public ModeloResourceException(Throwable cause) {
		super(cause);
	}

}
