package providers.impl.eventbrite;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import providers.IEventProvider;
import providers.impl.eventbrite.models.EventResponse;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by jeremiep on 2017-02-18.
 */
public class EventBriteProvider implements IEventProvider {
    private static final String API_KEY = "53QM7WSQUWPCMTHHPEZ4";
    private static final String API_VERSION = "v3";
    private static final String API_ENDPOINT = "https://www.eventbriteapi.com/" + API_VERSION + "/";
    private static final IEventBriteService eventBriteService;

    static {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", "Bearer " + API_KEY);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_ENDPOINT)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        eventBriteService = retrofit.create(IEventBriteService.class);
    }

    public static void main(String[] args) {

    }
}
