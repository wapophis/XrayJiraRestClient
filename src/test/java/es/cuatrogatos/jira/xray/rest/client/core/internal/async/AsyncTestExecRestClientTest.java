package es.cuatrogatos.jira.xray.rest.client.core.internal.async;

import es.cuatrogatos.jira.xray.rest.client.api.domain.TestExecution;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

import static org.junit.Assert.*;

/**
 * Created by lucho on 26/08/16.
 */
public class AsyncTestExecRestClientTest {

    private final String uriLocation=System.getenv("JIRA_URI");
    private final String username=System.getenv("JIRA_USER");;
    private final String password=System.getenv("JIRA_PASSWORD");;
    private final String TEST_EXEC_KEY="PBT-27";
    private final String TEST_KEY="PBT-2";
    private final long TEST_ID=1977;

    private final XrayRestAsyncRestClientFactory factory=new XrayRestAsyncRestClientFactory();
    private AsyncXrayJiraRestClient restClient;
    private TestExecution testExecution;

    @Before
    public void setUp() throws Exception {
        restClient= (AsyncXrayJiraRestClient) factory.createWithBasicHttpAuthentication(new URI(uriLocation),username,password);
        testExecution=new TestExecution(new URI(""),TEST_EXEC_KEY,1977l);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetTests() throws Exception {
        Iterable<TestExecution.Test>tests= restClient.getTestExecutionClient().getTests(testExecution).claim();
        for(TestExecution.Test t:tests)
        {
            assertNotNull(t);
        }
    }

    @Test
    public void testSetTests() throws Exception {

    }

    @Test
    public void testRemoveTest() throws Exception {

    }

    @Test
    public void testGet() throws Exception {

    }
}