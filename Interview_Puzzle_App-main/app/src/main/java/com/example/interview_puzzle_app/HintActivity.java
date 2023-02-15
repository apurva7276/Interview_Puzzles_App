package com.example.interview_puzzle_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.interview_puzzle_app.ui.logIn.SecondActivity;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class HintActivity extends YouTubeBaseActivity {

    Button playBtn;
    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    String videoLink = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        playBtn = findViewById(R.id.btnPlay);

        youTubePlayerView = findViewById(R.id.youtubePlyerView);
        Intent getvideoLink = getIntent();
        videoLink = getvideoLink.getStringExtra("videoLink");
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(videoLink);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                String errorMessage = String.format("There was an error while initializing the YouTube Player");
                Log.d("error", errorMessage);
                Toast.makeText(HintActivity.this,"Initialization failed",Toast.LENGTH_SHORT).show();
            }
        };

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                youTubePlayerView.initialize("AIzaSyB_o4l74EHbsNayklhanrRPXrnXylfXB7o",onInitializedListener);
            }
        });

    }
}