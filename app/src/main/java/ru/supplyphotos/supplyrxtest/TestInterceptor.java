package ru.supplyphotos.supplyrxtest;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author libgo (13.03.2018)
 */

public class TestInterceptor {

    public static OkHttpClient getInterceptor(){
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(chain -> {

            Request original = chain.request();

            Request request = original.newBuilder()
                    .header("x-access_token", "abacdafb6e91f52697e958d2c668733f")
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        });
        return client;
    }

    /*OkHttpClient client = new OkHttpClient();
client.interceptors().add(new Interceptor() {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        // Настраиваем запросы
        Request request = original.newBuilder()
                .header("Accept", "application/json")
                .header("Authorization", "auth-token")
                .method(original.method(), original.body())
                .build();

        Response response = chain.proceed(request);

        return response;
    }
});*/
}
