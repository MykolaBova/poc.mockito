package poc.exception;

public class IntegrationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public IntegrationException(String errorMessage){
		super(errorMessage);
	}

	public IntegrationException(Exception ex) {
		super(ex);
	}

}
