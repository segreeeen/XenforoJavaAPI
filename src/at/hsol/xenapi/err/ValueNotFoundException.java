package at.hsol.xenapi.err;

import at.hsol.xenapi.constants.PostConstants;

/**
 * Custom exception to throw if a parser was not able to find the given
 * {@link PostConstants}.
 * 
 * @author thomassulzbacher
 *
 */
public class ValueNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4628601126447784098L;

	public ValueNotFoundException() {
		super();
	}

	public ValueNotFoundException(String message) {
		super(message);
	}

	public ValueNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
