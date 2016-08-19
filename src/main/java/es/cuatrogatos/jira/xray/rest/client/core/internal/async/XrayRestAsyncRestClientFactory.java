package es.cuatrogatos.jira.xray.rest.client.core.internal.async;

import com.atlassian.httpclient.api.HttpClient;
import com.atlassian.jira.rest.client.api.AuthenticationHandler;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.auth.BasicHttpAuthenticationHandler;
import com.atlassian.jira.rest.client.internal.async.AsynchronousHttpClientFactory;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.jira.rest.client.internal.async.DisposableHttpClient;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by lucho on 16/08/16.
 */
public class XrayRestAsyncRestClientFactory extends AsynchronousJiraRestClientFactory {


    public JiraRestClient create(URI serverUri, AuthenticationHandler authenticationHandler) {
        DisposableHttpClient httpClient = (new AsynchronousHttpClientFactory()).createClient(serverUri, authenticationHandler);

        return new AsyncXrayJiraRestClient(serverUri, httpClient);
    }

    public JiraRestClient createWithBasicHttpAuthentication(URI serverUri, String username, String password) {
        return this.create(serverUri, (AuthenticationHandler)(new BasicHttpAuthenticationHandler(username, password)));
    }

    public JiraRestClient create(URI serverUri, HttpClient httpClient) {
        DisposableHttpClient disposableHttpClient = (new AsynchronousHttpClientFactory()).createClient(httpClient);
        return new AsyncXrayJiraRestClient(serverUri, disposableHttpClient);
    }


}
