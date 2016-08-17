package es.cuatrogatos.jira.xray.rest.client.api;

import com.atlassian.util.concurrent.Promise;
import es.cuatrogatos.jira.xray.rest.client.api.domain.*;

/**
 * Created by lucho on 11/08/16.
 */
public interface TestRunRestClient {

    Promise<TestRun> getTestRun(String testExecKey, String testKey);
    Promise<TestRun> getTestRun(Long testRunId);
    Promise<TestRun> updateTestRun(TestRun testRunInput);

    Promise<TestRun.Status> getStatus(Long testRunId);
    Promise<TestRun.Status> updateStatus(Long testRunId,TestRun.Status statusInput);
    Promise<TestRun.Status> getStatus(String testExecKey,String testKey);

    Promise<Defect> addDefect(String issueKey);
    Promise<Iterable<Defect>> getDefects(Long testRunId);
    Promise<Void> removeDefect(Long testRunId,String issueKey);


    Promise<Iterable<Evidence>> getEvidences(Long testRunId);
    Promise<Evidence> createEvidence(Long testRunId);
    Promise<Void> removeEvidence(Long testRunId,String resourceName);
    Promise<Void> removeEvidence(Long testRunId,Long evId);

    Promise<Comment> getComment(Long testRunId);
    Promise<Comment> updateComment(Long testRunId,String comment);

    Promise<Example> getExample(Long testRunId);

    Promise<Iterable<TestStep>> getTestSteps(Long testRunId);

}
