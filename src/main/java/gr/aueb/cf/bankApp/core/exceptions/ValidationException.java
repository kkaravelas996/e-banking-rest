package gr.aueb.cf.bankApp.core.exceptions;

import org.springframework.validation.BindingResult;

public class ValidationException extends Exception{

    private final BindingResult bindingResult;

    public ValidationException(BindingResult bindingResult){
        super("Validation failed");
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult(){
        return bindingResult;
    }
}
