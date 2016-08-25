package es.cuatrogatos.jira.xray.rest.client.api.domain;

import com.atlassian.jira.rest.client.api.domain.BasicIssue;

import java.net.URI;

/**
 * Created by lucho on 25/08/16.
 */
public class TestSet extends BasicIssue {
    public TestSet(URI self, String key, Long id) {
        super(self, key, id);
    }
}
