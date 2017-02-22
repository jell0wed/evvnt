package providers.requests;

import providers.AvailableProvider;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static providers.AvailableProvider.*;

/**
 * Created by jeremiep on 2017-02-21.
 */
public class SearchRequests {
    public static class ByQuery extends BaseParameterizedRequest {
        private String queryStr;

        public ByQuery(String q) {
            this.queryStr = q;
        }

        public Map<String, String> parameterize(AvailableProvider provider) {
            Map<String, String> result = new HashMap<>();
            switch (provider) {
                case EVENTBRITE:
                    result.put("q", this.queryStr);
                    return result;
            }

            throw new NotImplementedException();
        }
    }
}
