package es.cuatrogatos.jira.xray.rest.client.core.internal.async;

import java.net.URI;
import java.util.ArrayList;

import javax.annotation.Nullable;
import javax.ws.rs.core.UriBuilder;

import com.atlassian.httpclient.api.HttpClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.async.AbstractAsynchronousRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousSearchRestClient;
import com.atlassian.jira.rest.client.internal.async.DisposableHttpClient;
import com.atlassian.jira.rest.client.internal.json.JsonObjectParser;
import com.atlassian.util.concurrent.Promise;

import com.google.common.base.Function;
import com.sun.istack.logging.Logger;
import es.cuatrogatos.jira.xray.rest.client.api.TestRunRestClient;
import es.cuatrogatos.jira.xray.rest.client.api.domain.*;
import es.cuatrogatos.jira.xray.rest.client.core.internal.PluginConstants;
import es.cuatrogatos.jira.xray.rest.client.core.internal.json.StatusJsonParser;
import es.cuatrogatos.jira.xray.rest.client.core.internal.json.TestRunJsonParser;
import es.cuatrogatos.jira.xray.rest.client.core.internal.json.gen.TestRunJsonGenerator;
import es.cuatrogatos.jira.xray.rest.client.core.internal.json.gen.TestRunUpdateJsonGenerator;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * Created by lucho on 11/08/16.
 */
public class AsyncTestRunRestClient extends AbstractAsynchronousRestClient implements TestRunRestClient {
    private URI baseUri;
    private final TestRunJsonParser testRunParser=new TestRunJsonParser();
    private final TestRunUpdateJsonGenerator testRunUpdateJsonGenerator=new TestRunUpdateJsonGenerator();
    private final StatusJsonParser  statusParser=new StatusJsonParser();
    private SearchRestClient searchRestClient=null;

    protected AsyncTestRunRestClient(HttpClient client) {
        super(client);
    }

    public AsyncTestRunRestClient(URI serverUri, DisposableHttpClient httpClient){
        super(httpClient);
        searchRestClient=new AsynchronousSearchRestClient(UriBuilder.fromUri(serverUri).path("rest/api/latest/").build(new Object[0]),httpClient);
        baseUri = UriBuilder.fromUri(serverUri).path("/rest/raven/{restVersion}/api/").build(PluginConstants.XRAY_REST_VERSION);
    }

    /**
     *
     * @param testExecKey
     * @param testKey
     * @return
     */
    public Promise<TestRun> getTestRun(String testExecKey, String testKey) {
        UriBuilder uriBuilder=UriBuilder.fromUri(baseUri);
        uriBuilder.path("testrun").queryParam("testExecIssueKey",testExecKey).queryParam("testIssueKey",testKey);
        return this.getAndParse(uriBuilder.build(new Object[0]),this.testRunParser);
    }

    /**
     *
     * @param testRunId
     * @return
     */
    public Promise<TestRun> getTestRun(Long testRunId) {
        UriBuilder uriBuilder=UriBuilder.fromUri(baseUri);
        uriBuilder.path("testrun").path("{id}");
        return this.getAndParse(uriBuilder.build(testRunId),this.testRunParser);
    }

    /**
     * Rest-API call to the /testrun/ with params to updates it's contents.
     * @param testRunInput
     * @return
     */
    public Promise<Void> updateTestRun(TestRun testRunInput) {
        UriBuilder uriBuilder=UriBuilder.fromUri(baseUri);
        uriBuilder.path("testrun").path("{id}");
        System.out.println(uriBuilder.build(testRunInput.getId()));
        TestRunJsonGenerator jsonGenerator=new TestRunJsonGenerator();
        try {
            System.out.println(testRunUpdateJsonGenerator.generate(testRunInput));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this.putAndParse(uriBuilder.build(testRunInput.getId()), testRunInput,testRunUpdateJsonGenerator, new JsonObjectParser<Void>() {
            public Void parse(JSONObject jsonObject) throws JSONException {
                System.out.println("CALLING PARSE ON UPDATE");
                return null;
            }
        });

    }

    /**
     * Query the testRuns using the "testTestExecutions" JQL defined by the XRAY plugin in JIRA.
     * @param testKey Issue jira key for the test.
     * @return a list of XRAY test-runs in which the Test identified by test-key is involved in
     */
    public Promise<Iterable<TestRun>> getTestRuns(final String testKey) {
        Promise<SearchResult> searchResultPromise= searchRestClient.searchJql("issue in testTestExecutions(\""+testKey+"\") ");
        return searchResultPromise.map(new Function<SearchResult,Iterable<TestRun>>(){
            public Iterable<TestRun> apply(@Nullable SearchResult searchResult) {
                ArrayList<TestRun> testRunsList=new ArrayList<TestRun>();
                for(Issue issue: searchResult.getIssues() ){
                    testRunsList.add(getTestRun(issue.getKey(),testKey).claim());
                }
                return testRunsList;
            }
        });
    }

    /**
     * Rest-API call to the /{testrun_id}/status return not json response so crash
     * http://jira.xpand-addons.com/browse/XRAY-964.
     * @param testRunId Internal xray id for the TestRun
     * @return Status from the test run
     */
    public Promise<TestRun.Status> getStatus(Long testRunId) {
        UriBuilder uriBuilder=UriBuilder.fromUri(baseUri);
        uriBuilder.path("testrun").path("{id}").path("/status/");
        return this.getAndParse(uriBuilder.build(testRunId),statusParser);
    }

    public Promise<TestRun.Status> updateStatus(Long testRunId, TestRun.Status statusInput) {
        throw new IllegalArgumentException("NOT IMPLEMENTED YET");
    }

    /**
     * Rest-API call to the /testrun? with params because the default api rest-call doesn't work on json format.
     * @param testExecKey Key from the test execution
     * @param testKey Key from the test which is involved in this test run
     * @return The status from test run
     */
    public Promise<TestRun.Status> getStatus(String testExecKey, String testKey) {
        return this.getTestRun(testExecKey,testKey).map(new Function<TestRun, TestRun.Status>() {
            public TestRun.Status apply(@Nullable TestRun testRun) {
                return testRun.getStatus();
            }
        });
    }

    public Promise<Defect> addDefect(String issueKey) {
        throw new IllegalArgumentException("NOT IMPLEMENTED YET");
    }

    public Promise<Iterable<Defect>> getDefects(Long testRunId) {
        throw new IllegalArgumentException("NOT IMPLEMENTED YET");
    }

    public Promise<Void> removeDefect(Long testRunId, String issueKey) {
        throw new IllegalArgumentException("NOT IMPLEMENTED YET");
    }

    public Promise<Iterable<Evidence>> getEvidences(Long testRunId) {
        throw new IllegalArgumentException("NOT IMPLEMENTED YET");
    }

    public Promise<Evidence> createEvidence(Long testRunId) {
        throw new IllegalArgumentException("NOT IMPLEMENTED YET");
    }

    public Promise<Void> removeEvidence(Long testRunId, String resourceName) {
        throw new IllegalArgumentException("NOT IMPLEMENTED YET");
    }

    public Promise<Void> removeEvidence(Long testRunId, Long evId) {
        throw new IllegalArgumentException("NOT IMPLEMENTED YET");
    }

    public Promise<Comment> getComment(Long testRunId) {
        throw new IllegalArgumentException("NOT IMPLEMENTED YET");
    }

    public Promise<Comment> updateComment(Long testRunId, String comment) {
        throw new IllegalArgumentException("NOT IMPLEMENTED YET");
    }

    public Promise<Example> getExample(Long testRunId) {
        throw new IllegalArgumentException("NOT IMPLEMENTED YET");
    }

    public Promise<Iterable<TestStep>> getTestSteps(Long testRunId) {
        throw new IllegalArgumentException("NOT IMPLEMENTED YET");
    }
}
