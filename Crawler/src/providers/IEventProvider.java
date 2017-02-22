package providers;

import entities.Event;
import providers.requests.BaseParameterizedRequest;

import java.util.Iterator;

/**
 * Created by jeremiep on 2017-02-18.
 */
public interface IEventProvider {
    Iterator<Event> crawlEvents(BaseParameterizedRequest request);
}
