package com.newsgobelins.user.appnews.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.newsgobelins.user.appnews.R;
import com.newsgobelins.user.appnews.models.Article;
import com.newsgobelins.user.appnews.viewmodel.ArticleViewModel;
import com.squareup.picasso.Picasso;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class ArticleDetailFragment extends Fragment {
    private ArticleViewModel viewModel;
    private View view;
    private TextView title;
    private TextView description;
    ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(ArticleViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.article_detail_fragment, container, false);
        title = view.findViewById(R.id.detail_title);
        description = view.findViewById(R.id.detail_description);
        image = view.findViewById(R.id.detail_image);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.getSelected().observe(this, new Observer<Article>() {
            @Override
            public void onChanged(Article article) {
                title.setText(article.getTitle());
                description.setText(article.getDescription());
                Picasso.get().load(article.getImage()).resize(1000, 400) // resizes the image to these dimensions (in pixel)
                        .centerCrop().into(image);
            }
        });
    }
}
