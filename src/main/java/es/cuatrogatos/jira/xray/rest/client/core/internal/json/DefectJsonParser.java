package es.cuatrogatos.jira.xray.rest.client.core.internal.json;

import com.atlassian.jira.rest.client.internal.json.JsonObjectParser;
import com.atlassian.jira.rest.client.internal.json.JsonParseUtil;
import es.cuatrogatos.jira.xray.rest.client.api.domain.Defect;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.net.URI;

/**
 * Created by lucho on 16/08/16.
 */
public class DefectJsonParser implements JsonObjectParser<Defect> {
    public static final String DEFECT_KEY_ID="id";
    public static final String DEFECT_KEY="key";
    public static final String DEFECT_KEY_SUMMARY="summary";
    public static final String DEFECT_KEY_STATUS="status";

    public Defect parse(JSONObject jsonObject) throws JSONException {
        jsonObject.put("self",""); // TODO GET THE SELF URI FOR NAVIGATION
        Long id=jsonObject.getLong(DEFECT_KEY_ID);
        String key=jsonObject.getString(DEFECT_KEY);
        String summary=jsonObject.getString(DEFECT_KEY_SUMMARY);
        String status=jsonObject.getString(DEFECT_KEY_STATUS);
        Defect myDefect;
        myDefect = new Defect(JsonParseUtil.getSelfUri(jsonObject),key,id,summary,status);
        return myDefect;
    }
}
