package com.platzi.platzigram.posts.ui;

import com.platzi.platzigram.model.Post;
import com.platzi.platzigram.posts.PostTaskListener;
import com.platzi.platzigram.posts.PostsInteractor;
import com.platzi.platzigram.posts.PostsPresenter;

import java.util.List;

/**
 * Created by lucy on 30/09/16.
 */

public class PostPresenterImpl implements PostsPresenter, PostTaskListener {

    PostsView postsView;
    PostsInteractor postsInteractor;

    public PostPresenterImpl(PostsView postsView) {
        this.postsView = postsView;
        this.postsInteractor = new PostInteractorImpl(this);
    }

    @Override
    public void loadPosts() {
        postsInteractor.getPosts();
    }

    @Override
    public void onPostLoaded(List<Post> posts) {
        postsView.showPosts(posts);
    }
}
