package Controller;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class DatabaseConnector {
    private FirebaseFirestore db;
    private FirebaseUser currentUser;

    public DatabaseConnector(FirebaseFirestore db, FirebaseUser currentUser){
        this.db = db;
        this.currentUser = currentUser;
    }

    public Map<String, Object> downloadUserDetails(){
        String uid = currentUser.getUid();
        if( currentUser == null || uid == null)
            return null;

        DocumentReference docRef = db.collection("user_packs").document(uid);
        Task<DocumentSnapshot> documentSnapshotTask =  docRef.get();

        if( documentSnapshotTask.isSuccessful() ){
            DocumentSnapshot document = documentSnapshotTask.getResult();
            if( document.exists() )
                return document.getData();
        }

        Log.d("DEBUG", "get failed with ", documentSnapshotTask.getException());
        return null;
    }

    public boolean uploadUserDetails(Map<String,Object> user_details){
        String uid = currentUser.getUid();
        if( currentUser == null || uid == null){
            Log.d("DEBUG", "Error while uploading user details: User not found");
        }
        CollectionReference user_packs = db.collection("user_packs");
        return user_packs.document(uid).set(user_details).isSuccessful();
    }

    public FirebaseFirestore getDb() {
        return db;
    }

    public void setDb(FirebaseFirestore db) {
        this.db = db;
    }

    public FirebaseUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(FirebaseUser currentUser) {
        this.currentUser = currentUser;
    }
}
