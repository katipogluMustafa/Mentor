package Controller.DatabaseController;

public class MissingDatabaseQueryException extends DatabaseQueryException {
    public MissingDatabaseQueryException(String msg) {
        super(msg);
    }
}
