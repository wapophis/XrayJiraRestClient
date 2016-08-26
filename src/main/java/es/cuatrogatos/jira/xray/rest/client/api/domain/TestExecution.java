package es.cuatrogatos.jira.xray.rest.client.api.domain;

import java.net.URI;

/**
 * Created by lucho on 11/08/16.
 */
public class TestExecution extends VersionableIssue<TestExecution> {
    private Iterable<Test> tests;


    public TestExecution(URI self, String key, Long id) {
        super(self, key, id);
    }

    @Override
    public TestExecution clone() throws CloneNotSupportedException {
        return null;
    }

    public Iterable<Test> getTests() {
        return tests;
    }
    public void setTests(Iterable<Test> tests) {
        this.tests = tests;
    }



    public static class Test extends VersionableIssue<Test> {

        public Test(URI self, String key, Long id) {
            super(self, key, id);
        }

        @Override
        public Test clone() throws CloneNotSupportedException {
            return null;
        }
    }

}
