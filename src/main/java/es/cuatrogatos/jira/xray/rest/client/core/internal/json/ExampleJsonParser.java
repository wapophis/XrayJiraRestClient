package es.cuatrogatos.jira.xray.rest.client.core.internal.json;

import com.atlassian.jira.rest.client.internal.json.JsonObjectParser;
import com.atlassian.jira.rest.client.internal.json.JsonParseUtil;
import es.cuatrogatos.jira.xray.rest.client.api.domain.Example;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by lucho on 22/08/16.
 */
public class ExampleJsonParser implements JsonObjectParser<Example> {
    private final static String KEY_ID="id";
    private final static String KEY_RANK="rank";
    private final static String KEY_VALUES="values";
    private final static String KEY_STATUS="status";



    public Example parse(JSONObject jsonObject) throws JSONException {
        jsonObject.put("self",""); // TODO: ADD URI.
        URI selfUri = JsonParseUtil.getSelfUri(jsonObject);
        String key =" THERE IS NO KEY FOR TEST RUN AT X-RAY DIRECT REST API"; // TODO: GET THE ISSUE KEY
        return new Example(selfUri,key,jsonObject.getLong(KEY_ID)
                ,jsonObject.getInt("rank")
                ,getValues(jsonObject)
                ,getStatus(jsonObject));
    }

    private Example.Status getStatus(JSONObject jsonObject){
        return null;
    }

    private ArrayList<Object> getValues(JSONObject jsonObject) throws JSONException {
        JSONArray jsonValues=jsonObject.getJSONArray(KEY_VALUES);
        ArrayList<Object> values=new ArrayList<Object>();
        for(int i=0;i<jsonValues.length();i++){
                values.add((Object)jsonValues.getString(i));
        }
   return values;
    }
}
