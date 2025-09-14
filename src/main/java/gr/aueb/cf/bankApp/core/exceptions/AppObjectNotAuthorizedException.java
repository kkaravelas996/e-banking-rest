package gr.aueb.cf.bankApp.core.exceptions;

public class AppObjectNotAuthorizedException extends GenericException {

    private static final String DEFAULT_CODE = "NotAuthorized";

    public AppObjectNotAuthorizedException(String code, String message) {
        super(code + DEFAULT_CODE, message);
    }
}
