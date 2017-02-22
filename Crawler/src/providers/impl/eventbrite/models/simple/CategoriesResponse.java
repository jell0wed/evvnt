package providers.impl.eventbrite.models.simple;

import java.util.List;

/**
 * Created by jeremiep on 2017-02-21.
 */
public class CategoriesResponse {
    public Pagination pagination;
    public List<Category> categories;

    public class Category {
        public String resource_uri;
        public String id;
        public String name;
        public String name_localized;
        public String short_name;
        public String short_name_localized;
    }
}
