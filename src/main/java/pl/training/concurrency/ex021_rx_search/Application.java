package pl.training.concurrency.ex021_rx_search;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.training.concurrency.ex021_rx_search.github.GithubService;
import pl.training.concurrency.ex021_rx_search.wikipedia.Article;
import pl.training.concurrency.ex021_rx_search.wikipedia.WikipediaService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

public class Application {

    private final GithubService githubService = new GithubService(retrofitBuilder("https://api.github.com/"));
    private final WikipediaService wikipediaService = new WikipediaService(retrofitBuilder("https://en.wikipedia.org/w/"));
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private Retrofit retrofitBuilder(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                        .build())
                .build();
    }

    private <T> void showThreadInfo(T input) {
        System.out.println("Processing item on thread " + Thread.currentThread().getName());
    }

    private Observable<String> sendWikipediaQuery(String query) {
        return wikipediaService.getArticles(query)
                .flatMap(Observable::fromIterable)
                .map(Article::getTitle)
                //.doOnNext(this::showThreadInfo)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread());
    }

    private void start() {
        compositeDisposable.add(ObservableReader.from(System.in)
                .debounce(5, TimeUnit.SECONDS)
                .flatMap(this::sendWikipediaQuery)
                .subscribe(System.out::println, System.out::println, () -> System.out.println("Completed")));
    }

    public static void main(String[] args) throws InterruptedException {
        new Application().start();
        Thread.sleep(10_000);
    }

}

/*

https://proandroiddev.com/understanding-rxjava-subscribeon-and-observeon-744b0c6a41ea

We can specify a thread to execute any operator by using subscribeOn and/or observeOn.

subscribeOn affects upstream operators (operators above the subscribeOn)

observeOn affects downstream operators (operators below the observeOn)

If only subscribeOn is specified, all operators will be be executed on that thread

If only observeOn is specified, all operators will be executed on the current thread and only operators
below the observeOn will be switched to thread specified by the observeOn

 */