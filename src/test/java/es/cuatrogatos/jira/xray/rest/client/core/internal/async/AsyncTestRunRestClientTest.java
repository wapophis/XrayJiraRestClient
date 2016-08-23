package es.cuatrogatos.jira.xray.rest.client.core.internal.async;

import com.atlassian.jira.rest.client.api.RestClientException;
import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import es.cuatrogatos.jira.xray.rest.client.api.domain.Comment;
import es.cuatrogatos.jira.xray.rest.client.api.domain.Defect;
import es.cuatrogatos.jira.xray.rest.client.api.domain.TestRun;
import org.codehaus.jettison.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by lucho on 17/08/16.
 */
public class AsyncTestRunRestClientTest {
    private final XrayRestAsyncRestClientFactory factory=new XrayRestAsyncRestClientFactory();
    private AsyncXrayJiraRestClient restClient;

    private final String uriLocation=System.getenv("JIRA_URI");
    private final String username=System.getenv("JIRA_USER");;
    private final String password=System.getenv("JIRA_PASSWORD");;
    private final String TEST_EXEC_KEY="PBT-27";
    private final String TEST_KEY="PBT-2";
    private final long TEST_ID=1977;


    private JSONObject jsonObject=null;
    @Before
    public void setUp() throws Exception {
        System.out.println("JIRA_URI:"+uriLocation);
        restClient= (AsyncXrayJiraRestClient) factory.createWithBasicHttpAuthentication(new URI(uriLocation),username,password);
        jsonObject= new JSONObject("{\n" +
                "  \"id\" : 1977,\n" +
                "  \"status\" : \"FAIL\",\n" +
                "  \"executedBy\" : \"rgarcial\",\n" +
                "  \"startedOn\" : \"08/ago/16 10:24 AM\",\n" +
                "  \"finishedOn\" : \"viernes 1:19 PM\",\n" +
                "  \"defects\" : [\n" +
                "    {\n" +
                "      \"id\" : 16414,\n" +
                "      \"key\" : \"PBT-28\",\n" +
                "      \"summary\" : \"Especificar los detalles del requisito\",\n" +
                "      \"status\" : \"Open\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"evidences\" : [\n" +
                "    {\n" +
                "      \"id\" : 2,\n" +
                "      \"fileName\" : \"2012-04-14_130335.png\",\n" +
                "      \"fileSize\" : \"19 kB\",\n" +
                "      \"created\" : \"Hoy 7:16 PM\",\n" +
                "      \"author\" : \"luis.martinez\",\n" +
                "      \"fileURL\" : \"https://sasjira.services.connectis.es/jira/plugins/servlet/raven/attachment/2/2012-04-14_130335.png\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"comment\" : \"UN COMENTARIO DE PRUEBA\",\n" +
                "  \"steps\" : [\n" +
                "    {\n" +
                "      \"id\" : 7278,\n" +
                "      \"index\" : 1,\n" +
                "      \"step\" : {\n" +
                "        \"raw\" : \"CR-VC-001 ¿Se ha recogido toda la información relativa a los requisitos?\",\n" +
                "        \"rendered\" : \"<p>CR-VC-001 ¿Se ha recogido toda la información relativa a los requisitos?</p>\"\n" +
                "      },\n" +
                "      \"data\" : {\n" +
                "        \"rendered\" : \"\"\n" +
                "      },\n" +
                "      \"result\" : {\n" +
                "        \"raw\" : \"Indicación de todos los campos requeridos para cada requisito.\\n•\\tCódigo único e invariable.\\n•\\tNombre.\\n•\\tVersión.\\n•\\tFecha.\\n•\\tOrigen del requisito.\\n•\\tPrioridad.\\n•\\tDescripción.\\n•\\tSolución propuesta.\\n\",\n" +
                "        \"rendered\" : \"<p>Indicación de todos los campos requeridos para cada requisito.<br/>•Código único e invariable.<br/>•Nombre.<br/>•Versión.<br/>•Fecha.<br/>•Origen del requisito.<br/>•Prioridad.<br/>•Descripción.<br/>•Solución propuesta.</p>\"\n" +
                "      },\n" +
                "      \"attachments\" : [ ],\n" +
                "      \"status\" : \"PASS\",\n" +
                "      \"comment\" : {\n" +
                "        \"raw\" : \"OK\",\n" +
                "        \"rendered\" : \"<p>OK</p>\"\n" +
                "      },\n" +
                "      \"defects\" : [ ],\n" +
                "      \"evidences\" : [ ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\" : 7279,\n" +
                "      \"index\" : 2,\n" +
                "      \"step\" : {\n" +
                "        \"raw\" : \"CR-VC-002 ¿Se han descrito los requisitos claramente?\\n¿Están definidos los requisitos o funciones en términos no ambiguos?\\n\",\n" +
                "        \"rendered\" : \"<p>CR-VC-002 ¿Se han descrito los requisitos claramente?<br/>¿Están definidos los requisitos o funciones en términos no ambiguos?</p>\"\n" +
                "      },\n" +
                "      \"data\" : {\n" +
                "        \"rendered\" : \"\"\n" +
                "      },\n" +
                "      \"result\" : {\n" +
                "        \"raw\" : \"Los requisitos deben estar descritos de forma clara y comprensible para personal técnico, que no conoce el sistema, y usuarios, que no conocen el vocabulario técnico.\\nInexistencia de ambigüedades en la redacción.\\n\",\n" +
                "        \"rendered\" : \"<p>Los requisitos deben estar descritos de forma clara y comprensible para personal técnico, que no conoce el sistema, y usuarios, que no conocen el vocabulario técnico.<br/>Inexistencia de ambigüedades en la redacción.</p>\"\n" +
                "      },\n" +
                "      \"attachments\" : [ ],\n" +
                "      \"status\" : \"FAIL\",\n" +
                "      \"comment\" : {\n" +
                "        \"raw\" : \"Requisito incompleto\",\n" +
                "        \"rendered\" : \"<p>Requisito incompleto</p>\"\n" +
                "      },\n" +
                "      \"defects\" : [\n" +
                "        {\n" +
                "          \"id\" : 16414,\n" +
                "          \"key\" : \"PBT-28\",\n" +
                "          \"summary\" : \"Especificar los detalles del requisito\",\n" +
                "          \"status\" : \"Open\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"evidences\" : [ ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\" : 7280,\n" +
                "      \"index\" : 3,\n" +
                "      \"step\" : {\n" +
                "        \"raw\" : \"CR-VC-003 ¿Son consistentes los requisitos?\",\n" +
                "        \"rendered\" : \"<p>CR-VC-003 ¿Son consistentes los requisitos?</p>\"\n" +
                "      },\n" +
                "      \"data\" : {\n" +
                "        \"rendered\" : \"\"\n" +
                "      },\n" +
                "      \"result\" : {\n" +
                "        \"raw\" : \"Inexistencia de contradicciones entre requisitos.\",\n" +
                "        \"rendered\" : \"<p>Inexistencia de contradicciones entre requisitos.</p>\"\n" +
                "      },\n" +
                "      \"attachments\" : [ ],\n" +
                "      \"status\" : \"EXECUTING\",\n" +
                "      \"comment\" : {\n" +
                "        \"rendered\" : \"\"\n" +
                "      },\n" +
                "      \"defects\" : [ ],\n" +
                "      \"evidences\" : [ ]\n" +
                "    }\n" +
                "  ]\n" +
                "}");
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

        TestRun testRun = restClient.getTestRunClient().getTestRun(this.TEST_EXEC_KEY, this.TEST_KEY).claim();
        // UPDATE STATUS
        testRun.setStatus(TestRun.Status.EXECUTING);
            try {
                restClient.getTestRunClient().updateTestRun(testRun).claim();
            } catch (RestClientException e) { // TODO: THE SERVER RETURN A 200 CODE AND A EMPTY RESPONSE, SO WE MUST EXTEND ABSTRACTRESTCLIENTO TO DEAL WITH IN PUT OPERATIONS
            if(!e.getStatusCode().equals(Optional.absent()))
                throw e;
            }
        TestRun updatedTestRunRefreshed=restClient.getTestRunClient().getTestRun(testRun.getId()).claim();
        assertEquals(testRun.getStatus(),updatedTestRunRefreshed.getStatus());
        // UPDATE STATUS AND COMMENT
        testRun = restClient.getTestRunClient().getTestRun(this.TEST_EXEC_KEY, this.TEST_KEY).claim();
        testRun.setStatus(TestRun.Status.ABORTED);
        testRun.setComment(new Comment("THIS IS A COMMENT FROM THE XRAYJIRA RESTCLIENT LIBRARY FOR JAVA","THIS IS A COMMENT FROM THE <blink>XRAYJIRA RESCLIENT</blink> LIBRARY FOR JAVA"));
        try{
            restClient.getTestRunClient().updateTestRun(testRun).claim();
        } catch (RestClientException e1) { // TODO: THE SERVER RETURN A 200 CODE AND A EMPTY RESPONSE, SO WE MUST EXTEND ABSTRACTRESTCLIENTO TO DEAL WITH IN PUT OPERATIONS
            if (!e1.getStatusCode().equals(Optional.absent()))
                throw e1;
        }
        updatedTestRunRefreshed=restClient.getTestRunClient().getTestRun(testRun.getId()).claim();
        assertEquals(testRun.getStatus(),updatedTestRunRefreshed.getStatus());
        assertEquals(testRun.getComment().getRaw(),updatedTestRunRefreshed.getComment().getRaw());

        // UPDATE STATUS,COMMENTS AND ADD DEFECTS}
        testRun = restClient.getTestRunClient().getTestRun(this.TEST_EXEC_KEY, this.TEST_KEY).claim();
        testRun.setStatus(TestRun.Status.FAIL);
        testRun.setComment(new Comment("THIS IS A COMMENT FROM THE XRAYJIRA RESTCLIENT LIBRARY FOR JAVA.\n FOUND NEW DEFECTS","THIS IS A COMMENT FROM THE <blink>XRAYJIRA RESCLIENT</blink> LIBRARY FOR JAVA. \n" +
                " FOUND NEW DEFECTS"));
        Defect defect=new Defect("PBT-29");
        Collection<Defect> defects=new ArrayList<Defect>();
        Iterables.addAll(defects,testRun.getDefects());
        defects.add(defect);
        testRun.setDefects(defects);
        try{
            restClient.getTestRunClient().updateTestRun(testRun).claim();
        } catch (RestClientException e1) { // TODO: THE SERVER RETURN A 200 CODE AND A EMPTY RESPONSE, SO WE MUST EXTEND ABSTRACTRESTCLIENTO TO DEAL WITH IN PUT OPERATIONS
            if (!e1.getStatusCode().equals(Optional.absent())) {
                throw e1;
            }
        }
        updatedTestRunRefreshed=restClient.getTestRunClient().getTestRun(testRun.getId()).claim();
        assertEquals(testRun.getStatus(),updatedTestRunRefreshed.getStatus());
        assertEquals(testRun.getComment().getRaw(),updatedTestRunRefreshed.getComment().getRaw());
        // TODO: Assert the addition


        // UPDATE STATUS,COMMENTS AND REMOVE DEFECTS}
        testRun = restClient.getTestRunClient().getTestRun(this.TEST_EXEC_KEY, this.TEST_KEY).claim();
        testRun.setStatus(TestRun.Status.PASS);
        testRun.setComment(new Comment("THIS IS A COMMENT FROM THE XRAYJIRA RESTCLIENT LIBRARY FOR JAVA.\n OH IT WAS A MISTAKE :D","THIS IS A COMMENT FROM THE <blink>XRAYJIRA RESCLIENT</blink> LIBRARY FOR JAVA. \n" +
                " OHH IT WAS A MISTAKE"));
        defect=new Defect("PBT-29");
        defects=new ArrayList<Defect>();
        Iterables.addAll(defects,testRun.getDefects());
        for(Defect def: testRun.getDefects()){
            defects.remove(def);
        }

        testRun.setDefects(defects);
        try{
            restClient.getTestRunClient().updateTestRun(testRun).claim();
        } catch (RestClientException e1) { // TODO: THE SERVER RETURN A 200 CODE AND A EMPTY RESPONSE, SO WE MUST EXTEND ABSTRACTRESTCLIENTO TO DEAL WITH IN PUT OPERATIONS
            if (!e1.getStatusCode().equals(Optional.absent())) {
                throw e1;
            }
        }
        updatedTestRunRefreshed=restClient.getTestRunClient().getTestRun(testRun.getId()).claim();
        assertEquals(testRun.getStatus(),updatedTestRunRefreshed.getStatus());
        assertEquals(testRun.getComment().getRaw(),updatedTestRunRefreshed.getComment().getRaw());
        // TODO: Assert the remove
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