package es.cuatrogatos.jira.xray.rest.client.core.internal.json.gen;

import es.cuatrogatos.jira.xray.rest.client.api.domain.TestRun;
import es.cuatrogatos.jira.xray.rest.client.core.internal.json.TestRunJsonParser;
import org.apache.http.client.utils.URIBuilder;
import org.codehaus.jettison.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * Created by lucho on 22/08/16.
 */
public class TestRunJsonGeneratorTest {
    private TestRun testRun;
    private final static TestRunJsonParser jsonParser=new TestRunJsonParser();
    private String[] data;
    @Before
    public void setUp() throws Exception {
        data=new String[]{"{\n" +
                "   \"id\":371,\n" +
                "   \"status\":\"TODO\",\n" +
                "   \"executedBy\":\"admin\",\n" +
                "   \"startedOn\":\"22/Jun/15 11:25 AM\",\n" +
                "   \"defects\":[\n" +
                "      {\n" +
                "         \"id\":15017,\n" +
                "         \"key\":\"test-114\",\n" +
                "         \"summary\":\"summary 1\",\n" +
                "         \"status\":\"Open\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"id\":15018,\n" +
                "         \"key\":\"test-115\",\n" +
                "         \"summary\":\"summary 2\",\n" +
                "         \"status\":\"Open\"\n" +
                "      }\n" +
                "   ],\n" +
                "   \"evidences\":[\n" +
                "      {\n" +
                "         \"id\":313,\n" +
                "         \"fileName\":\"stuff.txt\",\n" +
                "         \"fileSize\":\"0,0 kB\",\n" +
                "         \"created\":\"Yesterday 4:22 PM\",\n" +
                "         \"author\":\"admin\",\n" +
                "         \"fileURL\":\"http://lenovo-pc:2990/jira/plugins/servlet/raven/attachment/313/stuff.txt\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"id\":314,\n" +
                "         \"fileName\":\"stuff2.txt\",\n" +
                "         \"fileSize\":\"0,0 kB\",\n" +
                "         \"created\":\"Yesterday 4:22 PM\",\n" +
                "         \"author\":\"admin\",\n" +
                "         \"fileURL\":\"http://lenovo-pc:2990/jira/plugins/servlet/raven/attachment/314/stuff2.txt\"\n" +
                "      }\n" +
                "   ],\n" +
                "   \"comment\":\"new comment\",\n" +
                "   \"scenarioOutline\":\"Given I have entered <input_1> into the calculator\\r\\nAnd I have entered <input_2> into the calculator\\r\\nWhen I press <button>\\r\\nThen the result should be <output> on the screen\\r\\n\\r\\n  Examples:\\r\\n    | input_1 | input_2 | button | output |\\r\\n    | 20      | 30      | add    | 50     |\\r\\n    | 2       | 5       | add    | 7      |\\r\\n    | 0       | 40      | add    | 40     |\",\n" +
                "   \"examples\":[\n" +
                "      {\n" +
                "         \"id\":1377,\n" +
                "         \"rank\":0,\n" +
                "         \"values\":[\n" +
                "            \"input_1\",\n" +
                "            \"input_2\",\n" +
                "            \"button\",\n" +
                "            \"output\"\n" +
                "         ],\n" +
                "         \"status\":\"PASS\",\n" +
                "         \"statusDescription\":\"The test run has passed\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"id\":1378,\n" +
                "         \"rank\":1,\n" +
                "         \"values\":[\n" +
                "            \"20\",\n" +
                "            \"30\",\n" +
                "            \"add\",\n" +
                "            \"50\"\n" +
                "         ],\n" +
                "         \"status\":\"PASS\",\n" +
                "         \"statusDescription\":\"The test run has passed\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"id\":1379,\n" +
                "         \"rank\":2,\n" +
                "         \"values\":[\n" +
                "            \"2\",\n" +
                "            \"5\",\n" +
                "            \"add\",\n" +
                "            \"7\"\n" +
                "         ],\n" +
                "         \"status\":\"PASS\",\n" +
                "         \"statusDescription\":\"The test run has passed\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"id\":1380,\n" +
                "         \"rank\":3,\n" +
                "         \"values\":[\n" +
                "            \"0\",\n" +
                "            \"40\",\n" +
                "            \"add\",\n" +
                "            \"40\"\n" +
                "         ],\n" +
                "         \"status\":\"PASS\",\n" +
                "         \"statusDescription\":\"The test run has passed\"\n" +
                "      }\n" +
                "   ]\n" +
                "}"
        ,"{\n" +
                "  \"id\" : 1977,\n" +
                "  \"status\" : \"FAIL\",\n" +
                "  \"executedBy\" : \"rgarcial\",\n" +
                "  \"startedOn\" : \"08/ago/16 10:24 AM\",\n" +
                "  \"finishedOn\" : \"12/ago/16 1:19 PM\",\n" +
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
                "      \"created\" : \"jueves 7:16 PM\",\n" +
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
                "}"};



    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSimpleGenerate() throws Exception {
        testRun=jsonParser.parse(new JSONObject(data[0]));
        TestRunJsonGenerator generator=new TestRunJsonGenerator();
        JSONObject generated=generator.generate(testRun);
        Logger.getAnonymousLogger().warning("Generated:"+generated);
        assertEquals(testRun.getId().longValue(),generated.getLong(TestRunJsonGenerator.KEY_ID));
    }

    @Test
    public void testGenerateSimpleManualTest() throws Exception {
        testRun=jsonParser.parse(new JSONObject(data[1]));
        TestRunJsonGenerator generator=new TestRunJsonGenerator();
        JSONObject generated=generator.generate(testRun);
        Logger.getAnonymousLogger().warning("Generated:"+generated);
        assertEquals(testRun.getId().longValue(),generated.getLong(TestRunJsonGenerator.KEY_ID));
    }


    @Test
    public void testGenerateAddManualTest() throws Exception {
        TestRunJsonGenerator generator=new TestRunJsonGenerator();
        JSONObject generated=generator.generate(testRun);
        Logger.getAnonymousLogger().warning("Generated:"+generated);
        assertEquals(testRun.getId().longValue(),generated.getLong(TestRunJsonGenerator.KEY_ID));
    }

    @Test
    public void testGenerateRemoveManualTest() throws Exception {
        TestRunJsonGenerator generator=new TestRunJsonGenerator();
        JSONObject generated=generator.generate(testRun);
        Logger.getAnonymousLogger().warning("Generated:"+generated);
        assertEquals(testRun.getId().longValue(),generated.getLong(TestRunJsonGenerator.KEY_ID));
    }



}