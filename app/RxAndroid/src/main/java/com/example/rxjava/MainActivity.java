package com.example.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    private final String TAG = "ggg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    private void singleton() {
        Observable.just("Hello", "world")
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) throws Exception {
                        Log.e(TAG, "apply: " + s);
                        return s.hashCode();
                    }
                })
                .last(0)
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: " + d);
                    }

                    @Override
                    public void onSuccess(Integer value) {
                        Log.e(TAG, "onSuccess: " + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e);
                    }
                });
    }

    private void mulThread() {
        Observable.just("Hello")
                .subscribeOn(Schedulers.io())
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) throws Exception {
                        Log.e(TAG, "map: " + s + " - " + Thread.currentThread().getName());
                        return s.hashCode();
                    }
                })
                .observeOn(Schedulers.newThread())
                .subscribe(new DisposableObserver<Integer>() {

                    @Override
                    public void onNext(Integer value) {
                        Log.e(TAG, "onNext: " + value + " - " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: " + Thread.currentThread().getName());
                    }
                });
    }
}
