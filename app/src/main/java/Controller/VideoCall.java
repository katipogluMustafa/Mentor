package Controller;

import android.widget.FrameLayout;

public interface VideoCall {
    // Configuration Methods
    void initSession();
    void setCameraViews(FrameLayout remoteVideoContainer, FrameLayout localCameraContainer) throws MissingVideoCallAttributeExpception;

    // Channel Methods
    boolean joinChannel(String channelName);
    boolean createChannel(String channelName, String extraChannelInfo);
    boolean leaveChannel();

    // Actions
    boolean muteLocalAudio(boolean isMuted);
    boolean muteLocalVideo(boolean isMuted);
    boolean muteRemoteVideo(int uid, boolean isMuted )

}
