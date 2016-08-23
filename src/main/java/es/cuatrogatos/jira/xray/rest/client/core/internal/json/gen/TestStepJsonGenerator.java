package es.cuatrogatos.jira.xray.rest.client.core.internal.json.gen;

import com.atlassian.jira.rest.client.internal.json.gen.JsonGenerator;
import com.google.common.collect.Iterables;
import es.cuatrogatos.jira.xray.rest.client.api.domain.Evidence;
import es.cuatrogatos.jira.xray.rest.client.api.domain.TestRun;
import es.cuatrogatos.jira.xray.rest.client.api.domain.TestStep;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by lucho on 22/08/16.
 */
public class TestStepJsonGenerator  implements JsonGenerator<TestStep> {

    private final static RendereableItemJsonGenerator rendereableGenerator=new RendereableItemJsonGenerator();

    private final static String KEY_ID="id";
    private final static String KEY_INDEX="index";
    private final static String KEY_STEP="step";
    private final static String KEY_DATA="data";
    private final static String KEY_RESULT="result";
    // UPDATABLE FIELDS
    private final static String KEY_ATTACHMENTS="attachments";
    private final static String KEY_EVIDENCES="evidences";
    private final static String KEY_DEFECTS="defects";

    public JSONObject generate(TestStep testStep) throws JSONException {
        JSONObject ex=new JSONObject();
        ex.put(KEY_ID,testStep.getId());
        ex.put(KEY_INDEX,testStep.getIndex());
        ex.put(KEY_STEP,rendereableGenerator.generate(testStep.getStep()));
        ex.put(KEY_DATA,rendereableGenerator.generate(testStep.getData()));
        ex.put(KEY_RESULT,rendereableGenerator.generate(testStep.getResult()));
        if(testStep.getAttachments()!=null)
            ex.put(KEY_ATTACHMENTS,generateAttachments(testStep));

//        ex.put(KEY_EVIDENCES,generateEvidences(testStep)); // TODO: IMPLEMENT IT
//        ex.put(KEY_DEFECTS,generateDefects(testStep)); // TODO: IMPLEMENT IT

    return ex;
    }


    private JSONObject generateAttachments(TestStep testStep) throws JSONException {
        ArrayList<Evidence> removes=new ArrayList<Evidence>();
        ArrayList<Evidence> adds=new ArrayList<Evidence>();
        Iterable<Evidence> all;

        if( testStep.getVersion()!=0)
            all=Iterables.concat(testStep.getOldVersion().getAttachments(),testStep.getAttachments());
        else
            all=testStep.getAttachments();

        if(testStep.getVersion()!=0){
            ArrayList<Evidence> oldEv=new ArrayList<Evidence>();
            ArrayList<Evidence> newEv=new ArrayList<Evidence>();
            Iterables.addAll(oldEv,testStep.getOldVersion().getAttachments());
            Iterables.addAll(newEv,testStep.getAttachments());
            for(Evidence ev: all){
                if(!oldEv.contains(ev) && !newEv.contains(ev)){
                    removes.add(ev);
                }
                if(!oldEv.contains(ev) && newEv.contains(ev)){
                    adds.add(ev);
                }
            }

        }
        JSONObject evidences=new JSONObject();
        if(!adds.isEmpty())
            evidences.put("add",new JSONArray(adds));
        if(!removes.isEmpty())
            evidences.put("remove",new JSONArray(removes));

        return evidences;
    }

    private JSONObject generateEvidences(TestStep testStep) throws JSONException {
        ArrayList<Evidence> removes=new ArrayList<Evidence>();
        ArrayList<Evidence> adds=new ArrayList<Evidence>();

        Iterable<Evidence> all=Iterables.concat(testStep.getOldVersion().getAttachments(),testStep.getAttachments());
        if(testStep.getVersion()!=0){
            ArrayList<Evidence> oldEv=new ArrayList<Evidence>();
            ArrayList<Evidence> newEv=new ArrayList<Evidence>();
            Iterables.addAll(oldEv,testStep.getOldVersion().getAttachments());
            Iterables.addAll(newEv,testStep.getAttachments());
            for(Evidence ev: all){
                if(!oldEv.contains(ev) && !newEv.contains(ev)){
                    removes.add(ev);
                }
                if(!oldEv.contains(ev) && newEv.contains(ev)){
                    adds.add(ev);
                }
            }

        }
        JSONObject evidences=new JSONObject();
        evidences.put("add",new JSONArray(adds));
        evidences.put("remove",new JSONArray(removes));
        return evidences;
    }

    private JSONObject generateDefects(TestStep testStep) throws JSONException {
        ArrayList<Evidence> removes = new ArrayList<Evidence>();
        ArrayList<Evidence> adds = new ArrayList<Evidence>();

        Iterable<Evidence> all = Iterables.concat(testStep.getOldVersion().getAttachments(), testStep.getAttachments());
        if (testStep.getVersion() != 0) {
            ArrayList<Evidence> oldEv = new ArrayList<Evidence>();
            ArrayList<Evidence> newEv = new ArrayList<Evidence>();
            Iterables.addAll(oldEv, testStep.getOldVersion().getAttachments());
            Iterables.addAll(newEv, testStep.getAttachments());
            for (Evidence ev : all) {
                if (!oldEv.contains(ev) && !newEv.contains(ev)) {
                    removes.add(ev);
                }
                if (!oldEv.contains(ev) && newEv.contains(ev)) {
                    adds.add(ev);
                }
            }

        }
        JSONObject evidences = new JSONObject();
        evidences.put("add", new JSONArray(adds));
        evidences.put("remove", new JSONArray(removes));
        return evidences;
    }

}
