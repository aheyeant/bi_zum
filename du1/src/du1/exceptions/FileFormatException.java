package du1.exceptions;

public class FileFormatException extends Exception {
    public FileFormatException(String message){
        this.message = message;
    }

    private String message;

    @Override
    public String getMessage() {
        return "Input file format exception in function \"" + message + "\"";
    }
}
