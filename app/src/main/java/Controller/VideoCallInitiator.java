package Controller;

import android.Manifest;
import android.app.Activity;

import java.util.Date;

import model.Appointment;
import model.User;

public class VideoCallInitiator extends AbstractVideoCall {
    private Appointment appointment;
    private User caller;
    private User callReceiver;
    private String channelName;

    private Date initCallTimeStamp;
    private boolean isCallInitiated;

    private Date endCallTimeStamp;
    private boolean isCallEnded;

    private Date respondTimeStamp;
    private boolean isCallAccepted;


    public VideoCallInitiator(Activity currentActivity, String apiID, Appointment appointment) {
        super(currentActivity, apiID);
        this.appointment = appointment;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public boolean call(User caller) throws VideoCallException{
        if( appointment == null)
            throw new MissingVideoCallAttributeExpception("Video Call Null Appointment : Appointment is not set");
        else if( caller == null)
            throw new MissingVideoCallAttributeExpception("Video Call Null Caller : Caller is not set");

        initSession();
        setCallReceiver();

        if( callReceiver == null)
            throw new MissingVideoCallAttributeExpception("Video Call Null Call Receiver : Call Receiver is not set");

        if ( !joinChannel(caller.getFirebaseUser().getUid()) )          // Channel Name is the UID of the caller
            throw getException();

        initCallTimeStamp = new Date();
        isCallInitiated = true;

        return true;
    }

    public boolean endTheCall(){
        if( !isCallInitiated )
            return false;

        if( !leaveChannel() )
            return false;

        endCallTimeStamp = new Date();
        isCallEnded = true;

        return true;
    }

    /**
     * Sets The call Receiver
     * @return Call Receiver may be null
     */
    private  void setCallReceiver(){
        User callReceiver = null;
        if( appointment != null )
            if( appointment.getConsumerUser().equals(caller) )
                callReceiver = appointment.getHostUser();
            else if( appointment.getHostUser().equals(caller) )
                callReceiver = appointment.getConsumerUser();

        this.callReceiver = callReceiver;
    }


    public boolean sendInvitation(){
        //TODO: Send Invitation to
        return true;
    }
}
