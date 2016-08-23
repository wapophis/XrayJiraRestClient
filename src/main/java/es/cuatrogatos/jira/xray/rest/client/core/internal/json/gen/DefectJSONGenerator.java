package es.cuatrogatos.jira.xray.rest.client.core.internal.json.gen;

import com.atlassian.jira.rest.client.internal.json.gen.JsonGenerator;
import es.cuatrogatos.jira.xray.rest.client.api.domain.Defect;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * Created by lucho on 23/08/16.
 */
public class DefectJSONGenerator implements JsonGenerator<Defect> {

    public static final String KEY_ID="id";
    public static final String KEY_KEY="key";
    public static final String KEY_SUMMARY="summary";
    public static final String KEY_STATUS="status";


    public JSONObject generate(Defect defect) throws JSONException {
        return new JSONObject().put(KEY_ID,defect.getId())
                .put(KEY_KEY,defect.getKey())
                .put(KEY_SUMMARY,defect.getSummary())
                .put(KEY_STATUS,defect.getStatus());
    }
}
