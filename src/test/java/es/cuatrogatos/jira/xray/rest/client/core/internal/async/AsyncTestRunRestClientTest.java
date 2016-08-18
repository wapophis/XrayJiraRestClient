package es.cuatrogatos.jira.xray.rest.client.core.internal.async;

import es.cuatrogatos.jira.xray.rest.client.api.domain.TestRun;
import org.codehaus.jettison.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.text.SimpleDateFormat;

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
    private final String TEST_EXEC_KEY="";
    private final String TEST_KEY="";
    private final long TEST_ID=1827;


    private JSONObject jsonObject=null;
    @Before
    public void setUp() throws Exception {
    restClient= (AsyncXrayJiraRestClient) factory.createWithBasicHttpAuthentication(new URI(uriLocation),username,password);
    jsonObject= new JSONObject("{\"id\":1827,\"status\":\"ABORTED\",\"assignee\":\"sandra.molina\",\"executedBy\":\"luis.martinez\",\"startedOn\":\"19/jul/16 10:51 AM\",\"finishedOn\":\"19/jul/16 11:53 AM\",\"defects\":[],\"evidences\":[],\"steps\":[]}");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetTestRunById() throws Exception {
            TestRun testRun=restClient.getTestRunClient().getTestRun(TEST_ID).claim();
        assertEquals(testRun.getStatus().name(),jsonObject.getString("status"));
        assertEquals(testRun.getExecutedBy(),jsonObject.getString("executedBy"));
        assertEquals(testRun.getAssignee(),jsonObject.getString("assignee"));
        assertEquals(testRun.getId().longValue(),jsonObject.getLong("id"));
        assertEquals(testRun.getStartedOn(),new SimpleDateFormat("dd/MMM/yy hh:mm aa").parse(jsonObject.getString("startedOn")));
        assertEquals(testRun.getFinishedOn(),new SimpleDateFormat("dd/MMM/yy hh:mm aa").parse(jsonObject.getString("finishedOn")));
    }

    @Test
    public void testGetTestRunByKeys() throws Exception {
       TestRun testRun=restClient.getTestRunClient().getTestRun(TEST_EXEC_KEY,TEST_KEY).claim();
        assertEquals(testRun.getStatus().name(),jsonObject.getString("status"));
        assertEquals(testRun.getExecutedBy(),jsonObject.getString("executedBy"));
        assertEquals(testRun.getAssignee(),jsonObject.getString("assignee"));
        assertEquals(testRun.getId().longValue(),jsonObject.getLong("id"));
        assertEquals(testRun.getStartedOn(),new SimpleDateFormat("dd/MMM/yy hh:mm aa").parse(jsonObject.getString("startedOn")));
        assertEquals(testRun.getFinishedOn(),new SimpleDateFormat("dd/MMM/yy hh:mm aa").parse(jsonObject.getString("finishedOn")));
    }

    @Test
    public void testGetTestRunsByTestKey() throws Exception{
        Iterable<TestRun> testRunIterable=restClient.getTestRunClient().getTestRuns(TEST_KEY).claim();
        for(TestRun testRun: testRunIterable){
            assertNotNull(testRun);
        }
    }

    @Test
    public void testUpdateTestRun() throws Exception {
        restClient.getTestRunClient().updateTestRun(new TestRun(new URI(""),"key",0L));
    }

    @Test
    public void testGetStatusById() throws Exception {
        TestRun.Status status=restClient.getTestRunClient().getStatus(TEST_ID).claim();
        assertEquals(status, TestRun.Status.ABORTED);
    }

    @Test
    public void testUpdateStatus() throws Exception {
        restClient.getTestRunClient().updateStatus(TEST_ID, TestRun.Status.ABORTED);
    }

    @Test
    public void testGetStatusByKey() throws Exception {
        restClient.getTestRunClient().getStatus(TEST_EXEC_KEY,TEST_KEY);
    }

    @Test
    public void testAddDefect() throws Exception {
        restClient.getTestRunClient().addDefect("");
    }

    @Test
    public void testGetDefects() throws Exception {
        restClient.getTestRunClient().getDefects(TEST_ID);
    }

    @Test
    public void testRemoveDefect() throws Exception {
        restClient.getTestRunClient().removeDefect(TEST_ID,"key");

    }

    @Test
    public void testGetEvidences() throws Exception {
        restClient.getTestRunClient().getEvidences(TEST_ID);
    }

    @Test
    public void testCreateEvidence() throws Exception {
        restClient.getTestRunClient().createEvidence(TEST_ID);
    }

    @Test
    public void testRemoveEvidence() throws Exception {
        restClient.getTestRunClient().removeEvidence(TEST_ID,"resourceName");

    }

    @Test
    public void testRemoveEvidence1() throws Exception {
        restClient.getTestRunClient().removeEvidence(TEST_ID,0L);

    }

    @Test
    public void testGetComment() throws Exception {
        restClient.getTestRunClient().getComment(TEST_ID);
    }

    @Test
    public void testUpdateComment() throws Exception {
        restClient.getTestRunClient().updateComment(TEST_ID,"");
    }

    @Test
    public void testGetExample() throws Exception {
        restClient.getTestRunClient().getExample(TEST_ID);
    }

    @Test
    public void testGetTestSteps() throws Exception {
        restClient.getTestRunClient().getTestSteps(TEST_ID);
    }
}