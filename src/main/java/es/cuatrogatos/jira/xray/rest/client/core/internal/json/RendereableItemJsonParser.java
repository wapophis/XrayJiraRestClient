package es.cuatrogatos.jira.xray.rest.client.core.internal.json;

import com.atlassian.jira.rest.client.internal.json.JsonObjectParser;
import es.cuatrogatos.jira.xray.rest.client.core.internal.json.util.RendereableItem;
import es.cuatrogatos.jira.xray.rest.client.core.internal.json.util.RendereableItemImpl;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * Created by lucho on 22/08/16.
 */
public class RendereableItemJsonParser implements JsonObjectParser<RendereableItem> {
    private static final String KEY_RAW="raw";
    private static final String KEY_RENDERED="rendered";

    public RendereableItem parse(JSONObject jsonObject) throws JSONException {
        return new RendereableItemImpl(jsonObject.optString(KEY_RAW),jsonObject.optString(KEY_RENDERED));
    }
}
