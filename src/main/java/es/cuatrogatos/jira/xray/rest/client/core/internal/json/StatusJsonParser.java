package es.cuatrogatos.jira.xray.rest.client.core.internal.json;

import com.atlassian.jira.rest.client.internal.json.JsonObjectParser;
import es.cuatrogatos.jira.xray.rest.client.api.domain.Defect;
import es.cuatrogatos.jira.xray.rest.client.api.domain.TestRun;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * Created by lucho on 17/08/16.
 */
public class StatusJsonParser implements JsonObjectParser<TestRun.Status> {
    public TestRun.Status parse(JSONObject jsonObject) throws JSONException {
        return null;
    }
}
