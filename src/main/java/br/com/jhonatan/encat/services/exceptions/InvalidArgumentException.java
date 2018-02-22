package br.com.jhonatan.encat.services.exceptions;

public class InvalidArgumentException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidArgumentException(String msg) {
		super(msg);
	}
	
	public InvalidArgumentException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
