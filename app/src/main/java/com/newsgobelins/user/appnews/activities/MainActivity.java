package com.newsgobelins.user.appnews.activities;

import android.os.Bundle;

import com.newsgobelins.user.appnews.R;
import com.newsgobelins.user.appnews.database.DatabaseHelper;
import com.newsgobelins.user.appnews.fragments.ArticleListFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper.init(this);

        showArticleList();
    }

    private void showArticleList() {
        ArticleListFragment fragment = new ArticleListFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }
}
