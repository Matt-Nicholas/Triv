package com.example.guest.triv.services;

import com.example.guest.triv.Constants;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;
import okhttp3.Call;

public class TriviaService {
    public static void findQuestions(Callback callback){
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer("","");
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer))
                .build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.TRIVIA_BASE_URL).newBuilder();
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
