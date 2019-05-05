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

    public <T> void uploadData(DatabaseReference ref, String key, T value){
        ref.child(key).setValue(value);
    }

    /*
    public void uploadData(DatabaseReference ref, String key, String value){
        ref.child(key).setValue(value);
    }

    public void uploadData(DatabaseReference ref, String key, double value){
        ref.child(key).setValue(value);
    }

    public void uploadData(DatabaseReference ref, String key, int value){
        ref.child(key).setValue(value);
    }

    public void uploadData(DatabaseReference ref, String key, boolean value){
        ref.child(key).setValue(value);
    }

    public void uploadData(DatabaseReference ref, String key, List<String> value){
        ref.child(key).setValue(value);
    }

    public void uploadData(DatabaseReference ref, String key, Gender value){
        ref.child(key).setValue(value);
    }

    public void uploadData(DatabaseReference ref, String key, Blood value){
        ref.child(key).setValue(value);
    }
    */

    public FirebaseDatabase getDatabase() {
        return database;
    }
    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

}
