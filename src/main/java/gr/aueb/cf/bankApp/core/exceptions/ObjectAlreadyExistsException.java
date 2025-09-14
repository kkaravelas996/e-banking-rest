package gr.aueb.cf.bankApp.core.exceptions;

import org.aspectj.bridge.IMessage;

public class ObjectAlreadyExistsException extends GenericException {
    private final static String DEFAULT_CODE = "AlredyExists";
    public ObjectAlreadyExistsException(String code,String message){
        super(code + DEFAULT_CODE , message);
    }
}
