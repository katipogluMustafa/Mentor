package Controller.VideoCallController;


public interface VideoCall {
    // Configuration Methods
    void initSession();

    // Channel Methods
    boolean joinChannel(String channelName);
    boolean createChannel(String channelName, String extraChannelInfo);
    boolean leaveChannel();

    // Actions
    boolean muteLocalAudio(boolean isMuted);
    boolean muteLocalVideo(boolean isMuted);
    boolean muteRemoteVideo(int uid, boolean isMuted );
    boolean muteRemoteAudio(int uid, boolean isMuted );
    boolean switchCamera();

}
