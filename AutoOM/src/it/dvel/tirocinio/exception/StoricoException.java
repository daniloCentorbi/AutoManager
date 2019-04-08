package it.dvel.tirocinio.exception;

public class StoricoException extends Exception {
	private static final long serialVersionUID = -1093636788199362279L;

	public StoricoException() {
		super();
	}

	public StoricoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public StoricoException(String arg0) {
		super(arg0);
	}

	public StoricoException(Throwable arg0) {
		super(arg0);
	}
}