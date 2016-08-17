package es.cuatrogatos.jira.xray.rest.client.core.internal.async;

import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClient;
import com.atlassian.jira.rest.client.internal.async.DisposableHttpClient;
import es.cuatrogatos.jira.xray.rest.client.api.*;

import java.net.URI;

/**
 * Created by lucho on 11/08/16.
 */
public class AsyncXrayJiraRestClient extends AsynchronousJiraRestClient implements XrayJiraRestClient{
    private TestRestClient testClient=null;
    private TestExecutionRestClient testExecutionClient=null;
    private TestRunRestClient testRunClient=null;
    private TestSetRestClient testSetClient=null;

    public AsyncXrayJiraRestClient(URI serverUri, DisposableHttpClient httpClient) {
        super(serverUri, httpClient);
        this.testRunClient=new AsyncTestRunRestClient(serverUri,httpClient);
    }

    public TestRestClient getTestClient() {
        return testClient;
    }

    public TestExecutionRestClient getTestExecutionClient() {
        return testExecutionClient;
    }

    public TestRunRestClient getTestRunClient() {
        return testRunClient;
    }

    public TestSetRestClient getTestSetClient() {
        return testSetClient;
    }
}
