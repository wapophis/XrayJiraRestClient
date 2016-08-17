package es.cuatrogatos.jira.xray.rest.client.core.internal.async;

import com.atlassian.httpclient.api.HttpClient;
import com.atlassian.jira.rest.client.internal.async.AbstractAsynchronousRestClient;
import com.atlassian.jira.rest.client.internal.async.DisposableHttpClient;
import com.atlassian.util.concurrent.Promise;
import es.cuatrogatos.jira.xray.rest.client.api.TestRunRestClient;
import es.cuatrogatos.jira.xray.rest.client.api.domain.*;
import es.cuatrogatos.jira.xray.rest.client.core.internal.json.TestRunJsonParser;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by lucho on 11/08/16.
 */
public class AsyncTestRunRestClient extends AbstractAsynchronousRestClient implements TestRunRestClient {
    private URI baseUri;
    private final TestRunJsonParser testRunParser=new TestRunJsonParser();

    public AsyncTestRunRestClient(URI serverUri, DisposableHttpClient httpClient){
        super(httpClient);
        baseUri = UriBuilder.fromUri(serverUri).path("/rest/raven/1.0/api/").build(new Object[0]);

    }

    protected AsyncTestRunRestClient(HttpClient client) {
        super(client);
    }

    public Promise<TestRun> getTestRun(String testExecKey, String testKey) {
        UriBuilder uriBuilder=UriBuilder.fromUri(baseUri);
        uriBuilder.path("testrun").queryParam("testExecIssueKey",testExecKey).queryParam("testIssueKey",testKey);
        return this.getAndParse(uriBuilder.build(new Object[0]),this.testRunParser);
    }

	public Promise<TestRun> getTestRun(Long testRunId) {
		UriBuilder uriBuilder = UriBuilder.fromUri(baseUri);
		uriBuilder.path("testrun").path(String.valueOf(testRunId));
		return this.getAndParse(uriBuilder.build(new Object[0]), this.testRunParser);
	}

    public Promise<TestRun> updateTestRun(TestRun testRunInput) {
        throw new IllegalArgumentException("NOT IMPLEMENTED YET");
    }

    public Promise<TestRun.Status> getStatus(Long testRunId) {
        throw new IllegalArgumentException("NOT IMPLEMENTED YET");
    }

    public Promise<TestRun.Status> updateStatus(Long testRunId, TestRun.Status statusInput) {
        throw new IllegalArgumentException("NOT IMPLEMENTED YET");
    }

    public Promise<TestRun.Status> getStatus(String testExecKey, String testKey) {
        throw new IllegalArgumentException("NOT IMPLEMENTED YET");
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
