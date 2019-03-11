package org.mule.modules.googleanalytics.internal.exception;

public class GoogleAnalyticsException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	/**
     * Constructs a new exception with the specified detail message.
     *
     * @param msg the detail message.
     */
    public GoogleAnalyticsException(String msg) {
        super(msg);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param msg   the detail message.
     * @param cause the cause.
     */
    public GoogleAnalyticsException(String msg, Throwable cause) {
        super(msg, cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail message.
     *
     * @param cause the cause
     */
    public GoogleAnalyticsException(Throwable cause) {
        super(cause);
    }

    public GoogleAnalyticsError getErrorCode() {
        return GoogleAnalyticsError.UNKNOWN;
    	//return GoogleAnalyticsError
    }

}
