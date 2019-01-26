package com.newsgobelins.user.appnews.network;

import com.newsgobelins.user.appnews.models.Article;
import com.newsgobelins.user.appnews.models.ArticleResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleService {

    @GET("top-headlines")
    Call<ArticleResult> listArticles(@Query("country") String country, @Query("apiKey") String apiKey);

}

