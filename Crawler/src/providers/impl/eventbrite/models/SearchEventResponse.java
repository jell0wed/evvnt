package providers.impl.eventbrite.models;

import entities.Categories;
import entities.base.MultipartText;
import org.joda.time.DateTime;
import providers.impl.eventbrite.EventBriteProvider;
import providers.impl.eventbrite.models.simple.MultipartTime;
import providers.impl.eventbrite.models.simple.Pagination;

import java.util.List;

/**
 * Created by jeremiep on 2017-02-18.
 */
public class SearchEventResponse {

    public Pagination pagination;
    public List<Event> events;

    public class Event {
        public MultipartText name;
        public MultipartText description;
        public MultipartTime start;
        public MultipartTime end;
        public String currency;
        public Boolean is_free;
        public String locale;
        public String resource_uri;
        public String url;
        public String venue_id;
        public String category_id;

        public entities.Event createEntity() {
            entities.Event resultingEvent = new entities.Event();

            resultingEvent.name = name;
            resultingEvent.description = description;
            resultingEvent.start = DateTime.parse(this.start.utc);
            resultingEvent.end = DateTime.parse(this.end.utc);
            resultingEvent.source_url = url;
            resultingEvent.category = new Categories();
            resultingEvent.category.main = EventBriteProvider.loadedCategories.get(Integer.valueOf(this.category_id)).name;

            return resultingEvent;
        }
    }

}
