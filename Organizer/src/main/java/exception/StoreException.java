package exception;

/**
 * Created by user on 29.09.16.
 */
public class StoreException extends Exception{
    public StoreException(String message) {
        super(message);
    }

    public StoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
