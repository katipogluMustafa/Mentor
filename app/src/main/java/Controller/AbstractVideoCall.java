package Controller;

import android.app.Activity;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.agora.rtc.Constants;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoCanvas;
import io.agora.rtc.video.VideoEncoderConfiguration;

public class AbstractVideoCall implements VideoCall{
    private String apiID;
    private Activity currentActivity;
    private RtcEngine rtcEngine;
    private Map<String,Object> sessionData;
    private VideoCallException exception;

    // Views for Camera
    private FrameLayout remoteVideoContainer;
    private FrameLayout localCameraContainer;
    private ImageView disabledRemoteCamera;

    private final IRtcEngineEventHandler rtcEventHandler = new IRtcEngineEventHandler() {
        // Channel is created
        @Override
        public void onFirstRemoteVideoDecoded(int uid, int width, int height, int elapsed) {
            currentActivity.runOnUiThread(()->setupRemoteVideoStream(uid));
        }

        // remote user has left channel
        @Override
        public void onUserOffline(int uid, int reason) {
            currentActivity.runOnUiThread(() -> onRemoteUserLeft());
        }

        // remote stream has been toggled
        @Override
        public void onUserMuteVideo(final int uid, final boolean toggle) {
            currentActivity.runOnUiThread(() -> onRemoteUserVideoToggle(uid, toggle));
        }

    };

    public AbstractVideoCall(Activity currentActivity, String apiID ){
        this.currentActivity = currentActivity;
        this.apiID = apiID;
        this.sessionData = new HashMap<>();

    }

    public void initSession() {
        try{
            rtcEngine = RtcEngine.create(currentActivity.getBaseContext(), apiID, rtcEventHandler);
        }catch (Exception e){
            e.printStackTrace();
        }
        configureSession();
    }

    private void configureSession() {
        rtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_COMMUNICATION);
        rtcEngine.enableVideo();
        rtcEngine.setVideoEncoderConfiguration(new VideoEncoderConfiguration(VideoEncoderConfiguration.VD_1920x1080,
                VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_30,
                VideoEncoderConfiguration.STANDARD_BITRATE,
                VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT));
    }

    private void setupLocalVideoFeed() {
        SurfaceView callerCameraSurface = RtcEngine.CreateRendererView(currentActivity.getBaseContext());
        callerCameraSurface.setZOrderMediaOverlay(true);
        localCameraContainer.addView(callerCameraSurface);
        rtcEngine.setupLocalVideo( new VideoCanvas(callerCameraSurface, VideoCanvas.RENDER_MODE_FIT, 0));        //  We leave the uid parameter blank so the SDK can handle creating a dynamic id for each user.
    }

    private void setupRemoteVideoStream(int uid){
        SurfaceView videoSurface = RtcEngine.CreateRendererView(currentActivity.getBaseContext());
        remoteVideoContainer.addView(videoSurface);
        rtcEngine.setupRemoteVideo( new VideoCanvas(videoSurface, VideoCanvas.RENDER_MODE_FIT,uid));
        rtcEngine.setRemoteSubscribeFallbackOption(Constants.STREAM_FALLBACK_OPTION_AUDIO_ONLY);
    }

    private void onRemoteUserVideoToggle(int uid, boolean toggle){
        SurfaceView videoSurface = (SurfaceView) remoteVideoContainer.getChildAt(0);
        videoSurface.setVisibility(toggle ? View.GONE : View.VISIBLE);

        // If disabledRemoteCamera view is set then add the icon to let the other user know remote video has been disabled
        if( disabledRemoteCamera != null)
            if(toggle){
                ImageView noCamera = disabledRemoteCamera;
                remoteVideoContainer.addView(noCamera);
            } else {
                ImageView noCamera = (ImageView) remoteVideoContainer.getChildAt(1);
                if(noCamera != null) {
                    remoteVideoContainer.removeView(noCamera);
                }
            }
    }

    private void onRemoteUserLeft(){
        remoteVideoContainer.removeAllViews();
    }

    public Map<String, Object> getSessionData() {
        sessionData.put("apiID", apiID);
        sessionData.put("rtcEngine", rtcEngine);
        sessionData.put("currentActivity", currentActivity);
        sessionData.put("exception", exception);
        return sessionData;
    }

    /**
     *
     * @param remoteVideoContainer Remote video container to put the remote camera view into
     * @param localCameraContainer Local video Camera container to put the user camera view into
     * @param disabledRemoteCamera Icon for letting user know the remote camera is disabled, Can be set as null
     * @throws MissingVideoCallAttributeExpception Throws if Ether remoteVideoContainer or localCameraContainer is null
     */
    public void setCameraViews(FrameLayout remoteVideoContainer, FrameLayout localCameraContainer, ImageView disabledRemoteCamera) throws MissingVideoCallAttributeExpception{
        if( remoteVideoContainer == null )
            throw new MissingVideoCallAttributeExpception("Null Reference to Remote Video Container...");
        else if( localCameraContainer == null )
            throw new MissingVideoCallAttributeExpception("Null Reference to Local Camera Container...");
        this.remoteVideoContainer = remoteVideoContainer;
        this.localCameraContainer = localCameraContainer;
        this.disabledRemoteCamera = disabledRemoteCamera;
    }

    public void setCameraViews(FrameLayout remoteVideoContainer, FrameLayout localCameraContainer) throws MissingVideoCallAttributeExpception{
        setCameraViews(remoteVideoContainer,localCameraContainer,null);
    }

    /**
     * Call This method inside onClickListener of the <bold>join</bold> button
     * Update the UI after calling this function, like making audio,video toggle buttons visible or leave conversation button etc.
     */
    public boolean joinChannel(String channelName){
        return createChannel(channelName,null);
    }

    //TODO: Process the Error Codes if error happens during channel creating or connection
    public boolean createChannel(String channelName, String extraChannelInfo){
        int resultCode = rtcEngine.joinChannel(null, channelName, extraChannelInfo,0);
        switch (resultCode){
            case 0:
                return true;
            case Constants.ERR_INVALID_ARGUMENT:
                exception = new VideoCallInvalidArgumentException("Create Channel: Invalid Channel Name or Channel Info");
                return false;
            case Constants.ERR_NOT_READY:
                exception = new VideoCallChannelNotReadyException("Create Channel: Channel Not Ready");
                return false;
            case Constants.ERR_REFUSED:
                exception = new VideoCallConnectionRefusedException("Create Channel: Connection Refused");
                return false;
        }

        setupLocalVideoFeed();
        return true;
    }

    /**
     * Call This method inside onClickListener of the <bold>leave</bold> button
     * Update the UI after calling this function, like making audio,video toggle buttons invisible or go to another activity etc.
     */
    public boolean leaveChannel(){
        return rtcEngine.leaveChannel() == 0;
    }

    /**
     * Call this inside onAudioMuteClick Listener
     * @param isMuted , if true mutes
     */
    public boolean muteLocalAudio(boolean isMuted){
        return rtcEngine.muteLocalAudioStream(isMuted) == 0;
    }

    /**
     * Call this inside onVideoMuteClicked
     * @param isMuted
     */
    public boolean muteLocalVideo(boolean isMuted){
        if( rtcEngine.muteLocalVideoStream(isMuted) < 0)
            return false;

        localCameraContainer.setVisibility(isMuted ? View.GONE : View.VISIBLE);
        SurfaceView videoSurface = (SurfaceView) localCameraContainer.getChildAt(0);
        videoSurface.setZOrderMediaOverlay( !isMuted );
        videoSurface.setVisibility(isMuted ? View.GONE : View.VISIBLE);
        return true;
    }

    public boolean muteRemoteVideo(int uid, boolean isMuted ){
        if( rtcEngine.muteRemoteVideoStream(uid, isMuted) < 0)
            return false;

        remoteVideoContainer.setVisibility( isMuted ? View.GONE : View.VISIBLE );
        SurfaceView videoSurface = (SurfaceView) remoteVideoContainer.getChildAt(0);
        videoSurface.setZOrderMediaOverlay(!isMuted);
        videoSurface.setVisibility(isMuted ? View.GONE : View.VISIBLE);
        return true;
    }

    public boolean muteRemoteAudio(int uid, boolean isMuted ){
        return rtcEngine.muteRemoteVideoStream(uid, isMuted) == 0;
    }

    public boolean switchCamera(){
        return rtcEngine.switchCamera() == 0;
    }

    public VideoCallException getException() {
        return exception;
    }
}
