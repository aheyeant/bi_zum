package du1.exceptions;

public class NoUniqueInsertIHeepException extends Exception {
    public NoUniqueInsertIHeepException(String message) {
        this.message = message;
    }

    private String message;

    @Override
    public String getMessage() {
        return "Try insert in IHeep no unique item in method \"" + message + "\"";
    }
}
