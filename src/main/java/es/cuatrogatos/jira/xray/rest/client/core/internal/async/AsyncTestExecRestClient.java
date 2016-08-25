package es.cuatrogatos.jira.xray.rest.client.core.internal.async;

import com.atlassian.httpclient.api.HttpClient;
import com.atlassian.jira.rest.client.internal.async.AbstractAsynchronousRestClient;
import com.atlassian.jira.rest.client.internal.async.DisposableHttpClient;
import com.atlassian.util.concurrent.Promise;

import es.cuatrogatos.jira.xray.rest.client.api.TestExecutionRestClient;
import es.cuatrogatos.jira.xray.rest.client.api.domain.Test;
import es.cuatrogatos.jira.xray.rest.client.api.domain.TestExecution;
import es.cuatrogatos.jira.xray.rest.client.core.internal.PluginConstants;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Created by lucho on 25/08/16.
 */
public class AsyncTestExecRestClient extends AbstractAsynchronousRestClient implements TestExecutionRestClient {
    private URI baseUri;

    protected AsyncTestExecRestClient(HttpClient client) {
        super(client);
    }

    public AsyncTestExecRestClient(URI serverUri, DisposableHttpClient httpClient){
        super(httpClient);
        baseUri = UriBuilder.fromUri(serverUri).path("/rest/raven/{restVersion}/api/").build(PluginConstants.XRAY_REST_VERSION);
    }

    public Promise<TestExecution> getTests(TestExecution key) {
        return null;
    }

    public Promise<Void> addTests(Test testKey) {
        return null;
    }

    public Promise<Void> removeTest(TestExecution testExecKey, Test testKey) {
        return null;
    }
}
