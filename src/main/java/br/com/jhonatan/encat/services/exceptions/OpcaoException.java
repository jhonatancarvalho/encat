package br.com.jhonatan.encat.services.exceptions;

public class OpcaoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public OpcaoException(String msg) {
		super(msg);
	}
	
	public OpcaoException(String msg, Throwable cause) {
		super(msg, cause);
	}
}