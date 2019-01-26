package com.newsgobelins.user.appnews.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.newsgobelins.user.appnews.R;
import com.newsgobelins.user.appnews.adapter.ArticleAdapter;
import com.newsgobelins.user.appnews.listener.ArticleListener;
import com.newsgobelins.user.appnews.models.Article;
import com.newsgobelins.user.appnews.viewmodel.ArticleViewModel;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class ArticleListFragment extends Fragment implements ArticleListener {
    private RecyclerView recyclerView;
    private ArticleViewModel model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Créer ou récuperer l'instance du viewmodel pour ce fragment
        model = ViewModelProviders.of(getActivity()).get(ArticleViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.article_list_fragment, container, false);
        recyclerView = view.findViewById(R.id.list_article);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        //Swipe
        SwipeRefreshLayout swipeContainer = view.findViewById(R.id.swipe_container);

        // Changer les couleurs du refresh
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_blue_light,
                android.R.color.holo_blue_dark);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        model.getArticles().observe(getViewLifecycleOwner(), new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                ArticleAdapter adapter = new ArticleAdapter(articles, ArticleListFragment.this);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onShare(Article article) {
        Toast.makeText(getContext(), "PARTAGER", Toast.LENGTH_SHORT).show();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Cet article " + article.getTitle() + " est trop bien");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    @Override
    public void onSelect(Article article) {
        model.setSelected(article);
        showDetail();
    }

    @Override
    public void onLike(Article article) {
        //Ici il faut ajouter le like dans la bdd et changer le bouton

    }

    private void showDetail() {
        ArticleDetailFragment fragment = new ArticleDetailFragment();

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

}