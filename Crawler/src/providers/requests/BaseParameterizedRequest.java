package providers.requests;

import providers.AvailableProvider;

import java.util.Map;

/**
 * Created by jeremiep on 2017-02-21.
 */
public abstract class BaseParameterizedRequest {
    public abstract Map<String, String> parameterize(AvailableProvider provider);
}
