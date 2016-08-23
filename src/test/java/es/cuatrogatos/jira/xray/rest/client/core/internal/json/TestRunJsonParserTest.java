package es.cuatrogatos.jira.xray.rest.client.core.internal.json;

import es.cuatrogatos.jira.xray.rest.client.api.domain.*;
import es.cuatrogatos.jira.xray.rest.client.core.internal.json.util.XrayJiraDateFormatter;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by lucho on 16/08/16.
 */
public class TestRunJsonParserTest {
    private String[] manualTestRunData={""};
    private String[] automatedTestRunData={""};
    private String[] cucumberTestRunData={""};

    private String KEY_ID="id";
    private String KEY_STATUS="status";
    private String KEY_EXECBY="executedBy";
    private String KEY_ASSIGNEE="assignee";
    private String KEY_STARTEDON="startedOn";
    private String KEY_FINISHEDON="finishedON";
    private String KEY_EXAMPLES="";
    private String KEY_COMMENT="comment";
    private String KEY_DEFECTS="defects";
    private String KEY_EVIDENCES="evidences";

    private final XrayJiraDateFormatter xrayJiraDateFormatter=new XrayJiraDateFormatter();




    private String[] data={"{\n" +
            "  \"id\": 371,\n" +
            "  \"status\": \"TODO\",\n" +
            "  \"executedBy\": \"admin\",\n" +
            "  \"startedOn\": \"22/Jun/15 11:25 AM\",\n" +
            "  \"defects\": [\n" +
            "    {\n" +
            "      \"id\": 15017,\n" +
            "      \"key\": \"test-114\",\n" +
            "      \"summary\": \"summary 1\",\n" +
            "      \"status\": \"Open\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 15018,\n" +
            "      \"key\": \"test-115\",\n" +
            "      \"summary\": \"summary 2\",\n" +
            "      \"status\": \"Open\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"evidences\": [\n" +
            "    {\n" +
            "      \"id\": 313,\n" +
            "      \"fileName\": \"stuff.txt\",\n" +
            "      \"fileSize\": \"0,0 kB\",\n" +
            "      \"created\": \"Yesterday 4:22 PM\",\n" +
            "      \"author\": \"admin\",\n" +
            "      \"fileURL\": \"http://lenovo-pc:2990/jira/plugins/servlet/raven/attachment/313/stuff.txt\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 314,\n" +""+
            "      \"fileName\": \"stuff2.txt\",\n" +
            "      \"fileSize\": \"0,0 kB\",\n" +
            "      \"created\": \"Yesterday 4:22 PM\",\n" +
            "      \"author\": \"admin\",\n" +
            "      \"fileURL\": \"http://lenovo-pc:2990/jira/plugins/servlet/raven/attachment/314/stuff2.txt\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"comment\": \"new comment\",\n" +
            "  \"scenarioOutline\": \"Given I have entered <input_1> into the calculator\\r\\nAnd I have entered <input_2> into the calculator\\r\\nWhen I press <button>\\r\\nThen the result should be <output> on the screen\\r\\n\\r\\n  Examples:\\r\\n    | input_1 | input_2 | button | output |\\r\\n    | 20      | 30      | add    | 50     |\\r\\n    | 2       | 5       | add    | 7      |\\r\\n    | 0       | 40      | add    | 40     |\",\n" +
            "  \"examples\": [\n" +
            "    {\n" +
            "      \"id\": 1377,\n" +
            "      \"rank\": 0,\n" +
            "      \"values\": [\n" +
            "        \"input_1\",\n" +
            "        \"input_2\",\n" +
            "        \"button\",\n" +
            "        \"output\"\n" +
            "      ],\n" +
            "      \"status\": \"PASS\",\n" +
            "      \"statusDescription\": \"The test run has passed\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 1378,\n" +
            "      \"rank\": 1,\n" +
            "      \"values\": [\n" +
            "        \"20\",\n" +
            "        \"30\",\n" +
            "        \"add\",\n" +
            "        \"50\"\n" +
            "      ],\n" +
            "      \"status\": \"PASS\",\n" +
            "      \"statusDescription\": \"The test run has passed\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 1379,\n" +
            "      \"rank\": 2,\n" +
            "      \"values\": [\n" +
            "        \"2\",\n" +
            "        \"5\",\n" +
            "        \"add\",\n" +
            "        \"7\"\n" +
            "      ],\n" +
            "      \"status\": \"PASS\",\n" +
            "      \"statusDescription\": \"The test run has passed\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 1380,\n" +
            "      \"rank\": 3,\n" +
            "      \"values\": [\n" +
            "        \"0\",\n" +
            "        \"40\",\n" +
            "        \"add\",\n" +
            "        \"40\"\n" +
            "      ],\n" +
            "      \"status\": \"PASS\",\n" +
            "      \"statusDescription\": \"The test run has passed\"\n" +
            "    }\n" +
            "  ]\n" +
            "}",
            "{" +
                    "  \"id\": 2022,\n" +
                    "  \"status\": \"TODO\",\n" +
                    "  \"startedOn\": \"sábado 7:44 PM\",\n" +
                    "  \"defects\": [],\n" +
                    "  \"evidences\": [],\n" +
                    "  \"steps\": [\n" +
                    "    {\n" +
                    "      \"id\": 7388,\n" +
                    "      \"index\": 1,\n" +
                    "      \"step\": {\n" +
                    "        \"raw\": \"Paso 1\",\n" +
                    "        \"rendered\": \"<p>Paso 1</p>\"\n" +
                    "      },\n" +
                    "      \"data\": {\n" +
                    "        \"raw\": \"DATA 1\",\n" +
                    "        \"rendered\": \"<p>DATA 1</p>\"\n" +
                    "      },\n" +
                    "      \"result\": {\n" +
                    "        \"raw\": \"EXPT 1\\n\",\n" +
                    "        \"rendered\": \"<p>EXPT 1</p>\"\n" +
                    "      },\n" +
                    "      \"attachments\": [],\n" +
                    "      \"status\": \"TODO\",\n" +
                    "      \"comment\": {\n" +
                    "        \"rendered\": \"\"\n" +
                    "      },\n" +
                    "      \"defects\": [],\n" +
                    "      \"evidences\": []\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 7389,\n" +
                    "      \"index\": 2,\n" +
                    "      \"step\": {\n" +
                    "        \"raw\": \"PASO 2\",\n" +
                    "        \"rendered\": \"<p>PASO 2</p>\"\n" +
                    "      },\n" +
                    "      \"data\": {\n" +
                    "        \"raw\": \"DATA 2\",\n" +
                    "        \"rendered\": \"<p>DATA 2</p>\"\n" +
                    "      },\n" +
                    "      \"result\": {\n" +
                    "        \"raw\": \"EXPT 2\\n\",\n" +
                    "        \"rendered\": \"<p>EXPT 2</p>\"\n" +
                    "      },\n" +
                    "      \"attachments\": [],\n" +
                    "      \"status\": \"TODO\",\n" +
                    "      \"comment\": {\n" +
                    "        \"rendered\": \"\"\n" +
                    "      },\n" +
                    "      \"defects\": [],\n" +
                    "      \"evidences\": []\n" +
                    "    }\n" +
                    "  ]\n" +
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
            "}"

    };

    private String[] parseable_dates={"22/Jun/15 11:25 AM","Viernes 1:19 PM","Hoy 7:29 PM"};


    private final TestRunJsonParser parser=new TestRunJsonParser();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testParse() throws Exception {
        for(int i=0;i<data.length;i++) {
            JSONObject jsonObject = new JSONObject(data[i]);

            TestRun testRun = parser.parse(jsonObject);
            assertEquals(testRun.getId().longValue(), jsonObject.getLong("id"));
            assertEquals(testRun.getKey(), " THERE IS NO KEY FOR TEST RUN AT X-RAY DIRECT REST API");
            try{
                assertEquals(testRun.getStatus().name(), jsonObject.getString("status"));
            }catch(JSONException nE){
            Logger.getAnonymousLogger().severe("There is no STATUS field in this test data["+i+"] bank.");
            }
            assertEquals(testRun.getStartedOn(), xrayJiraDateFormatter.parse(jsonObject.getString("startedOn").toLowerCase()));
            try {
                assertEquals(testRun.getExecutedBy(), jsonObject.getString("executedBy"));
            }catch(JSONException nE){
                Logger.getAnonymousLogger().info("There is no EXECUTEDBY field in this test data["+i+"] bank.");
            }
                Iterator it = testRun.getDefects().iterator();
            // Check defects array
            int count = 0;
            while (it.hasNext()) {
                Defect myDefect = (Defect) it.next();
                count++;
                assertNotNull(myDefect.getKey());
            }
            assertEquals(count, jsonObject.getJSONArray("defects").length());
            // Check evidences array
            it = testRun.getEvidences().iterator();
            count = 0;
            while (it.hasNext()) {
                Evidence evidence = (Evidence) it.next();
                count++;
                assertNotNull(evidence.getFileName());
            }
            assertEquals(count, jsonObject.getJSONArray("evidences").length());
            // Check steps array
            try {
                it = testRun.getSteps().iterator();
                count = 0;
                while (it.hasNext()) {
                    TestStep testStep = (TestStep) it.next();
                    count++;
                    assertNotNull(testStep.getStep());
                }
                assertEquals(count, jsonObject.getJSONArray("steps").length());
            } catch (NullPointerException nE) {
                // THERE IS NO STEPS ARRAY
                Logger.getAnonymousLogger().info("There is no STEPS array in this test data["+i+"] bank.");
            }
            try {
                // Check examples array (Cucumber)
                it = testRun.getExamples().iterator();
                count = 0;
                while (it.hasNext()) {
                    Example example = (Example) it.next();
                    count++;
                    assertNotNull(example.getValues());
                }
                assertEquals(count, jsonObject.getJSONArray("examples").length());
            } catch (NullPointerException nE) {
                // THERE IS NO EXAMPLES ARRAY
                Logger.getAnonymousLogger().info("There is no EXAMPLES array in this test data["+i+"] bank.");
            }
        }
    }


}