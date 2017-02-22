package providers.impl.eventbrite;

import providers.impl.eventbrite.models.SearchEventResponse;
import providers.impl.eventbrite.models.VenueResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import java.util.Map;

/**
 * Created by jeremiep on 2017-02-18.
 */
public interface IEventBriteService {
    @GET("/v3/events/search")
    Call<SearchEventResponse> search(@QueryMap Map<String, String> options);

    @GET("/v3/venues/{venue_id}/")
    Call<VenueResponse> venue(@Path("venue_id") String venue_id);
}
