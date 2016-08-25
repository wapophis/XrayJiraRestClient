package es.cuatrogatos.jira.xray.rest.client.api.domain;

import java.net.URI;

/**
 * Created by lucho on 25/08/16.
 */
public abstract class UpdatableXrayIssue extends VersionableIssue implements UpdatableXray {
    public UpdatableXrayIssue(URI self, String key, Long id) {
        super(self, key, id);
    }
}
