package br.com.frigelar.integracao.vtex.exception;

public class ModeloNotFoundException extends Exception {

	private static final long serialVersionUID = -5864726414664676797L;

	public ModeloNotFoundException() {
	    super();
	  }

	  public ModeloNotFoundException(String message, Throwable cause, boolean enableSuppression,
	      boolean writableStackTrace) {
	    super(message, cause, enableSuppression, writableStackTrace);
	  }

	  public ModeloNotFoundException(String message, Throwable cause) {
	    super(message, cause);
	  }

	  public ModeloNotFoundException(String message) {
	    super(message);
	  }

	  public ModeloNotFoundException(Throwable cause) {
	    super(cause);
	  }

}
