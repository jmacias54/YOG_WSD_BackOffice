/**
 * @author Jesus Armando Macias Benitez
 */
package mx.com.amx.wsd.yog.backoffice.dao.exception;

/**
 * @author  Jesus Armando Macias Benitez
 *
 */
public class DAOException extends Exception{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new DAOException.
	 */
	public DAOException() {
		super();
	}

	/**
	 * Instantiates a new DAOException.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new DAOException.
	 *
	 * @param message the message
	 */
	public DAOException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new DAOException.
	 *
	 * @param cause the cause
	 */
	public DAOException(Throwable cause) {
		super(cause);
	}

}
