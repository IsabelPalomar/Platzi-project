package com.platzi.platzigram.posts;

import com.platzi.platzigram.api.PlatzigramClient;
import com.platzi.platzigram.api.PlatzigramFirebaseService;
import com.platzi.platzigram.api.PostResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lucy on 30/09/16.
 */

public class PostRepositoryImpl implements  PostsRepository {

    PostTaskListener postTaskListener;

    public PostRepositoryImpl(PostTaskListener listener) {
        postTaskListener = listener;
    }

    @Override
    public void getPosts() {

        PlatzigramFirebaseService service = (new PlatzigramClient()).getService();

        Call<PostResponse> postListCall = service.getPostList();

        postListCall.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                if (response.isSuccessful()){
                    PostResponse result = response.body();
                    postTaskListener.onPostLoaded(result.getPostList());
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {

            }
        });


    }
}
