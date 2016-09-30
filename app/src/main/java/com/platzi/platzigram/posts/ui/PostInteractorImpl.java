package com.platzi.platzigram.posts.ui;

import com.platzi.platzigram.posts.PostRepositoryImpl;
import com.platzi.platzigram.posts.PostTaskListener;
import com.platzi.platzigram.posts.PostsInteractor;
import com.platzi.platzigram.posts.PostsRepository;

/**
 * Created by lucy on 30/09/16.
 */

public class PostInteractorImpl  implements PostsInteractor{

    PostsRepository postsRepository;

    public PostInteractorImpl(PostTaskListener listener) {
        this.postsRepository = new PostRepositoryImpl(listener);
    }

    @Override
    public void getPosts() {
        postsRepository.getPosts();
    }
}
