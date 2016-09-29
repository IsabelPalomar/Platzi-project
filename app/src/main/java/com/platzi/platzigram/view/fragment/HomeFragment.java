package com.platzi.platzigram.view.fragment;



import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.platzi.platzigram.R;
import com.platzi.platzigram.adapter.PictureAdapterRecyclerView;
import com.platzi.platzigram.adapter.PostAdapterRecyclerView;
import com.platzi.platzigram.api.PlatzigramClient;
import com.platzi.platzigram.api.PlatzigramFirebaseService;
import com.platzi.platzigram.api.PostResponse;
import com.platzi.platzigram.model.Picture;
import com.platzi.platzigram.model.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class HomeFragment extends Fragment {

    RecyclerView picturesRecycler;
    LinearLayoutManager linearLayoutManager;
    ArrayList<Post> posts;
    PostAdapterRecyclerView postAdapterRecyclerView;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        showToolbar(getResources().getString(R.string.tab_home), false, view);

        posts = new ArrayList<>();
        populateData();

        picturesRecycler = (RecyclerView) view.findViewById(R.id.pictureRecycler);

        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        picturesRecycler.setLayoutManager(linearLayoutManager);

        postAdapterRecyclerView =
                new PostAdapterRecyclerView(posts, R.layout.cardview_picture, getActivity());
        picturesRecycler.setAdapter(postAdapterRecyclerView);


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewPostFragment newPostFragment = new NewPostFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, newPostFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        populateData();
    }

    private void populateData() {

        PlatzigramFirebaseService service = (new PlatzigramClient()).getService();

        Call<PostResponse> postListCall = service.getPostList();

        postListCall.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                if (response.isSuccessful()){
                    PostResponse result = response.body();
                    posts.clear();
                    posts.addAll(result.getPostList());
                    postAdapterRecyclerView.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {

            }
        });


    }

    public ArrayList<Picture> buidPictures(){
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("http://www.novalandtours.com/images/guide/guilin.jpg", "Uriel Ramírez", "4 días", "3 Me Gusta"));
        pictures.add(new Picture("http://www.enjoyart.com/library/landscapes/tuscanlandscapes/large/Tuscan-Bridge--by-Art-Fronckowiak-.jpg", "Juan Pablo", "3 días", "10 Me Gusta"));
        pictures.add(new Picture("http://www.educationquizzes.com/library/KS3-Geography/river-1-1.jpg", "Anahi Salgado", "2 días", "9 Me Gusta"));
        return pictures;
    }

    public void showToolbar(String tittle, boolean upButton, View view){
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(tittle);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);


    }

}
