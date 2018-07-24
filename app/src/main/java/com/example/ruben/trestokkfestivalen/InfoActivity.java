package com.example.ruben.trestokkfestivalen;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import static android.view.View.getDefaultSize;

public class InfoActivity extends AppCompatActivity {

    private static final String TAG = "Trestokk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        Button mBackButton = findViewById(R.id.back_button);
//      back_button is pressed
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Back button clicked");
                goBack();
            }
        });

////      Finding the videoView by ID and assigning the video file
//        VideoView videoView = findViewById(R.id.videoView);
//        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.info_video;
//        Uri uri = Uri.parse(videoPath);
//        videoView.setVideoURI(uri);
//        videoView.start();
//
////      Loops the video
//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mp.setLooping(true);
//            }
//        });
    }

//  Method for the back button
    public void goBack() {
        Intent intent = new Intent(this, com.example.ruben.trestokkfestivalen.MainActivity.class);
        startActivity(intent);
    }
}





//class CustomVideo extends VideoView {
//
//    private int mVideoWidth;
//    private int mVideoHeight;
//    private VideoView mVideoView;
//
//    public CustomVideo(Context context) {
//        super(context);
//    }
//
//    public CustomVideo(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public CustomVideo(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    public void setVideoSize(int width, int height) {
//        mVideoWidth = width;
//        mVideoHeight = height;
//    }
//
//
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//         Log.i("@@@", "onMeasure");
//        int width = getDefaultSize(mVideoWidth, widthMeasureSpec);
//        int height = getDefaultSize(mVideoHeight, heightMeasureSpec);
//        if (mVideoWidth > 0 && mVideoHeight > 0) {
//            if (mVideoWidth * height > width * mVideoHeight) {
//                Log.i("@@@", "image too tall, correcting");
//                height = width * mVideoHeight / mVideoWidth;
//            } else if (mVideoWidth * height < width * mVideoHeight) {
//                Log.i("@@@", "image too wide, correcting");
//                width = height * mVideoWidth / mVideoHeight;
//            } else {
//                Log.i("@@@", "aspect ratio is correct: " +
//                        width+"/"+height+"="+
//                        mVideoWidth+"/"+mVideoHeight);
//            }
//        }
//        Log.i("@@@", "setting size: " + width + 'x' + height);
//        setMeasuredDimension(width, height);
//    }
//}
