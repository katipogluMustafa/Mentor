package Controller.VideoCallController;

public class MissingVideoCallAttributeExpception extends VideoCallException {
    public MissingVideoCallAttributeExpception(String msg){
        super(msg);
    }
}
