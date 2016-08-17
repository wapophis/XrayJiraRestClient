package es.cuatrogatos.jira.xray.rest.client.core.internal.async;

import es.cuatrogatos.jira.xray.rest.client.api.domain.TestRun;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

import static org.junit.Assert.*;

/**
 * Created by lucho on 17/08/16.
 */
public class AsyncTestRunRestClientTest {
    private final XrayRestAsyncRestClientFactory factory=new XrayRestAsyncRestClientFactory();
    private AsyncXrayJiraRestClient restClient;

    private final String uriLocation="";
    private final String username="";
    private final String password="";

    @Before
    public void setUp() throws Exception {
    restClient= (AsyncXrayJiraRestClient) factory.createWithBasicHttpAuthentication(new URI(uriLocation),username,password);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetTestRun() throws Exception {

    }

    @Test
    public void testGetTestRun1() throws Exception {
       TestRun testRun=restClient.getTestRunClient().getTestRun("DYPIT-1728","DYPIT-1608").claim();
       assertEquals(testRun.getStatus().name(),"ABORTED");

    }
    
    @Test
    public void testGetTestRun2() throws Exception {
       TestRun testRun=restClient.getTestRunClient().getTestRun(1973L).claim();
       assertEquals(testRun.getStatus().name(),"TODO");
    }

    @Test
    public void testUpdateTestRun() throws Exception {

    }

    @Test
    public void testGetStatus() throws Exception {

    }

    @Test
    public void testUpdateStatus() throws Exception {

    }

    @Test
    public void testGetStatus1() throws Exception {

    }

    @Test
    public void testAddDefect() throws Exception {

    }

    @Test
    public void testGetDefects() throws Exception {

    }

    @Test
    public void testRemoveDefect() throws Exception {

    }

    @Test
    public void testGetEvidences() throws Exception {

    }

    @Test
    public void testCreateEvidence() throws Exception {

    }

    @Test
    public void testRemoveEvidence() throws Exception {

    }

    @Test
    public void testRemoveEvidence1() throws Exception {

    }

    @Test
    public void testGetComment() throws Exception {

    }

    @Test
    public void testUpdateComment() throws Exception {

    }

    @Test
    public void testGetExample() throws Exception {

    }

    @Test
    public void testGetTestSteps() throws Exception {

    }
}