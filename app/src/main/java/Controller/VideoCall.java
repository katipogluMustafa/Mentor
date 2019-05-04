package Controller;

import android.app.Activity;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.agora.rtc.Constants;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoCanvas;
import io.agora.rtc.video.VideoEncoderConfiguration;

public class VideoCall {
    private String apiID;
    private Activity currentActivity;
    private RtcEngine rtcEngine;
    private Map<String,Object> sessionData;
    private List<Exception> exceptions;
    private View onJoinChannelView;

    // Containers for local and remote camera
    private int remoteVideoContainerFrameLayoutID;
    private int localCameraContainerFrameLayoutID;

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

    public VideoCall(Activity currentActivity, String apiID ){
        this.currentActivity = currentActivity;
        this.apiID = apiID;
        this.sessionData = new HashMap<>();
        initSession();
    }

    private void initSession() {
        try{
            rtcEngine = RtcEngine.create(currentActivity.getBaseContext(), apiID, rtcEventHandler);
        }catch (Exception e){
            e.printStackTrace();
            exceptions.add(e);
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

    /**
     * Call This method inside onClickListener of the join button
     * Update the UI after calling this function, like making audio,video toggle buttons visible or leave conversation button etc.
     * @throws MissingVideoCallAttributeExpception Don't forget to set onJoinView, otherwise you get this exception
     */
    public void onJoinChannel(int localCameraContainerFrameLayoutID, int remoteVideoContainerFrameLayoutID) throws MissingVideoCallAttributeExpception{
        if( onJoinChannelView == null )
            throw new MissingVideoCallAttributeExpception("You need to set onJoinChannelView...");
        this.remoteVideoContainerFrameLayoutID = remoteVideoContainerFrameLayoutID;
        this.localCameraContainerFrameLayoutID = localCameraContainerFrameLayoutID;
        setupLocalVideoFeed();
    }

    private void setupLocalVideoFeed() {
        FrameLayout callerCameraContainer = currentActivity.findViewById(localCameraContainerFrameLayoutID);
        SurfaceView callerCameraSurface = RtcEngine.CreateRendererView(currentActivity.getBaseContext());
        callerCameraSurface.setZOrderMediaOverlay(true);
        callerCameraContainer.addView(callerCameraSurface);
        rtcEngine.setupLocalVideo( new VideoCanvas(callerCameraSurface, VideoCanvas.RENDER_MODE_FIT, 0));        //  We leave the uid parameter blank so the SDK can handle creating a dynamic id for each user.
    }

    private void setupRemoteVideoStream(int uid){
        FrameLayout remoteVideoContainer = currentActivity.findViewById(remoteVideoContainerFrameLayoutID);
        SurfaceView videoSurface = RtcEngine.CreateRendererView(currentActivity.getBaseContext());
        remoteVideoContainer.addView(remoteVideoContainer);
        rtcEngine.setupRemoteVideo( new VideoCanvas(videoSurface, VideoCanvas.RENDER_MODE_FIT,uid));
        rtcEngine.setRemoteSubscribeFallbackOption(Constants.STREAM_FALLBACK_OPTION_AUDIO_ONLY);
    }

    /**
     * You need to set the otherwise join channel will not be working properly
     * @param onJoinChannelView View of the onClickListener of the join channel button
     */
    public void setOnJoinChannelView(View onJoinChannelView) {
        this.onJoinChannelView = onJoinChannelView;
    }

    public Map<String, Object> getSessionData() {
        sessionData.put("apiID", apiID);
        sessionData.put("rtcEngine", rtcEngine);
        sessionData.put("currentActivity", currentActivity);
        sessionData.put("exceptions", exceptions);          // Put List of exceptions into Map
        return sessionData;
    }
}
