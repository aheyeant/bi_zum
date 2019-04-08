package du1.exceptions;

public class IMapNotFoundException extends Exception {
    public IMapNotFoundException(String message) {
        this.message = message;
    }

    private String message;

    @Override
    public String getMessage() {
        return "IMap not found in function \"" + message + "\"";
    }
}
