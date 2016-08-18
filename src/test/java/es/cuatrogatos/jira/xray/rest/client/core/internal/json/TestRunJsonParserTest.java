package es.cuatrogatos.jira.xray.rest.client.core.internal.json;

import es.cuatrogatos.jira.xray.rest.client.api.domain.TestRun;
import org.codehaus.jettison.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

/**
 * Created by lucho on 16/08/16.
 */
public class TestRunJsonParserTest {

    private String data="{\n" +
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
            "}";


    private final TestRunJsonParser parser=new TestRunJsonParser();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testParse() throws Exception {
        JSONObject jsonObject=new JSONObject(data);
        TestRun testRun=parser.parse(jsonObject);
        assertEquals(testRun.getId().longValue(),jsonObject.getLong("id"));
        assertEquals(testRun.getKey()," THERE IS NO KEY FOR TEST RUN AT X-RAY DIRECT REST API");
        assertEquals(testRun.getStatus().name(),jsonObject.getString("status"));
        assertEquals(new SimpleDateFormat("dd/MMM/yy hh:mm aa").format(testRun.getStartedOn()).toLowerCase(),jsonObject.getString("startedOn").toLowerCase());
        assertEquals(testRun.getExecutedBy(),jsonObject.getString("executedBy"));
        Iterator it=testRun.getDefects().iterator();
        int count=0;
        while(it.hasNext()){
            count++;
            it.next();
        }
        assertEquals(count,jsonObject.getJSONArray("defects").length());

    }
}