package com.example.mentor;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import Controller.VideoCall;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;


public class CallingActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView doctorNameTextView;
    private CircleImageView doctorCircularImageView;
    private ImageButton cancelImageButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);

        setUp();

    }

    private void setUp() {
        doctorNameTextView = (TextView) findViewById(R.id.callinActivity_doctorName);
        doctorCircularImageView = (CircleImageView) findViewById(R.id.callingActivity_doctorCircularImage);
        cancelImageButton = (ImageButton) findViewById(R.id.callingActivity_cancelImageButton);

        cancelImageButton.setOnClickListener(this);
        createVideoChannel();
        String s = getString(R.string.private_agora_id);
    }

    private void createVideoChannel() {
        VideoCall videoCall = new VideoCall(this, getString(R.string.private_agora_id));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.callingActivity_cancelImageButton:
                onBackPressed();
                finish();
                break;

        }
    }
}
