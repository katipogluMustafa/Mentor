package Controller.DatabaseController;


public class DatabaseQueryTimeOutException extends DatabaseQueryException {

    public DatabaseQueryTimeOutException(String msg) {
        super(msg);
    }
}
