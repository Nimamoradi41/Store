package com.example.store;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;

import org.neshan.core.LngLat;
import org.neshan.ui.ClickData;
import org.neshan.ui.ClickType;
import org.neshan.ui.MapEventListener;

import java.util.concurrent.TimeUnit;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AppStart extends Application {
    public static Typeface danaTypeface;
    private static Api api;
    @Override
    public void onCreate() {



        
        super.onCreate();
        danaTypeface = Typeface.createFromAsset(getAssets(), "fonts/vazir_med.ttf");
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new io.github.inflationx.calligraphy3.CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/vazir_med.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());




    }
    public static Api getApi() {
        if (api == null) {
            HttpLoggingInterceptor interceptor;
            interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(Constants.CONNECT_TIME_OUT, TimeUnit.SECONDS)
                    .readTimeout(Constants.READ_TIME_OUT, TimeUnit.SECONDS)
                    .writeTimeout(Constants.WRITE_TIME_OUT, TimeUnit.SECONDS)
                    .build();
            api = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                    .create(Api.class);
        }


        return api;
    }

}
