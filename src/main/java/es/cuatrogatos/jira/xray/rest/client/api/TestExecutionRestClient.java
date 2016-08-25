package es.cuatrogatos.jira.xray.rest.client.api;

import com.atlassian.util.concurrent.Promise;
import es.cuatrogatos.jira.xray.rest.client.api.domain.Test;
import es.cuatrogatos.jira.xray.rest.client.api.domain.TestExecution;

/**
 * Created by lucho on 11/08/16.
 */
public interface TestExecutionRestClient {
    Promise<TestExecution> getTests(TestExecution key);
    Promise<Void> addTests(Test testKey);
    Promise<Void> removeTest(TestExecution testExecKey,Test testKey);
}
