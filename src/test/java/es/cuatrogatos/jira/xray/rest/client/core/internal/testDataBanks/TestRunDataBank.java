package es.cuatrogatos.jira.xray.rest.client.core.internal.testDataBanks;

/**
 * Created by lucho on 23/08/16.
 */
public final class TestRunDataBank {
    public static final String JSON_MANUAL_EMPTY_TEST = "{\n" +
            "  \"id\" : 2022,\n" +
            "  \"status\" : \"TODO\",\n" +
            "  \"startedOn\" : \"sábado 7:44 PM\",\n" +
            "  \"defects\" : [ ],\n" +
            "  \"evidences\" : [ ],\n" +
            "}";

    public static String JSON_MANUAL_SIMPLE_TEST="{\n" +
            "  \"id\" : 2022,\n" +
            "  \"status\" : \"TODO\",\n" +
            "  \"startedOn\" : \"sábado 7:44 PM\",\n" +
            "  \"defects\" : [ ],\n" +
            "  \"evidences\" : [ ],\n" +
            "  \"steps\" : [\n" +
            "    {\n" +
            "      \"id\" : 7388,\n" +
            "      \"index\" : 1,\n" +
            "      \"step\" : {\n" +
            "        \"raw\" : \"Paso 1\",\n" +
            "        \"rendered\" : \"<p>Paso 1</p>\"\n" +
            "      },\n" +
            "      \"data\" : {\n" +
            "        \"raw\" : \"DATA 1\",\n" +
            "        \"rendered\" : \"<p>DATA 1</p>\"\n" +
            "      },\n" +
            "      \"result\" : {\n" +
            "        \"raw\" : \"EXPT 1\\n\",\n" +
            "        \"rendered\" : \"<p>EXPT 1</p>\"\n" +
            "      },\n" +
            "      \"attachments\" : [ ],\n" +
            "      \"status\" : \"TODO\",\n" +
            "      \"comment\" : {\n" +
            "        \"rendered\" : \"\"\n" +
            "      },\n" +
            "      \"defects\" : [ ],\n" +
            "      \"evidences\" : [ ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\" : 7389,\n" +
            "      \"index\" : 2,\n" +
            "      \"step\" : {\n" +
            "        \"raw\" : \"PASO 2\",\n" +
            "        \"rendered\" : \"<p>PASO 2</p>\"\n" +
            "      },\n" +
            "      \"data\" : {\n" +
            "        \"raw\" : \"DATA 2\",\n" +
            "        \"rendered\" : \"<p>DATA 2</p>\"\n" +
            "      },\n" +
            "      \"result\" : {\n" +
            "        \"raw\" : \"EXPT 2\\n\",\n" +
            "        \"rendered\" : \"<p>EXPT 2</p>\"\n" +
            "      },\n" +
            "      \"attachments\" : [ ],\n" +
            "      \"status\" : \"TODO\",\n" +
            "      \"comment\" : {\n" +
            "        \"rendered\" : \"\"\n" +
            "      },\n" +
            "      \"defects\" : [ ],\n" +
            "      \"evidences\" : [ ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";


    public static String JSON_MANUAL_SIMPLE_TEST_WITH_DEFECTS="{\n" +
            "  \"id\" : 2022,\n" +
            "  \"status\" : \"TODO\",\n" +
            "  \"startedOn\" : \"sábado 7:44 PM\",\n" +
            "  \"defects\" : [\n" +
            "    {\n" +
            "      \"id\" : 16414,\n" +
            "      \"key\" : \"KEY-777\",\n" +
            "      \"summary\" : \"Especificar los detalles del requisito\",\n" +
            "      \"status\" : \"Open\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"evidences\" : [ ],\n" +
            "  \"steps\" : [\n" +
            "    {\n" +
            "      \"id\" : 7388,\n" +
            "      \"index\" : 1,\n" +
            "      \"step\" : {\n" +
            "        \"raw\" : \"Paso 1\",\n" +
            "        \"rendered\" : \"<p>Paso 1</p>\"\n" +
            "      },\n" +
            "      \"data\" : {\n" +
            "        \"raw\" : \"DATA 1\",\n" +
            "        \"rendered\" : \"<p>DATA 1</p>\"\n" +
            "      },\n" +
            "      \"result\" : {\n" +
            "        \"raw\" : \"EXPT 1\\n\",\n" +
            "        \"rendered\" : \"<p>EXPT 1</p>\"\n" +
            "      },\n" +
            "      \"attachments\" : [ ],\n" +
            "      \"status\" : \"TODO\",\n" +
            "      \"comment\" : {\n" +
            "        \"rendered\" : \"\"\n" +
            "      },\n" +
            "      \"defects\" : [ ],\n" +
            "      \"evidences\" : [ ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\" : 7389,\n" +
            "      \"index\" : 2,\n" +
            "      \"step\" : {\n" +
            "        \"raw\" : \"PASO 2\",\n" +
            "        \"rendered\" : \"<p>PASO 2</p>\"\n" +
            "      },\n" +
            "      \"data\" : {\n" +
            "        \"raw\" : \"DATA 2\",\n" +
            "        \"rendered\" : \"<p>DATA 2</p>\"\n" +
            "      },\n" +
            "      \"result\" : {\n" +
            "        \"raw\" : \"EXPT 2\\n\",\n" +
            "        \"rendered\" : \"<p>EXPT 2</p>\"\n" +
            "      },\n" +
            "      \"attachments\" : [ ],\n" +
            "      \"status\" : \"TODO\",\n" +
            "      \"comment\" : {\n" +
            "        \"rendered\" : \"\"\n" +
            "      },\n" +
            "      \"defects\" : [ ],\n" +
            "      \"evidences\" : [ ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";


    public static String JSON_COMPLEX_MANUAL_TEST="{\n" +
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
            "}";

}
