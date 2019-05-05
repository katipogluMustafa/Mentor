package Controller;

import android.app.Activity;

import model.Appointment;
import model.User;


public class VideoCallReceiver extends AbstractVideoCall {
    private Appointment appointment;
    private User receiver;
    private String channelName;

    public VideoCallReceiver(Activity currentActivity, String apiID) {
        super(currentActivity, apiID);
    }


}
