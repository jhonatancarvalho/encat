package br.com.jhonatan.encat.services.exceptions;

public class EnqueteException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EnqueteException(String msg) {
		super(msg);
	}
	
	public EnqueteException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
