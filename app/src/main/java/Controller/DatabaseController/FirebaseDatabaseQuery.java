package Controller.DatabaseController;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public abstract class FirebaseDatabaseQuery {
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference databaseReference = database.getReference();

    public FirebaseDatabaseQuery(){

    }

    public FirebaseDatabase getDatabase() {
        return database;
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }
}
