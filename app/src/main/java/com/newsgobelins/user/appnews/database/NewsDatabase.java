package com.newsgobelins.user.appnews.database;

import com.newsgobelins.user.appnews.database.daos.ArticleDao;
import com.newsgobelins.user.appnews.models.Article;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Article.class}, version = 1, exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();
}

