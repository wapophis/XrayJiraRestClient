package es.cuatrogatos.jira.xray.rest.client.api.domain;

import com.atlassian.jira.rest.client.api.domain.BasicIssue;

import java.net.URI;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by lucho on 16/08/16.
 */
public class Example extends BasicIssue{
    private Long id;
    private Integer rank;
    private ArrayList<Object> values;
    private Status status;

    public Example(URI self, String key, Long id){
        super(self, key, id);
    }

    public Example(URI self,String key,Long id,Integer rank,ArrayList<Object> values,Status status){
        super(self,key,id);
        this.rank=rank;
        this.values=values;
        this.status=status;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;

    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public ArrayList<Object> getValues() {
        return values;
    }

    public void setValues(ArrayList<Object> values) {
        this.values = values;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status{TODO,FAIL,PASS};

};