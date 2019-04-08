package du1.exceptions;

public class NoPathException extends Exception {
    public NoPathException(String message) {
        this.message = message;
    }

    private String message;

    @Override
    public String getMessage() {
        return "No way in algorithm \"" + message + "\"";
    }
}
