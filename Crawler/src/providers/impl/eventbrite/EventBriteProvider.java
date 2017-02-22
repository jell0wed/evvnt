package providers.impl.eventbrite;

import entities.Event;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import providers.AvailableProvider;
import providers.IEventProvider;
import providers.impl.eventbrite.models.SearchEventResponse;
import providers.requests.BaseParameterizedRequest;
import providers.requests.SearchRequests;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by jeremiep on 2017-02-18.
 */
public class EventBriteProvider implements IEventProvider {
    private static final String API_KEY = "53QM7WSQUWPCMTHHPEZ4";
    private static final String API_ENDPOINT = "https://www.eventbriteapi.com/";
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

    public Iterator<Event> crawlEvents(BaseParameterizedRequest request) {
        return new Iterator<Event>() {
            SearchEventResponse doQuery(int pageNo) {
                Map<String, String> params = request.parameterize(AvailableProvider.EVENTBRITE);
                params.put("page", String.valueOf(pageNo));

                try {
                    return eventBriteService.search(params).execute().body();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            SearchEventResponse response = doQuery(0);
            int entryCount = 0;
            int currentPageIndex = 0;
            int currentPage = 0;

            public boolean hasNext() {
                if(currentPageIndex >= this.response.events.size()) { // check to see if remaining events
                    if(entryCount >= response.pagination.object_count -1) { // no obj remaining
                        return false;
                    }

                    currentPageIndex = 0;
                    currentPage++;
                    this.response = doQuery(currentPage);
                }

                return true;
            }

            public Event next() {
                SearchEventResponse.Event evt = this.response.events.get(currentPageIndex);

                currentPageIndex++;
                entryCount++;

                return evt.createEntity();
            }
        };
    }

    public static void main(String[] args) {
        EventBriteProvider provider = new EventBriteProvider();
        Iterator<Event> evtItt = provider.crawlEvents(new SearchRequests.ByQuery("Montréal"));
        while(evtItt.hasNext()) {
            Event evt = evtItt.next();
            System.out.println(evt.name.text);
        }
    }
}
