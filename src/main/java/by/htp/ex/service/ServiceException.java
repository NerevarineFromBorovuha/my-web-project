package by.htp.ex.service;

public class ServiceException extends Exception{

	public ServiceException(String e) {
		super(e);
	}
	
	public ServiceException(Exception e) {
		super(e);
	}

	public ServiceException() {
		super();
		
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ServiceException(Throwable cause) {
		super(cause);

	}

	
	
	
}
