package es.cuatrogatos.jira.xray.rest.client.api.domain;

/**
 * Created by lucho on 16/08/16.
 */
public class Comment{
    private String raw;
    private String rendered;
    public Comment(String raw,String rendered){
        this.raw=raw;
        this.rendered=rendered;
    }
};
