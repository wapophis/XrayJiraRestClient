package es.cuatrogatos.jira.xray.rest.client.core.internal.json;

import com.atlassian.jira.rest.client.internal.json.JsonObjectParser;
import es.cuatrogatos.jira.xray.rest.client.api.domain.TestExecution;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * Created by lucho on 25/08/16.
 */
public class TestExecJsonParser implements JsonObjectParser<TestExecution> {
    public TestExecution parse(JSONObject jsonObject) throws JSONException {
        return null;
    }
}
