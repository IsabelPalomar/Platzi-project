package com.platzi.platzigram.posts;

import com.platzi.platzigram.model.Post;

import java.util.List;

/**
 * Created by lucy on 30/09/16.
 */

public interface PostTaskListener {
    void onPostLoaded(List<Post> posts);
}
