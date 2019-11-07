package com.example.rxjava;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class IntStreamTest {

    public static void main(String[] args) {
        IntStream.range(1, 100).filter(i -> i % 2 == 0).forEach(System.out::println);
        System.out.println(Observable.just("Hello World"));
        Observable.create(observableEmitter -> {
            try {
                if (!observableEmitter.isDisposed()) {
                    for (int i = 0; i < 5; i++) {
                        observableEmitter.onNext(i);
                    }
                    observableEmitter.onComplete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).subscribe(
                System.out::println,
                System.err::println,
                () ->
                        System.out.println("Sequence commplate")
        );

        Observable<Integer> defer = Observable.defer(() -> Observable.just(126));
        defer.subscribe(System.out::println);

        Observable<Integer> arr = Observable.defer(() -> Observable.fromArray(126, 456, 789));
        arr.subscribe(System.out::println);

        Observable<Long> integerObservable = Observable.interval(1, TimeUnit.SECONDS)
                .map(tick -> tick.longValue());
        integerObservable.subscribe(System.out::println);

        Observable<Long> observable = Observable.timer(1, TimeUnit.SECONDS).map(tick -> tick.longValue());
        observable.subscribe(System.out::println);
        // buffer 方法用于创建给定大小的组，然后将它们打包为列表。
        Observable.range(0,10).buffer(6).subscribe(System.out::println);
    }
}
