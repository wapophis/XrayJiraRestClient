package es.cuatrogatos.jira.xray.rest.client.core.internal.async;

import com.atlassian.httpclient.api.HttpClient;
import com.atlassian.httpclient.api.Response;
import com.atlassian.httpclient.api.ResponsePromise;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.async.AbstractAsynchronousRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousSearchRestClient;
import com.atlassian.jira.rest.client.internal.async.DisposableHttpClient;
import com.atlassian.jira.rest.client.internal.json.JsonArrayParser;
import com.atlassian.jira.rest.client.internal.json.JsonObjectParser;
import com.atlassian.jira.rest.client.internal.json.JsonParser;
import com.atlassian.util.concurrent.Promise;

import com.google.common.base.Function;
import es.cuatrogatos.jira.xray.rest.client.api.TestExecutionRestClient;
import es.cuatrogatos.jira.xray.rest.client.api.domain.TestExecution;
import es.cuatrogatos.jira.xray.rest.client.api.domain.TestRun;
import es.cuatrogatos.jira.xray.rest.client.core.internal.PluginConstants;
import es.cuatrogatos.jira.xray.rest.client.core.internal.json.TestJsonParser;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.annotation.Nullable;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

/**
 * Created by lucho on 25/08/16.
 */
public class AsyncTestExecRestClient extends AbstractAsynchronousRestClient implements TestExecutionRestClient {
    private URI baseUri;
    private final static TestJsonParser testParser=new TestJsonParser();

    private SearchRestClient searchRestClient=null;



    protected AsyncTestExecRestClient(HttpClient client) {
        super(client);
    }

    public AsyncTestExecRestClient(URI serverUri, DisposableHttpClient httpClient){
        super(httpClient);
        baseUri = UriBuilder.fromUri(serverUri).path("/rest/raven/{restVersion}/api/").build(PluginConstants.XRAY_REST_VERSION);
        searchRestClient=new AsynchronousSearchRestClient(UriBuilder.fromUri(serverUri).path("rest/api/latest/").build(new Object[0]),httpClient);
    }

    public Promise<TestExecution.Test> getTests(TestExecution testExecution) {
        UriBuilder uriBuilder=UriBuilder.fromUri(baseUri);
        uriBuilder.path("testexec").path("{isssue-key}");
        return this.getAndParse(uriBuilder.build(testExecution.getKey()),this.testParser);
    }



    public Promise<Void> addTests(TestExecution.Test ...testKey) {
        UriBuilder uriBuilder=UriBuilder.fromUri(baseUri);
        uriBuilder.path("testexec").path("{isssue-key}");
        return null;
//        return this.post(uriBuilder.build(testKey.getKey()),this.testParser);
    }

    public Promise<Void> removeTest(TestExecution testExecKey, TestExecution.Test testKey) {
        UriBuilder uriBuilder=UriBuilder.fromUri(baseUri);
        uriBuilder.path("testexec").path("{isssue-key}");
        return null;
//        return this.getAndParse(uriBuilder.build(testExecution.getKey()),this.testParser);
    }



}
