package it.dvel.tirocinio.exception;

public class ModelloException extends Exception {
	private static final long serialVersionUID = 2529368417821496345L;

	public ModelloException() {
		super();
	}

	public ModelloException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ModelloException(String arg0) {
		super(arg0);
	}

	public ModelloException(Throwable arg0) {
		super(arg0);
	}
}