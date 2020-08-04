package pl.training.concurrency.ex020_rx_java;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    public static void main(String[] args) {

        CompositeDisposable compositeDisposable = new CompositeDisposable();

        Observable<String> observable = Observable.just("Java", "PHP", "JavaScript", "ActionScript");

        Observable<String> name = Observable.fromIterable(List.of("1", "2", "3"));

        compositeDisposable.add(observable.map(String::toLowerCase)
                .filter(language -> language.contains("java"))
                .buffer(2)
                .subscribe(System.out::println, System.out::println, () -> System.out.println("Completed")));

        compositeDisposable.add(Observable.fromCallable(new Sum(1, 5))
                .subscribe(System.out::println));

        Observable<Integer> numbers = Observable.create(emitter -> {
            Random random = new Random();
            for (int index = 0; index < 5; index++) {
                emitter.onNext(random.nextInt(101) + 1);
                Thread.sleep(500);
            }
            emitter.onComplete();
        });

        compositeDisposable.add(numbers
                .subscribe(System.out::println, System.out::println, () -> System.out.println("Completed")));

        PublishSubject<Integer> publishSubject = PublishSubject.create();
        publishSubject.onNext(1);
        compositeDisposable.add(publishSubject.subscribe(System.out::println));
        publishSubject.onNext(2);

        BehaviorSubject<Integer> behaviorSubject = BehaviorSubject.create();
        behaviorSubject.onNext(1);
        compositeDisposable.add(behaviorSubject.subscribe(System.out::println));
        behaviorSubject.onNext(2);

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        compositeDisposable.add(observable
                .map(String::length)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.from(executorService))
                .subscribe(System.out::println, System.out::println, () -> System.out.println("Completed")));
    }

}
