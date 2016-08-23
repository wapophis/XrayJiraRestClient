package es.cuatrogatos.jira.xray.rest.client.core.internal.json.gen;

import com.atlassian.jira.rest.client.internal.json.gen.JsonGenerator;
import es.cuatrogatos.jira.xray.rest.client.api.domain.TestRun;
import es.cuatrogatos.jira.xray.rest.client.api.domain.TestStep;
import es.cuatrogatos.jira.xray.rest.client.core.internal.json.TestRunJsonParser;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lucho on 22/08/16.
 */

public class TestRunJsonGenerator implements JsonGenerator<TestRun> {
    public final static String KEY_ID="id";
    public final static String KEY_EXECBY="executedBy";
    public final static String KEY_STARTEDON="startedOn";
    public final static String KEY_ASSIGNEE="assignee";
    public final static String KEY_FINISHEDON="finishedON";
    // UPDATABLE FIELDS
    public final static String KEY_COMMENT="comment";
    public final static String KEY_STATUS="status";
    public final static String KEY_DEFECTS="defects";
    public final static String KEY_EVIDENCES="evidences";
    public final static String KEY_EXAMPLES="examples";
    public final static String KEY_TESTSTEPS="steps";
    // CUMCUMBER TESTS
    public final static String KEY_SCENARIO="scenario";
    public final static String KEY_SCENARIO_OUTLINE="scenarioOutline";


    private final static RendereableItemJsonGenerator commentsGenerator=new RendereableItemJsonGenerator();
    private final static TestStepJsonGenerator testStepsGenerator=new TestStepJsonGenerator();


    public JSONObject generate(TestRun testRun) throws JSONException {
        JSONObject ex=new JSONObject();
        // INMUTABLE FIELDS
        ex.put(KEY_ID,testRun.getOldVersion().getId());
        ex.put(KEY_EXECBY,testRun.getOldVersion().getExecutedBy());
        ex.put(KEY_STARTEDON,testRun.getOldVersion().getStartedOn());
        ex.put(KEY_ASSIGNEE,testRun.getOldVersion().getAssignee());
        ex.put(KEY_FINISHEDON,testRun.getOldVersion().getFinishedOn());
        // UPDATABLE FIELDS
        ex.put(KEY_COMMENT,commentsGenerator.generate(testRun.getComment()));
        ex.put(KEY_STATUS,testRun.getStatus().name());
        ex.put(KEY_DEFECTS,generateDefects(testRun));
        ex.put(KEY_EVIDENCES,generateEvidences(testRun));
        if(testRun.getSteps()!=null)
            ex.put(KEY_TESTSTEPS,generateTestSteps(testRun));
        // EXAMPLES IS CUCUMBER


        return ex;
    }

    private JSONObject generateEvidences(TestRun testRun) {
        return new JSONObject();
    }

    private JSONObject generateDefects(TestRun testRun) {

        return new JSONObject();
    }

    private JSONArray generateTestSteps(TestRun testRun) throws JSONException {
        ArrayList<JSONObject> testStepsArray=new ArrayList<JSONObject>();
        for(TestStep testStep: testRun.getSteps()){
                testStepsArray.add(testStepsGenerator.generate(testStep));
        }

    return new JSONArray(testStepsArray);}


}
