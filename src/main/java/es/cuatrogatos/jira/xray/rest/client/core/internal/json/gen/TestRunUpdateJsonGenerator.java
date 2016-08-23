package es.cuatrogatos.jira.xray.rest.client.core.internal.json.gen;

import es.cuatrogatos.jira.xray.rest.client.api.domain.TestRun;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * Created by lucho on 23/08/16.
 */
public class TestRunUpdateJsonGenerator extends TestRunJsonGenerator {
    private static final String KEY_ADD="add";
    private static final String KEY_REMOVE="remove";

    public JSONObject generate(TestRun testRun) throws JSONException {

        JSONObject filteredJSON=super.generate(testRun);
        filteredJSON.remove(TestRunJsonGenerator.KEY_ID);
        filteredJSON.remove(TestRunJsonGenerator.KEY_EXECBY);
        filteredJSON.remove(TestRunJsonGenerator.KEY_STARTEDON);
        filteredJSON.remove(TestRunJsonGenerator.KEY_FINISHEDON);

        if(testRun.getVersion()!=0){
            // TODO: THINK ABOUT REMOVE THIS INSTANCEOF
            if(filteredJSON.opt(TestRunJsonGenerator.KEY_DEFECTS) instanceof JSONObject){
               //UPDATE NOTHING TO DO
                if(((JSONObject) filteredJSON.opt(TestRunJsonGenerator.KEY_DEFECTS)).opt(KEY_ADD)==null
                        &&
                        ((JSONObject) filteredJSON.opt(TestRunJsonGenerator.KEY_DEFECTS)).opt(KEY_REMOVE) ==null)
                    filteredJSON.remove(TestRunJsonGenerator.KEY_DEFECTS);
            }
            // TODO: THINK ABOUT REMOVE THIS INSTANCEOF
            if(filteredJSON.opt(TestRunJsonGenerator.KEY_DEFECTS) instanceof JSONArray || filteredJSON.opt(TestRunJsonGenerator.KEY_DEFECTS)==null){
                filteredJSON.remove(TestRunJsonGenerator.KEY_DEFECTS);
            }
            // TODO: THINK ABOUT REMOVE THIS INSTANCEOF
            if(filteredJSON.opt(TestRunJsonGenerator.KEY_EVIDENCES) instanceof JSONObject){
                //UPDATE NOTHING TO DO?
            }
            // TODO: THINK ABOUT REMOVE THIS INSTANCEOF
            if(filteredJSON.opt(TestRunJsonGenerator.KEY_EVIDENCES) instanceof JSONArray  || filteredJSON.opt(TestRunJsonGenerator.KEY_DEFECTS)==null){
                filteredJSON.remove(TestRunJsonGenerator.KEY_EVIDENCES);
            }
            // TODO: DO FILTERING
            filteredJSON.remove(TestRunJsonGenerator.KEY_TESTSTEPS);

            if(testRun.getComment().getRaw().equals(testRun.getOldVersion().getComment().getRaw())){
                filteredJSON.remove(TestRunJsonGenerator.KEY_COMMENT);
            }
        }else {
            filteredJSON.remove(TestRunJsonGenerator.KEY_DEFECTS);
            filteredJSON.remove(TestRunJsonGenerator.KEY_COMMENT);
            filteredJSON.remove(TestRunJsonGenerator.KEY_EVIDENCES);
            filteredJSON.remove(TestRunJsonGenerator.KEY_TESTSTEPS);
        }

            return filteredJSON;}

}
