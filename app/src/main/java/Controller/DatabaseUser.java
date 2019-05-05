package Controller;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import model.Blood;
import model.Gender;
import model.User;

public class DatabaseUser {
/*
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private DatabaseReference userReference;

    private User user;
    private FirebaseUser firebaseUser;

    public DatabaseUser(@NonNull FirebaseUser firebaseUser, User user){
        this.firebaseUser = firebaseUser;
        if( user != null)
            this.user = user;
        else
            createDatabaseUser();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        userReference = databaseReference.child("users").child(user.getUid());
    }

    private void createDatabaseUser(){
        this.user = new User();
    }

    private Map<String, Object> getUserDetails(){
        Map<String,Object> content;
        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                content = (HashMap<String,Object>)dataSnapshot.getValue();
                setUserDetails(content);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return content;
    }

    private void setUserDetails(Map<String, Object> content){
        for(Map.Entry<String,Object> entries : content.entrySet() )
            switch (entries.getKey()){
                case "uid":
                    user.setUid( firebaseUser.getUid() );
                    break;
                case "isSpecialUser":
                    user.setSpecialUser( (Boolean)entries.getValue() );
                case "prioritized_name":
                    user.setPrioritizedName((String)entries.getValue());
                    break;
                case "balance":
                    user.setBalance( (Double)entries.getValue() );
                    break;
                case "age":
                    user.setAge( (Integer)entries.getValue());
                    break;
                case "blood":
                    user.setBlood( Blood.bloodFactory( (Integer)entries.getValue() ) );
                    break;
                case "gender":
                    user.setGender( Gender.genderFactory( (Integer)entries.getValue() ) );
                    break;
                case "name":
                    user.setName((String)entries.getValue());
                    break;
                case "surname":
                    user.setSurname((String)entries.getValue());
                    break;




            }
    }

    public User getUser(){
        return user;
    }
*/
}

