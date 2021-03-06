package es.cuatrogatos.jira.xray.rest.client.core.internal.json.gen;

import com.google.common.collect.Iterables;
import es.cuatrogatos.jira.xray.rest.client.api.domain.Defect;
import es.cuatrogatos.jira.xray.rest.client.api.domain.Evidence;
import es.cuatrogatos.jira.xray.rest.client.api.domain.TestStep;
import es.cuatrogatos.jira.xray.rest.client.core.internal.json.EvidenceJsonParser;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by lucho on 25/08/16.
 */
public class TestStepUpdateJsonGenerator extends TestStepJsonGenerator {
    public final static String KEY_ADDS="add";
    public final static String KEY_REMOVES="remove";
    private final static EvidenceJsonGenerator evGenerator=new EvidenceJsonGenerator();


    public JSONObject generate(TestStep testStep) throws JSONException {
        JSONObject ex=new JSONObject();


        if(testStep.getVersion()!=0){ // BUILD THE UPDATE JSON
            ex.put(KEY_ID,testStep.getId());
            if(!testStep.getStatus().name().equals(testStep.getOldVersion().getStatus().name()))
            {
                ex.put(KEY_STATUS,testStep.getStatus());
            }
            if(!testStep.getComment().getRaw().equals(testStep.getOldVersion().getComment().getRaw())){
                ex.put(KEY_COMMENT,testStep.getComment().getRaw());
            }
          //  ex.put(KEY_ATTACHMENTS,getAttachmentsUpdate(testStep));
            ex.put(KEY_EVIDENCES,getEvidencesUpdates(testStep));
            ex.put(KEY_DEFECTS,getDefectsUpdates(testStep));
        }
        return ex;

    }

    private JSONObject getAttachmentsUpdate(TestStep testStep) throws JSONException {
        Collection<Evidence> allAttachments=new ArrayList<Evidence>();
        Iterables.addAll(allAttachments,Iterables.concat(testStep.getAttachments(),testStep.getOldVersion().getAttachments()));
        JSONArray adds=new JSONArray();
        JSONArray removes=new JSONArray();
        JSONObject ex=null;

        for(Evidence e:allAttachments){
            if( Iterables.contains(testStep.getAttachments(),e) && !Iterables.contains(testStep.getOldVersion().getAttachments(),e))
            {
                adds.put(evGenerator.generate(e));
            }

            if( !Iterables.contains(testStep.getAttachments(),e) && Iterables.contains(testStep.getOldVersion().getAttachments(),e))
            {
                removes.put(evGenerator.generate(e));
            }
        }
        if(adds!=null || removes!=null){
            ex=new JSONObject();
            if(adds!=null){
                ex.put(KEY_ADDS,adds);
            }
            if(removes!=null){
                ex.put(KEY_REMOVES,removes);
            }
        }

    return ex;}

    private JSONObject getEvidencesUpdates(TestStep testStep) throws JSONException {
        Collection<Evidence> allEvidences=new ArrayList<Evidence>();
        Iterables.addAll(allEvidences,Iterables.concat(testStep.getEvidences(),testStep.getOldVersion().getEvidences()));
        JSONArray adds=new JSONArray();
        JSONArray removes=new JSONArray();
        JSONObject ex=null;

        for(Evidence e:allEvidences){
            if( Iterables.contains(testStep.getEvidences(),e) && !Iterables.contains(testStep.getOldVersion().getEvidences(),e))
            {
                adds.put(evGenerator.generate(e));
            }

            if( !Iterables.contains(testStep.getAttachments(),e) && Iterables.contains(testStep.getOldVersion().getAttachments(),e))
            {
                removes.put(evGenerator.generate(e));
            }
        }

        if(adds!=null || removes!=null){
            ex=new JSONObject();
            if(adds!=null){
                ex.put(KEY_ADDS,adds);
            }
            if(removes!=null){
                ex.put(KEY_REMOVES,removes);
            }
        }
       return ex;
}

    private JSONObject getDefectsUpdates(TestStep testStep) throws JSONException {
        Collection<Defect> allDefects=new ArrayList<Defect>();
        Iterables.addAll(allDefects,Iterables.concat(testStep.getDefects(),testStep.getOldVersion().getDefects()));
        JSONArray adds=new JSONArray();
        JSONArray removes=new JSONArray();
        JSONObject ex=null;

        for(Defect d:allDefects){
            if( Iterables.contains(testStep.getDefects(),d) && !Iterables.contains(testStep.getOldVersion().getDefects(),d))
            {
                adds.put(d.getKey());
            }

            if( !Iterables.contains(testStep.getDefects(),d) && Iterables.contains(testStep.getOldVersion().getDefects(),d))
            {
                removes.put(d.getKey());
            }
        }
        if(adds!=null || removes!=null){
            ex=new JSONObject();
            if(adds!=null){
                ex.put(KEY_ADDS,adds);
            }
            if(removes!=null){
                ex.put(KEY_REMOVES,removes);
            }
        }
        return ex;
    }
}
