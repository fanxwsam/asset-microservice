package com.asset.obs.common.exception;

/**
 * This exception will handle query param verification.
 * when input param is invalid, this exception will be thrown
 */

public class InvalidQueryInputException extends SystemException {
    private static final long serialVersionUID = -3678309876986786971L;

    public InvalidQueryInputException() {
        super();
    }

    public InvalidQueryInputException(final String message, final Integer errorCode) {
        super(message, errorCode);
    }
}
