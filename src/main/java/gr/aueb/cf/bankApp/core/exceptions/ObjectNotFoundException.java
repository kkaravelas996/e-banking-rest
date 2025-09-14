package gr.aueb.cf.bankApp.core.exceptions;

public class ObjectNotFoundException extends GenericException{
    private final static String DEFAULT_CODE = "NotFound";

    public ObjectNotFoundException(String code,String message){
        super(code + DEFAULT_CODE, message);
    }
}
