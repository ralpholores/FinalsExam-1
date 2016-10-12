package com.usjr.finalsexam.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.usjr.finalsexam.R;
import com.usjr.finalsexam.adapters.VideoListAdapter;
import com.usjr.finalsexam.entity.Video;

import java.util.ArrayList;

public class VideoListFragment extends Fragment implements AdapterView.OnItemClickListener {

    public interface OnVideoSelectedListener {
        void videoSelectedListener(Video video);
    }

    private VideoListAdapter        mAdapter;
    private OnVideoSelectedListener mOnVideoSelectedListener;

    private DatabaseReference mRootDb;
    private DatabaseReference mVideosDb;
    ArrayList<Video> listVideo = new ArrayList<>();

    public VideoListFragment() {
        // Required empty public constructor
    }

    public OnVideoSelectedListener getOnVideoSelectedListener() {
        return mOnVideoSelectedListener;
    }

    public VideoListFragment setOnVideoSelectedListener(OnVideoSelectedListener onVideoSelectedListener) {
        mOnVideoSelectedListener = onVideoSelectedListener;
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRootDb = FirebaseDatabase.getInstance().getReference();
        mVideosDb = mRootDb.child("videos");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Toast.makeText(getContext(),"Retrieved Data", Toast.LENGTH_SHORT).show();
                    Video video = dataSnapshot1.getValue(Video.class);
                    listVideo.add(video);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mVideosDb.addValueEventListener(valueEventListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_list, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listView);

        mAdapter = new VideoListAdapter(getContext(), listVideo);
        listView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mOnVideoSelectedListener == null) {
            return;
        }
    }
}
