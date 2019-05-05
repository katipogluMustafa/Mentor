package Controller.DatabaseController;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import model.Blood;
import model.Gender;

public abstract class FirebaseDatabaseQuery {
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference databaseReference = database.getReference();

    public FirebaseDatabaseQuery(){

    }

    public void uploadData(DatabaseReference ref, String key, String value){

    }

    public void uploadData(DatabaseReference ref, String key, double value){

    }

    public void uploadData(DatabaseReference ref, String key, int value){

    }

    public void uploadData(DatabaseReference ref, String key, boolean value){

    }

    public void uploadData(DatabaseReference ref, String key, List<String> value){

    }

    public void uploadData(DatabaseReference ref, String key, Gender value){

    }

    public void uploadData(DatabaseReference ref, String key, Blood value){

    }

    public FirebaseDatabase getDatabase() {
        return database;
    }
    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

}
