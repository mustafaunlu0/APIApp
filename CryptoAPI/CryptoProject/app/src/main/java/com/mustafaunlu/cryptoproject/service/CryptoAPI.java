package com.mustafaunlu.cryptoproject.service;

import com.mustafaunlu.cryptoproject.model.CryptoModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface CryptoAPI {


    @GET("prices?key=526ae6515bb61f8d8ad55f4098109d1d4464f790")
    Observable<List<CryptoModel>> getData();
}
