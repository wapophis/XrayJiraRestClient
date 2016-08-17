package es.cuatrogatos.jira.xray.rest.client.core.internal.json;

import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.internal.json.GenericJsonArrayParser;
import com.atlassian.jira.rest.client.internal.json.JsonObjectParser;
import com.atlassian.jira.rest.client.internal.json.JsonParseUtil;
import es.cuatrogatos.jira.xray.rest.client.api.domain.*;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lucho on 12/08/16.
 */
public class TestRunJsonParser implements JsonObjectParser<TestRun> {
    private static String DEFECTS_KEY="defects";
    private final static DefectJsonParser defectParser=new DefectJsonParser();
    public TestRunJsonParser(){
    }

    public TestRun parse(JSONObject jsonObject) throws JSONException {
        jsonObject.put("self",""); // TODO: ADD URI.
        URI selfUri = JsonParseUtil.getSelfUri(jsonObject);
        String key =" THERE IS NO KEY FOR TEST RUN AT X-RAY DIRECT REST API";
        Long id = Long.valueOf(jsonObject.getLong("id"));
        TestRun.Status status=getStatus(jsonObject);
        GenericJsonArrayParser arrayParser=new GenericJsonArrayParser(defectParser);
        Iterable<Defect> defects=arrayParser.parse(jsonObject.getJSONArray(DEFECTS_KEY));
        Iterable<Evidence> evidences=null;
        Iterable<Comment> comments=null;

        Date startedOn= null;
        try {
            startedOn = new SimpleDateFormat("dd/MMM/yy hh:mm aa").parse(jsonObject.getString("startedOn"));
        } catch (ParseException e) {
            e.printStackTrace();
            throw new JSONException(e.getMessage());
        }
        String executedBy=jsonObject.getString("executedBy");

        TestRun res=new TestRun(JsonParseUtil.getSelfUri(jsonObject),key,id,status,startedOn,executedBy,defects,evidences,comments);
        return res;
    }

    private TestRun.Status getStatus(JSONObject jsonObject) throws JSONException {
        if(jsonObject.get("status").equals("TODO")){
            return TestRun.Status.TODO;
        }
        if(jsonObject.get("status").equals("EXECUTING")){
            return TestRun.Status.EXECUTING;
        }
        if(jsonObject.get("status").equals("ABORTED")){
            return TestRun.Status.ABORTED;
        }
        if(jsonObject.get("status").equals("FAIL")){
            return TestRun.Status.FAIL;
        }
        if(jsonObject.get("status").equals("PASS")){
            return TestRun.Status.PASS;
        }
        return null;
    }

}
