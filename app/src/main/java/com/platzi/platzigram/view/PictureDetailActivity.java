package com.platzi.platzigram.view;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.platzi.platzigram.R;
import com.platzi.platzigram.model.Post;
import com.squareup.picasso.Picasso;

public class PictureDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);
        showToolbar("", true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setEnterTransition(new Fade());
        }

        Post post = (Post) getIntent().getSerializableExtra("Post");
        ImageView ivHeader = (ImageView) findViewById(R.id.imageHeader);
        TextView tvUserName = (TextView) findViewById(R.id.userNameDetail);

        Picasso.with(this).load(post.getImageUrl()).into(ivHeader);

        String userName = post.getAuthor().replace(",", ".");
        tvUserName.setText(userName);
    }

    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);

    }

}
