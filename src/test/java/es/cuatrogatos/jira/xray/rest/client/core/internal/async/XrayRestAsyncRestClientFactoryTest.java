package es.cuatrogatos.jira.xray.rest.client.core.internal.async;

import com.atlassian.httpclient.api.Request;
import com.atlassian.jira.rest.client.api.AuthenticationHandler;
import com.atlassian.jira.rest.client.auth.AnonymousAuthenticationHandler;
import com.atlassian.jira.rest.client.auth.BasicHttpAuthenticationHandler;
import com.atlassian.jira.rest.client.internal.async.DisposableHttpClient;
import com.atlassian.jira.rest.client.internal.json.JsonParseUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URI;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Created by lucho on 16/08/16.
 */
public class XrayRestAsyncRestClientFactoryTest {

    private AsyncXrayJiraRestClient client;
    private XrayRestAsyncRestClientFactory factoria;
    private  AuthenticationHandler authHandler;

    private final String uriLocation="";
    private final String username="";
    private final String password="";


    @Before
    public void setUp() throws Exception {
        factoria=new XrayRestAsyncRestClientFactory();

        if (username == null || username.isEmpty()) {
            authHandler = new AnonymousAuthenticationHandler();

        } else {
            authHandler = new BasicHttpAuthenticationHandler(username, password);
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreate() throws Exception {
        assertNotNull(factoria.create(new URI(uriLocation),authHandler));

    }

    @Test
    public void testCreateWithBasicHttpAuthentication() throws Exception {
        assertNotNull(factoria.createWithBasicHttpAuthentication(new URI(uriLocation),username,password));
    }

    @Test
    public void testCreate1() throws Exception {
        assertNotNull(factoria.create(new URI(uriLocation), new DisposableHttpClient() {
            public void destroy() throws Exception {

            }

            public Request newRequest() {
                return null;
            }

            public Request newRequest(URI uri) {
                return null;
            }

            public Request newRequest(String s) {
                return null;
            }

            public Request newRequest(URI uri, String s, String s1) {
                return null;
            }

            public Request newRequest(String s, String s1, String s2) {
                return null;
            }

            public void flushCacheByUriPattern(Pattern pattern) {

            }
        }));
    }
}