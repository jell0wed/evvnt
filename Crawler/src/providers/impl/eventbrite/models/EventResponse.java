package providers.impl.eventbrite.models;

import java.util.List;

/**
 * Created by jeremiep on 2017-02-18.
 */
public class EventResponse {

    public Pagination pagination;
    public List<Event> events;

    public class Event {
        public MultipartText name;
        public MultipartText description;
    }

}
