package com.mustafaunlu.cryptoproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mustafaunlu.cryptoproject.R;
import com.mustafaunlu.cryptoproject.adapter.CryptoAdapter;
import com.mustafaunlu.cryptoproject.databinding.ActivityMainBinding;
import com.mustafaunlu.cryptoproject.model.CryptoModel;
import com.mustafaunlu.cryptoproject.service.CryptoAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    Retrofit retrofit;
    private String baseUrl="https://api.nomics.com/v1/";
    CompositeDisposable compositeDisposable;
    ArrayList<CryptoModel> cryptoModelArrayList;
    CryptoAdapter cryptoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Gson gson=new GsonBuilder().setLenient().create();

        retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        loadData();


    }

    private void loadData() {
        CryptoAPI cryptoAPI=retrofit.create(CryptoAPI.class);

        compositeDisposable=new CompositeDisposable();
        compositeDisposable.add(cryptoAPI.getData()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(this::handleResponse)
        );
    }

    private void handleResponse(List<CryptoModel> cryptoModels) {
        cryptoModelArrayList=new ArrayList<>(cryptoModels);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        CryptoAdapter cryptoAdapter=new CryptoAdapter(cryptoModelArrayList);
        binding.recyclerView.setAdapter(cryptoAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}