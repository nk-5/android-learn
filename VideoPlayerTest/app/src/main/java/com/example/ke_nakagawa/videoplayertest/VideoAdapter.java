package com.example.ke_nakagawa.videoplayertest;

import static android.widget.RelativeLayout.CENTER_HORIZONTAL;
import static android.widget.RelativeLayout.CENTER_VERTICAL;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class VideoAdapter extends ArrayAdapter {
    RelativeLayout videoLayout;
    MediaPlayerView videoView;

    public VideoAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        RelativeLayout.LayoutParams videoViewLayoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 400);

        videoLayout = new RelativeLayout(context);
        videoLayout.setLayoutParams(videoViewLayoutParams);
        videoView = new MediaPlayerView(context);
        videoView.setLayoutParams(videoViewLayoutParams);
        // https://iabtechlab.com/wp-content/uploads/2016/07/VAST-4.0-Short-Intro.mp4
        videoView.setResourceId(R.raw.vast_intro);

        RelativeLayout.LayoutParams playLayoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        playLayoutParams.addRule(CENTER_HORIZONTAL);
        playLayoutParams.addRule(CENTER_VERTICAL);
        final Button play = new Button(context);
        play.setLayoutParams(playLayoutParams);
        play.setText("play!!");
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.play();
                play.setVisibility(View.INVISIBLE);
            }
        });

        videoLayout.addView(videoView);
        videoLayout.addView(play);
    }

    @Override
    public int getCount() {
        return 50;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position == 20) {
            return videoLayout;
        } else {
            TextView textView = new TextView(getContext());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            textView.setText("test");
            textView.setTextSize(50);
            textView.setLayoutParams(layoutParams);
            return textView;
        }
    }
}
