package com.newsgobelins.user.appnews.listener;

import com.newsgobelins.user.appnews.models.Article;

public interface ShareListener {
    void onShare(Article article);
    void onSelect(Article article);
    void onLike(Article article);
}
