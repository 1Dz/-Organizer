package exception;

/**
 * Created by user on 17.10.16.
 */
public class OrgException extends Exception {
    public OrgException(String message) {
        super(message);
    }

    public OrgException(String message, Throwable cause) {
        super(message, cause);
    }
}
