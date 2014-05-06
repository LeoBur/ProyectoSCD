package war.webapp.service;

public class MedicionExistsException extends Exception {
	
	private static final long serialVersionUID = 3697944066144708775L;

	public MedicionExistsException(final String message) {
        super(message);
    }
}
