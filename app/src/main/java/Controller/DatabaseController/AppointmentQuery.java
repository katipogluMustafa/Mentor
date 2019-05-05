package Controller.DatabaseController;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import model.Appointment;


public class AppointmentQuery extends FirebaseDatabaseQuery {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference().child("appointments");
    private DatabaseReference currentAppointmentReference;
    private Exception exception;
    private String appointmentUID;

    public AppointmentQuery(@NonNull String appointmentUID){
        this.appointmentUID = appointmentUID;
         currentAppointmentReference= databaseReference.child(appointmentUID);
    }

    public static String appointmentFactory(Appointment appointment){
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("appointments");
        String uid = ref.push().getKey();                                // Create appointment and get UID
        Map<String, Object> appointmentDetails = appointment.toMap();

        ref.child(uid).setValue(appointmentDetails);

        return uid;
    }

    public <T> void uploadAppointmentData(@NonNull String key, T value){
        uploadData(currentAppointmentReference,key,value);
    }

    public Exception getException() {
        return exception;
    }
}
