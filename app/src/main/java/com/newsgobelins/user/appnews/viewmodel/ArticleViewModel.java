package com.newsgobelins.user.appnews.viewmodel;

import com.newsgobelins.user.appnews.database.DatabaseHelper;
import com.newsgobelins.user.appnews.models.Article;
import com.newsgobelins.user.appnews.models.ArticleResult;
import com.newsgobelins.user.appnews.network.ArticleService;
import com.newsgobelins.user.appnews.utils.Constants;

import java.util.List;
import java.util.concurrent.Callable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import bolts.Task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticleViewModel extends ViewModel {

    private MutableLiveData<List<Article>> articlesLiveData;

    private MutableLiveData<Article> selected = new MutableLiveData<>();

    public LiveData<List<Article>> getArticles() {
        if (articlesLiveData == null) {
            articlesLiveData = new MutableLiveData<>();
            loadArticles();
        }
        return articlesLiveData;
    }

    private void loadArticles() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ArticleService service = retrofit.create(ArticleService.class);

        Call<ArticleResult> articles_new = service.listArticles("us", Constants.API_KEY);
        articles_new.enqueue(new Callback<ArticleResult>() {
            @Override
            public void onResponse(Call<ArticleResult> call, Response<ArticleResult> response) {
                List<Article> articles = response.body().getArticles();
                articlesLiveData.setValue(articles);

                //On sauvegarde les articles dans la database
                saveNews(articles);

            }

            @Override
            public void onFailure(Call<ArticleResult> call, Throwable t) {
                System.out.println("Resultat Erreur" + t.getLocalizedMessage());
            }
        });
    }

    private void saveNews(final List<Article> articles) {
        Task.callInBackground(new Callable<Void>() {
            @Override
            public Void call() {
                DatabaseHelper.getDatabase().articleDao().insertAll(articles);
                return null;
            }
        });
    }

    public void setSelected(Article article) {
        selected.setValue(article);
    }

    public LiveData<Article> getSelected() {
        return selected;
    }
}
