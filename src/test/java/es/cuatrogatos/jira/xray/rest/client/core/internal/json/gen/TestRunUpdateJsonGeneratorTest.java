package es.cuatrogatos.jira.xray.rest.client.core.internal.json.gen;

import com.google.common.collect.Iterables;
import es.cuatrogatos.jira.xray.rest.client.api.domain.Comment;
import es.cuatrogatos.jira.xray.rest.client.api.domain.Defect;
import es.cuatrogatos.jira.xray.rest.client.api.domain.TestRun;
import es.cuatrogatos.jira.xray.rest.client.api.domain.TestStep;
import es.cuatrogatos.jira.xray.rest.client.core.internal.json.TestRunJsonParser;
import es.cuatrogatos.jira.xray.rest.client.core.internal.testDataBanks.TestRunDataBank;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.codehaus.jettison.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

/**
 * Created by lucho on 22/08/16.
 */
public class TestRunUpdateJsonGeneratorTest {
    private TestRun testRun;
    private final static TestRunJsonParser jsonParser=new TestRunJsonParser();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }
    @Test
    public void testSimpleGenerate() throws Exception {
        testRun=jsonParser.parse(new JSONObject(TestRunDataBank.JSON_MANUAL_EMPTY_TEST));
        TestRunUpdateJsonGenerator generator=new TestRunUpdateJsonGenerator();
        JSONObject generated=generator.generate(testRun);
        assertEquals(new JSONObject("{}").toString(),generated.toString());
    }
    @Test
    public void testSimpleUpdateStatus() throws Exception {
        testRun=jsonParser.parse(new JSONObject(TestRunDataBank.JSON_MANUAL_EMPTY_TEST));
        TestRunUpdateJsonGenerator generator=new TestRunUpdateJsonGenerator();
        testRun.setStatus(TestRun.Status.ABORTED);
        JSONObject generated=generator.generate(testRun);
        assertEquals(testRun.getStatus().name(),generated.get(TestRunJsonGenerator.KEY_STATUS));

        testRun.setStatus(TestRun.Status.EXECUTING);
        generated=generator.generate(testRun);
        assertEquals(testRun.getStatus().name(),generated.getString(TestRunJsonGenerator.KEY_STATUS));

        testRun.setStatus(TestRun.Status.FAIL);
        generated=generator.generate(testRun);
        assertEquals(testRun.getStatus().name(),generated.getString(TestRunJsonGenerator.KEY_STATUS));

        testRun.setStatus(TestRun.Status.PASS);
        generated=generator.generate(testRun);
        assertEquals(testRun.getStatus().name(),generated.getString(TestRunJsonGenerator.KEY_STATUS));
    }
    @Test
    public void testSimpleUpdateComment() throws Exception {
        testRun=jsonParser.parse(new JSONObject(TestRunDataBank.JSON_MANUAL_EMPTY_TEST));
        TestRunUpdateJsonGenerator generator=new TestRunUpdateJsonGenerator();
        testRun.setComment(new Comment("HOLA MUNDO","<b>HOLA MUNDO</b>"));
        JSONObject generated=generator.generate(testRun);
        assertEquals(testRun.getComment().getRaw(),generated.getString(TestRunJsonGenerator.KEY_COMMENT));
    }
    @Test
    public void testSimpleManualTestGenerate() throws Exception {
        testRun=jsonParser.parse(new JSONObject(TestRunDataBank.JSON_MANUAL_SIMPLE_TEST));
        TestRunUpdateJsonGenerator generator=new TestRunUpdateJsonGenerator();
        JSONObject generated=generator.generate(testRun);
        assertEquals(new JSONObject("{}").toString(),generated.toString());

    }
    @Test
    public void testSimpleManualTestUpdateAddDefects() throws Exception {
        testRun=jsonParser.parse(new JSONObject(TestRunDataBank.JSON_MANUAL_SIMPLE_TEST));
        TestRunUpdateJsonGenerator generator=new TestRunUpdateJsonGenerator();
        // ADD NEW DEFECT TO ARRAY
        Defect newDefect=new Defect("KEY-124");
        Defect newDefect1=new Defect("KEY-125");
        Defect newDefect2=new Defect("KEY-126");


        // ADDS DEFECTS ON AN EMPTY DEFECTS LIST
        Collection<Defect> defects=new ArrayList<Defect>();
        Iterables.addAll(defects,testRun.getDefects());
        defects.add(newDefect);
        defects.add(newDefect1);
        defects.add(newDefect2);
        testRun.setDefects(defects);
        JSONObject generated=generator.generate(testRun);
     //   Logger.getAnonymousLogger().warning("Generated:"+generated);
      //  assertEquals(testRun.getId().longValue(),generated.getLong(TestRunJsonGenerator.KEY_ID));
        assertEquals(new JSONObject("{\"add\":[\"KEY-124\",\"KEY-125\",\"KEY-126\"]}").toString(),generated.getJSONObject(TestRunJsonGenerator.KEY_DEFECTS).toString());

        // REMOVE ONE DEFECT EXISTING IN THE LIST AND RELOAD INTO TESTRU
        // TODO: TO ANOTHER TEST
        defects.remove(newDefect1);
        testRun.setDefects(defects);
        generated=generator.generate(testRun);
        assertEquals(new JSONObject("{\"add\":[\"KEY-124\",\"KEY-126\"]}").toString(),generated.getJSONObject(TestRunJsonGenerator.KEY_DEFECTS).toString());


        /// ADD DEFECTS IN A NON EMPTY DEFECT LIST

        Defect newDefect4=new Defect("KEY-127");
        Defect newDefect5=new Defect("KEY-128");
        Defect newDefect6=new Defect("KEY-129");
        Defect newDefect7=new Defect("KEY-130");

        Collection<Defect> defects1=new ArrayList<Defect>();
        Iterables.addAll(defects1,testRun.getDefects());

        defects1.add(newDefect4);
        defects1.add(newDefect5);
        defects1.add(newDefect6);
        defects1.add(newDefect7);
        testRun.setDefects(defects1);
        generated=generator.generate(testRun);
        assertEquals(new JSONObject("{\"add\":[\"KEY-124\",\"KEY-126\",\"KEY-127\",\"KEY-128\",\"KEY-129\",\"KEY-130\"]}").toString(),generated.getJSONObject(TestRunJsonGenerator.KEY_DEFECTS).toString());
    }
    @Test
    public void testSimpleManualRemoveDefects() throws Exception {
        testRun=jsonParser.parse(new JSONObject(TestRunDataBank.JSON_MANUAL_SIMPLE_TEST_WITH_DEFECTS));
        TestRunUpdateJsonGenerator generator=new TestRunUpdateJsonGenerator();
        Collection<Defect> defects=new ArrayList<Defect>();
        Iterables.addAll(defects,testRun.getDefects());
        defects.remove(defects.toArray()[0]);
        testRun.setDefects(defects);
        JSONObject generated=generator.generate(testRun);
 //       Logger.getAnonymousLogger().warning("Generated:"+generated);
//        assertEquals(testRun.getId().longValue(),generated.getLong(TestRunJsonGenerator.KEY_ID));
        assertEquals(new JSONObject("{\"remove\":[\"KEY-777\"]}").toString(),generated.getJSONObject(TestRunJsonGenerator.KEY_DEFECTS).toString());
    }
    @Test
    public void testSimpleManualAddRemoveDefects() throws Exception {
        testRun=jsonParser.parse(new JSONObject(TestRunDataBank.JSON_MANUAL_SIMPLE_TEST_WITH_DEFECTS));
        TestRunUpdateJsonGenerator generator=new TestRunUpdateJsonGenerator();
        Collection<Defect> defects=new ArrayList<Defect>();
        Iterables.addAll(defects,testRun.getDefects());
        defects.remove(defects.toArray()[0]);

        // ADD NEW DEFECT TO ARRAY
        Defect newDefect=new Defect("KEY-124");
        Defect newDefect1=new Defect("KEY-125");
        Defect newDefect2=new Defect("KEY-126");
        defects.add(newDefect);
        defects.add(newDefect1);
        defects.add(newDefect2);

        testRun.setDefects(defects);
        JSONObject generated=generator.generate(testRun);
 //       Logger.getAnonymousLogger().warning("Generated:"+generated);
//        assertEquals(testRun.getId().longValue(),generated.getLong(TestRunJsonGenerator.KEY_ID));
        assertEquals(new JSONObject("{\"add\":[\"KEY-124\",\"KEY-125\",\"KEY-126\"],\"remove\":[\"KEY-777\"]}").toString(),generated.getJSONObject(TestRunJsonGenerator.KEY_DEFECTS).toString());
    }
    @Test
    public void testSimpleManualUpdateTestSteps() throws  Exception{
        testRun=jsonParser.parse(new JSONObject(TestRunDataBank.JSON_MANUAL_SIMPLE_TEST_WITH_DEFECTS));
        TestRunUpdateJsonGenerator generator=new TestRunUpdateJsonGenerator();
        Collection<TestStep> steps=new ArrayList<TestStep>();
        Iterables.addAll(steps,testRun.getSteps());
        for(TestStep step:steps){
            step.setStatus(TestStep.Status.EXECUTING);
        }
        testRun.setSteps(steps);
        JSONObject generated=generator.generate(testRun);
       // System.out.println("GENERATED: "+generated);
        assertEquals(new JSONObject("{\"steps\":[{\"id\":7388,\"status\":\"EXECUTING\",\"attachments\":{\"add\":[],\"remove\":[]},\"evidences\":{\"add\":[],\"remove\":[]},\"defects\":{\"add\":[],\"remove\":[]}},{\"id\":7389,\"status\":\"EXECUTING\",\"attachments\":{\"add\":[],\"remove\":[]},\"evidences\":{\"add\":[],\"remove\":[]},\"defects\":{\"add\":[],\"remove\":[]}}]}\n").toString()
                ,generated.toString());
    }

    @Test
    public void testSimpleManualUpdateTestStepsAddDefects() throws  Exception{
        testRun=jsonParser.parse(new JSONObject(TestRunDataBank.JSON_MANUAL_SIMPLE_TEST_WITH_DEFECTS));
        TestRunUpdateJsonGenerator generator=new TestRunUpdateJsonGenerator();
        Collection<TestStep> steps=new ArrayList<TestStep>();
        Iterables.addAll(steps,testRun.getSteps());
        for(TestStep step:steps){
            Collection<Defect> defects=new ArrayList<Defect>();
            Iterables.addAll(defects,step.getDefects());
            defects.add(new Defect("NEWDEFECT-1"));
            defects.add(new Defect("NEWDEFECT-2"));
            defects.add(new Defect("NEWDEFECT-3"));
            step.setDefects(defects);
        }
        testRun.setSteps(steps);
        JSONObject generated=generator.generate(testRun);
      //  System.out.println("GENERATED: "+generated);
        assertEquals(new JSONObject(" {\"steps\":[{\"id\":7388,\"attachments\":{\"add\":[],\"remove\":[]},\"evidences\":{\"add\":[],\"remove\":[]},\"defects\":{\"add\":[\"NEWDEFECT-1\",\"NEWDEFECT-2\",\"NEWDEFECT-3\"],\"remove\":[]}},{\"id\":7389,\"attachments\":{\"add\":[],\"remove\":[]},\"evidences\":{\"add\":[],\"remove\":[]},\"defects\":{\"add\":[\"NEWDEFECT-1\",\"NEWDEFECT-2\",\"NEWDEFECT-3\"],\"remove\":[]}}]}\n").toString()
                ,generated.toString());
    }

    @Test
    public void testSimpleManualUpdateTestStepsRemoveDefects() throws  Exception{
        testRun=jsonParser.parse(new JSONObject(TestRunDataBank.JSON_COMPLEX_MANUAL_TEST));
        TestRunUpdateJsonGenerator generator=new TestRunUpdateJsonGenerator();
        Collection<TestStep> steps=new ArrayList<TestStep>();
        Iterables.addAll(steps,testRun.getSteps());
        for(TestStep step:steps){
            Collection<Defect> defects=new ArrayList<Defect>();
         //   Iterables.addAll(defects,step.getDefects());
            defects.add(new Defect("NEWDEFECT-1"));
            defects.add(new Defect("NEWDEFECT-2"));
            defects.add(new Defect("NEWDEFECT-3"));
            step.setDefects(defects);
        }
        testRun.setSteps(steps);
        JSONObject generated=generator.generate(testRun);
     //   System.out.println("GENERATED: "+generated);
        assertEquals(new JSONObject(" {\"steps\":[{\"id\":7278,\"attachments\":{\"add\":[],\"remove\":[]},\"evidences\":{\"add\":[],\"remove\":[]},\"defects\":{\"add\":[\"NEWDEFECT-1\",\"NEWDEFECT-2\",\"NEWDEFECT-3\"],\"remove\":[]}},{\"id\":7279,\"attachments\":{\"add\":[],\"remove\":[]},\"evidences\":{\"add\":[],\"remove\":[]},\"defects\":{\"add\":[\"NEWDEFECT-1\",\"NEWDEFECT-2\",\"NEWDEFECT-3\"],\"remove\":[\"PBT-28\"]}},{\"id\":7280,\"attachments\":{\"add\":[],\"remove\":[]},\"evidences\":{\"add\":[],\"remove\":[]},\"defects\":{\"add\":[\"NEWDEFECT-1\",\"NEWDEFECT-2\",\"NEWDEFECT-3\"],\"remove\":[]}}]}\n").toString()
                ,generated.toString());
    }
}