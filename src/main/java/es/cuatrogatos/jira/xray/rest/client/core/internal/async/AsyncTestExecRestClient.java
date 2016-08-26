package es.cuatrogatos.jira.xray.rest.client.core.internal.async;

import com.atlassian.httpclient.api.HttpClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.api.domain.User;
import com.atlassian.jira.rest.client.internal.async.AbstractAsynchronousRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousSearchRestClient;
import com.atlassian.jira.rest.client.internal.async.DisposableHttpClient;
import com.atlassian.jira.rest.client.internal.json.JsonObjectParser;
import com.atlassian.util.concurrent.Promise;

import com.google.common.base.Function;
import es.cuatrogatos.jira.xray.rest.client.api.TestExecutionRestClient;
import es.cuatrogatos.jira.xray.rest.client.api.domain.TestExecution;
import es.cuatrogatos.jira.xray.rest.client.core.internal.PluginConstants;
import es.cuatrogatos.jira.xray.rest.client.core.internal.json.TestArrayJsonParser;
import es.cuatrogatos.jira.xray.rest.client.core.internal.json.TestJsonParser;
import es.cuatrogatos.jira.xray.rest.client.core.internal.json.gen.TestExecJsonGenerator;
import es.cuatrogatos.jira.xray.rest.client.core.internal.json.gen.TestExecUpdateJsonGenerator;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.annotation.Nullable;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.ArrayList;

/**
 * Created by lucho on 25/08/16.
 */
public class AsyncTestExecRestClient extends AbstractAsynchronousRestClient implements TestExecutionRestClient {
    private URI baseUri;
    private final  TestArrayJsonParser testsParser=new TestArrayJsonParser();
    private final static TestExecUpdateJsonGenerator execUpdateGenerator=new TestExecUpdateJsonGenerator();


    private SearchRestClient searchRestClient=null;



    protected AsyncTestExecRestClient(HttpClient client) {
        super(client);
    }

    public AsyncTestExecRestClient(URI serverUri, DisposableHttpClient httpClient){
        super(httpClient);
        baseUri = UriBuilder.fromUri(serverUri).path("/rest/raven/{restVersion}/api/").build(PluginConstants.XRAY_REST_VERSION);
        searchRestClient=new AsynchronousSearchRestClient(UriBuilder.fromUri(serverUri).path("rest/api/latest/").build(new Object[0]),httpClient);
    }

    public Promise<Iterable<TestExecution.Test>> getTests(TestExecution testExecution) {
        UriBuilder uriBuilder=UriBuilder.fromUri(baseUri);
        uriBuilder.path("testexec").path("{isssue-key}").path("test");
        return this.getAndParse(uriBuilder.build(testExecution.getKey()),this.testsParser);
    }


    /**
     * Adds/Removes the test associated with this test execution
     * @param testExec
     * @return
     */
    public Promise<Void> setTests(TestExecution testExec) {
        UriBuilder uriBuilder=UriBuilder.fromUri(baseUri);
        uriBuilder.path("testexec").path("{isssue-key}").path("test");
        return this.postAndParse(uriBuilder.build(testExec.getKey()), testExec, execUpdateGenerator, new JsonObjectParser<Void>() {
            public Void parse(JSONObject jsonObject) throws JSONException {
                return null;
            }
        });
    }

    /**
     * Removes a test from the test execution identified by his test key
     * @param testExecKey
     * @param testKey
     * @return
     */
    public Promise<Void> removeTest(TestExecution testExecKey, TestExecution.Test testKey) {
        UriBuilder uriBuilder=UriBuilder.fromUri(baseUri);
        uriBuilder.path("testexec").path("{isssue-key}").path("test").path("{ŧest-key");
        return this.delete(uriBuilder.build(testExecKey.getKey(),testKey.getKey()));
    }


    /**
     * Method who queries the JQL testTestExecution() from the X-RAY plugin. Returning the test-executions related to this test identified by his key.
     * @param test Test containin the test key to search for.
     * @return A list of test execution promises which runs this test.
     */
    // TODO: MOVE THIS METHOD TO A BOUNDARY
    public Promise<Iterable<TestExecution>> get(TestExecution.Test test){
        if(test.getKey()==null){
            throw new IllegalArgumentException("KEY NOT SET");
        }
        Promise<SearchResult> searchResultPromise= searchRestClient.searchJql("issue in testTestExecutions(\""+test.getKey()+"\") ");
        return searchResultPromise.map(new Function<SearchResult, Iterable<TestExecution>>() {
            public Iterable<TestExecution> apply(@Nullable SearchResult searchResult) {
                ArrayList<TestExecution> testExceList=new ArrayList<TestExecution>();
                for(Issue testExcecIssue : searchResult.getIssues()){
                    testExceList.add(new TestExecution(testExcecIssue.getSelf(),testExcecIssue.getKey(),testExcecIssue.getId()));
                }
                return testExceList;
            }
        });
    }
}
