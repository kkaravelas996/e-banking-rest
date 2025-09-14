package gr.aueb.cf.bankApp.core.exceptions;

public class InvalidArgumentException extends GenericException{

    private final static String DEFAULT_CODE = "InvalidArgument";

    public InvalidArgumentException(String code,String message){
        super(code + DEFAULT_CODE, message);
    }
}
