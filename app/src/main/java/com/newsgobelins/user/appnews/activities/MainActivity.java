package com.newsgobelins.user.appnews.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;
import com.newsgobelins.user.appnews.R;
import com.newsgobelins.user.appnews.adapter.PageAdapter;
import com.newsgobelins.user.appnews.database.DatabaseHelper;
import com.newsgobelins.user.appnews.fragments.ArticleListFragment;
import com.newsgobelins.user.appnews.fragments.ContactFragment;
import com.newsgobelins.user.appnews.network.NetworkHelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper.init(this);
        NetworkHelper.init(this);

        showArticleList();

        //Pour tablayout
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        PageAdapter adapter = new PageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ArticleListFragment(), "Actualités");
        adapter.addFragment(new ContactFragment(), "Contact");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        int[] tabIcons = {
                R.drawable.ic_home,
                R.drawable.ic_contact,
        };
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FrameLayout details_fragment;
                details_fragment = (FrameLayout) findViewById(R.id.details_container);
                details_fragment.setVisibility(View.INVISIBLE);
                System.out.println("click tab");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                FrameLayout details_fragment;
                details_fragment = (FrameLayout) findViewById(R.id.details_container);
                details_fragment.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                FrameLayout details_fragment;
                details_fragment = (FrameLayout) findViewById(R.id.details_container);
                details_fragment.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void showArticleList() {
        ArticleListFragment fragment = new ArticleListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
