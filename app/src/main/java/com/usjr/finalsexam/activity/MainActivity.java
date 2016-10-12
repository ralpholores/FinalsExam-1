package com.usjr.finalsexam.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.usjr.finalsexam.R;
import com.usjr.finalsexam.fragments.VideoListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        VideoListFragment videoListFragment =
                (VideoListFragment) fm.findFragmentById(R.id.videoListFragment);
        videoListFragment.getOnVideoSelectedListener();
    }
}
