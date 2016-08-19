package es.cuatrogatos.jira.xray.rest.client.core.internal.json;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import es.cuatrogatos.jira.xray.rest.client.core.internal.json.util.XrayJiraDateFormatter;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.atlassian.jira.rest.client.internal.json.GenericJsonArrayParser;
import com.atlassian.jira.rest.client.internal.json.JsonObjectParser;
import com.atlassian.jira.rest.client.internal.json.JsonParseUtil;

import es.cuatrogatos.jira.xray.rest.client.api.domain.Comment;
import es.cuatrogatos.jira.xray.rest.client.api.domain.Defect;
import es.cuatrogatos.jira.xray.rest.client.api.domain.Evidence;
import es.cuatrogatos.jira.xray.rest.client.api.domain.TestRun;

/**
 * Created by lucho on 12/08/16.
 */
public class TestRunJsonParser implements JsonObjectParser<TestRun> {
    private final static DefectJsonParser defectParser=new DefectJsonParser();
    private final static EvidenceJsonParser evidenceParser=new EvidenceJsonParser();
    public TestRunJsonParser(){
    }

    public TestRun parse(JSONObject jsonObject) throws JSONException {
        jsonObject.put("self",""); // TODO: ADD URI.
        URI selfUri = JsonParseUtil.getSelfUri(jsonObject);
        String key =" THERE IS NO KEY FOR TEST RUN AT X-RAY DIRECT REST API"; // TODO: GET THE ISSUE KEY
        Long id = Long.valueOf(jsonObject.getLong("id"));
        TestRun.Status status=getStatus(jsonObject);
        GenericJsonArrayParser arrayParser=new GenericJsonArrayParser(defectParser);
        Iterable<Defect> defects=arrayParser.parse(jsonObject.getJSONArray("defects"));
        arrayParser=new GenericJsonArrayParser(evidenceParser);
        Iterable<Evidence> evidences=arrayParser.parse(jsonObject.getJSONArray("evidences"));

        Date startedOn = null;
        Date finishedOn = null;
        String executedBy = null;
        String assignee = null;
        
        try {
            if (!jsonObject.isNull("startedOn")) {
                startedOn = new XrayJiraDateFormatter().parse(jsonObject.getString("startedOn"));
            }
            if (!jsonObject.isNull("finishedOn")) {
                finishedOn= new XrayJiraDateFormatter().parse(jsonObject.getString("finishedOn"));
            }
            if (!jsonObject.isNull("executedBy")) {
                executedBy = jsonObject.getString("executedBy");
            }
            if (!jsonObject.isNull("assignee")) {
                assignee = jsonObject.getString("assignee");
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw new JSONException(e.getMessage());
        }

        TestRun res=new TestRun(selfUri,key,id,status,startedOn,finishedOn,assignee,executedBy,defects,evidences,parseComment(jsonObject));
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

    private Comment parseComment(JSONObject jsonObject){
        try {
            return new Comment(jsonObject.getString("comment"), jsonObject.getString("comment"));
        }catch(JSONException jE){
            return new Comment("","");
        }
    }



}
