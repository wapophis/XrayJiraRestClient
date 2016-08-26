package es.cuatrogatos.jira.xray.rest.client.api.domain;

import com.atlassian.jira.rest.client.api.domain.BasicIssue;

import java.net.URI;

/**
 * Created by lucho on 25/08/16.
 */
public class TestSet extends VersionableIssue<TestSet> {

    public TestSet(URI self, String key, Long id) {
        super(self, key, id);
    }

    @Override
    public TestSet clone() throws CloneNotSupportedException {
        TestSet myTestSet=new TestSet(getSelf(),getKey(),getId());

        return null;
    }
}
