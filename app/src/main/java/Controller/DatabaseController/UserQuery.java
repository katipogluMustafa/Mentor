package Controller.DatabaseController;

import android.os.Handler;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import androidx.annotation.NonNull;
import model.Blood;
import model.Gender;
import model.User;

@SuppressWarnings("unchecked")
public class UserQuery extends FirebaseDatabaseQuery {
    private DatabaseReference userDatabaseReference = getDatabaseReference().child("users");
    private String currentUserID;
    private DatabaseReference currentUserDatabaseReference;

    private boolean isSearchDone;
    private boolean isUploadSuccessful;
    private boolean isRemoved;
    private User lastFoundUser;                                                  // last found lastFoundUser
    private Exception exception;                                                 // Last Exception we got
    private boolean isExceptionUpdated;

    public UserQuery(String currentUserID){
        this.currentUserID = currentUserID;
        currentUserDatabaseReference = userDatabaseReference.child(currentUserID);
    }

    /**
     * Removes the lastFoundUser with specified uid
     * @param uid the uid of the lastFoundUser that we want to delete
     * @return true if success, otherwise false take the exception
     */
    public boolean removeUser(String uid){
        if( uid == null){
            exception = new MissingDatabaseQueryException("Trying to remove null lastFoundUser...");
            return false;
        }

        isRemoved = false;
        userDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for( DataSnapshot snapshot : dataSnapshot.getChildren() )
                    if( Objects.equals( ((User)snapshot.getValue()).getUid(), uid) )
                        snapshot.getRef().removeValue().addOnCompleteListener( task -> {
                            if( task.isSuccessful() )
                                isRemoved = true;
                            else{
                                isRemoved = false;
                                exception = task.getException();
                            }
                        });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        return isRemoved;
    }

    /**
     * Uploads a lastFoundUser into database
     * @param user the User we want to upload into database
     * @return if the result is false, get the exception
     */
    public boolean uploadUser(User user){
        if( user == null) {
           exception =  new MissingDatabaseQueryException("Trying to upload null lastFoundUser...");
            return false;
        }else if(user.getUid() == null){
            exception = new MissingDatabaseQueryException("Unspecified User unique id...");
            return false;
        }

        isUploadSuccessful = false;
        Map<String,Object> userData = user.getUserData();

        userDatabaseReference.child(user.getUid()).setValue(userData).addOnCompleteListener( task -> {
            if( task.isSuccessful() )
                isUploadSuccessful = true;
            else {
                isUploadSuccessful = false;
                exception = task.getException();
            }
        });

        return isUploadSuccessful;
    }

    public <T> void uploadUserData(String key, T value){
        uploadData(currentUserDatabaseReference,key,value);
    }

    public Exception getException() {
        return exception;
    }



    /**
     * Search lastFoundUser from the database
     * @param uid UID of the lastFoundUser
     * @return User that we found
     * @throws DatabaseQueryException on database search time out
     */
    /*
    public User getUser(String uid) throws DatabaseQueryException{              //TODO: Unit Test
        lastFoundUser = null;                                                   // first set the lastFoundUser ass null
        int timeLimit = 3000;                                                   // set the time limit
        isSearchDone = false;                                                   // flag for checking is search done
        while( !isSearchDone  && timeLimit > 0){                                // Wait while we search
            (new Handler()).postDelayed( ()->{}, 200);
            timeLimit -= 200;
        }

        if( !isSearchDone )
            throw new DatabaseQueryTimeOutException("Database Time Out While Searching User...");

        return lastFoundUser;
    }

    public User getUser(){
        return getUser(currentUserID);
    }

    public User getUser(String uid){
        userDatabaseReference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lastFoundUser = User.createUser((HashMap<String,Object>)dataSnapshot.getValue());
                if( lastFoundUser == null)
                    exception = new DatabaseQueryNotFoundException("User not found...");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
        return lastFoundUser;
    }
*/
    /**
     * Helper method for getUser
     * @param uid the uid of the lastFoundUser we search
     */
/*
    private void searchUser(String uid){
        userDatabaseReference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if( dataSnapshot.exists() )
                    lastFoundUser = User.createUser((HashMap<String,Object>)dataSnapshot.getValue());
                else
                    exception = new DatabaseQueryNotFoundException("User not found...");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }
        public void uploadUserData(String key, String value){
        uploadData(currentUserDatabaseReference,key,value);
    }

    public void uploadUserData(String key, double value){
        uploadData(currentUserDatabaseReference,key,value);
    }

    public void uploadUserData(String key, int value){
        uploadData(currentUserDatabaseReference,key,value);
    }

    public void uploadUserData(String key, boolean value){
        uploadData(currentUserDatabaseReference,key,value);
    }

    public void uploadUserData(String key, List<String> value){
        uploadData(currentUserDatabaseReference,key,value);
    }

    public void uploadUserData(String key, Gender value){
        uploadData(currentUserDatabaseReference,key,value);
    }

    public void uploadUserData(String key, Blood value){
        uploadData(currentUserDatabaseReference,key,value);
    }
*/

}
