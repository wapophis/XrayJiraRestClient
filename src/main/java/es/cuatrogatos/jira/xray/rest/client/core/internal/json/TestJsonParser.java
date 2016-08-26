package es.cuatrogatos.jira.xray.rest.client.core.internal.json;

import com.atlassian.jira.rest.client.internal.json.JsonObjectParser;
import com.atlassian.jira.rest.client.internal.json.JsonParseUtil;
import es.cuatrogatos.jira.xray.rest.client.api.domain.TestExecution;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.net.URI;

/**
 * Created by lucho on 25/08/16.
 */
public class TestJsonParser implements JsonObjectParser<TestExecution.Test> {

        public TestExecution.Test parse(JSONObject json) throws JSONException {
            json.put("self","");
            URI selfUri = JsonParseUtil.getSelfUri(json);
            String key = json.getString("key");
            Long id = Long.valueOf(json.getLong("id"));
            return new TestExecution.Test(selfUri, key, id);
        }

}
