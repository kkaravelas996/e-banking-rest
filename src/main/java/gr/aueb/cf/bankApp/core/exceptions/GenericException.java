package gr.aueb.cf.bankApp.core.exceptions;

import lombok.Getter;

@Getter
public class GenericException extends Exception{

    private final String code;

    public GenericException(String code,String message){
        super(message);
        this.code = code;
    }

}
