package du1.exceptions;

import zum_du1.Node;

public class NotSupportedMethodException extends Exception {
    public NotSupportedMethodException(String message){
        this.message = message;
    }

    private String message;

    @Override
    public String getMessage() {
        return "not supported method \"" + message + "\"";
    }
}
