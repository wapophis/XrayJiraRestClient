package es.cuatrogatos.jira.xray.rest.client.api.domain;

/**
 * Created by lucho on 22/08/16.
 */

public interface Versionable<T> {

    public T getOldVersion();
    public void setOldVersion(T oldVersion);

    public int getVersion();
}
