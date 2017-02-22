package providers.impl.eventbrite;

import providers.impl.eventbrite.models.EventResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import java.util.List;
import java.util.Map;

/**
 * Created by jeremiep on 2017-02-18.
 */
public interface IEventBriteService {
    @GET("/v3/events/search")
    Call<EventResponse> search(@QueryMap Map<String, String> options);
}
