package es.cuatrogatos.jira.xray.rest.client.api;

import com.atlassian.jira.rest.client.api.JiraRestClient;

/**
 * Created by lucho on 11/08/16.
 */
public interface XrayJiraRestClient extends JiraRestClient {

    public TestRestClient getTestClient();
    public TestExecutionRestClient getTestExecutionClient();
    public TestRunRestClient getTestRunClient();
    public TestSetRestClient getTestSetClient();

}
