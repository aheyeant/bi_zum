package du1.exceptions;

import java.util.Scanner;

public class IStepNullException extends Exception {
    public IStepNullException(String message) {
        this.message = message;
    }

    private String message;

    @Override
    public String getMessage() {
        return "try read IStep info from NULL pointer. In function \"" + message + "\"";
    }
}
