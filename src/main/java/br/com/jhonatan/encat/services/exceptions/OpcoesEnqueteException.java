package br.com.jhonatan.encat.services.exceptions;

public class OpcoesEnqueteException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public OpcoesEnqueteException(String msg) {
		super(msg);
	}
	
	public OpcoesEnqueteException(String msg, Throwable cause) {
		super(msg, cause);
	}
}