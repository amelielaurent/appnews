package com.newsgobelins.user.appnews.database;

import android.content.Context;

import java.util.concurrent.Callable;

import androidx.room.Room;
import bolts.Continuation;
import bolts.Task;

public class DatabaseHelper {

    private static NewsDatabase database;

    public static void init(final Context context) {

        Task.callInBackground(new Callable<Void>()

        {
            @Override
            public Void call() throws Exception {
                database = Room.databaseBuilder(context, NewsDatabase.class, "news-db")
                        .fallbackToDestructiveMigration()
                        .build();
                return null;
            }
        }).continueWith(new Continuation<Void, Void>() {
            @Override
            public Void then(Task<Void> task) throws Exception {
                return null;
            }
        }, Task.UI_THREAD_EXECUTOR); //retourne sur le thread principal
    }

    public static NewsDatabase getDatabase() {
        return database;
    }

}