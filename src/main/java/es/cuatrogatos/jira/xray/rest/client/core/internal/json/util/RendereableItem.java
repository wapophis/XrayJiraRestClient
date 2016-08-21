package es.cuatrogatos.jira.xray.rest.client.core.internal.json.util;

/**
 * Created by lucho on 22/08/16.
 */
public interface RendereableItem {


    public String getRendered();
    public void setRendered(String rendered);

    public String getRaw();
    public void setRaw(String raw);
}
