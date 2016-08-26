package es.cuatrogatos.jira.xray.rest.client.core.internal.json.gen;

import com.atlassian.jira.rest.client.internal.json.JsonParseUtil;
import com.google.common.collect.Iterables;
import es.cuatrogatos.jira.xray.rest.client.api.domain.TestExecution;
import org.codehaus.jettison.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by lucho on 26/08/16.
 */
public class TestExecUpdateJsonGeneratorTest {
    private final static TestExecUpdateJsonGenerator generator=new TestExecUpdateJsonGenerator();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGenerate() throws Exception {
        // TEST EMPTY OBJECT
        TestExecution testExecution=new TestExecution(JsonParseUtil.getSelfUri(new JSONObject("{\"self\":\"\"}")),"KEY-123",0L);
        JSONObject generated=generator.generate(testExecution);
        System.out.println("GENERATED:"+generated);

        //// TESTS ADDS
        ArrayList<TestExecution.Test> tests=new ArrayList<TestExecution.Test>();
        tests.add(new TestExecution.Test(JsonParseUtil.getSelfUri(new JSONObject("{\"self\":\"\"}")),"TESTKEY-123",1L));
        tests.add(new TestExecution.Test(JsonParseUtil.getSelfUri(new JSONObject("{\"self\":\"\"}")),"TESTKEY-124",2L));
        tests.add(new TestExecution.Test(JsonParseUtil.getSelfUri(new JSONObject("{\"self\":\"\"}")),"TESTKEY-125",3L));
        testExecution.setTests(tests);
        generated=generator.generate(testExecution);
        System.out.println("GENERATED:"+generated);

        /// TESTS REMOVES
        TestExecution mirrorTestExec=testExecution.clone();
        ArrayList<TestExecution.Test> tests1=new ArrayList<TestExecution.Test>();
        Iterables.addAll(tests1,mirrorTestExec.getTests());
        for(TestExecution.Test t:tests){
            tests1.remove(t);
        }
        mirrorTestExec.setTests(tests1);
        generated=generator.generate(mirrorTestExec);
        System.out.println("GENERATED:"+generated);
    }
}