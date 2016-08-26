package es.cuatrogatos.jira.xray.rest.client.api;

import com.atlassian.util.concurrent.Promise;
import es.cuatrogatos.jira.xray.rest.client.api.domain.TestExecution;
import es.cuatrogatos.jira.xray.rest.client.api.domain.TestRun;

/**
 * Created by lucho on 11/08/16.
 */
public interface TestExecutionRestClient {
    Promise<Iterable<TestExecution.Test>> getTests(TestExecution key);
    public Promise<Void> setTests(TestExecution testExec);
    Promise<Void> removeTest(TestExecution testExecKey,TestExecution.Test testKey);

}
