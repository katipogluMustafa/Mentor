package Controller.VideoCallController;

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

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
