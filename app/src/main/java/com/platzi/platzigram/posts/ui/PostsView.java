package com.platzi.platzigram.posts.ui;

import com.platzi.platzigram.model.Post;

import java.util.List;

/**
 * Created by lucy on 30/09/16.
 */

public interface PostsView {

    void showPosts(List<Post> posts);
    void showAddNewPostView();

}
