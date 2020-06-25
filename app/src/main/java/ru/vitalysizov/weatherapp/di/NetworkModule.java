package ru.vitalysizov.weatherapp.di;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.vitalysizov.weatherapp.BuildConfig;
import ru.vitalysizov.weatherapp.data.network.api.IWeatherApiService;
import ru.vitalysizov.weatherapp.data.network.interceptors.ApiKeyInterceptor;
import ru.vitalysizov.weatherapp.data.network.interceptors.LanguageInterceptor;
import ru.vitalysizov.weatherapp.data.network.interceptors.MetricInterceptor;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    OkHttpClient provideClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.addInterceptor(new ApiKeyInterceptor());
        builder.addInterceptor(new LanguageInterceptor());
        builder.addInterceptor(new MetricInterceptor());

        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        return builder.build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    IWeatherApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(IWeatherApiService.class);
    }
}
