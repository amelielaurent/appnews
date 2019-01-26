package com.newsgobelins.user.appnews.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Article {

    @ColumnInfo(name = "article_image")

    private String urlToImage;

    @ColumnInfo(name = "article_title")
    private String title;

    @PrimaryKey
    @NonNull
    private String url;

    @ColumnInfo(name = "article_description")
    private String description;

    @ColumnInfo(name = "article_like")
    private int like;


    public Article() {
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return urlToImage;
    }

    public void setImage(String image) {
        this.urlToImage = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Article{" +
                "urlToImage='" + urlToImage + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
