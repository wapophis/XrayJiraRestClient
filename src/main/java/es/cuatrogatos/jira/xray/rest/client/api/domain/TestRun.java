package es.cuatrogatos.jira.xray.rest.client.api.domain;

import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.Status;
import org.joda.time.DateTime;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by lucho on 11/08/16.
 */
public class TestRun extends BasicIssue {
    private Status status;
    private String executedBy;
    private Date startedOn;
    private Iterable<Defect> defects;
    private ArrayList<Evidence> evidences;
    private ArrayList<Comment> comments;


    public TestRun(URI self, String key, Long id,Status status,Date startedOn,String executedBy,Iterable<Defect> defects,Iterable<Evidence> evidences,Iterable<Comment> comments) {
        super(self, key, id);
        this.status=status;
        this.executedBy=executedBy;
        this.startedOn=startedOn;

        if(defects!=null){
            this.defects=defects;
        }

        if(evidences!=null){

        }
        if(comments!=null){

        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Iterable<Defect> getDefects() {
        return defects;
    }

    public void setDefects(Iterable<Defect> defects) {
        this.defects = defects;
    }

    public ArrayList<Evidence> getEvidences() {
        return evidences;
    }

    public void setEvidences(ArrayList<Evidence> evidences) {
        this.evidences = evidences;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public String getExecutedBy() {
        return executedBy;
    }

    public void setExecutedBy(String executedBy) {
        this.executedBy = executedBy;
    }

    public Date getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(Date startedOn) {
        this.startedOn = startedOn;
    }

    public enum Status{TODO,EXECUTING,ABORTED,FAIL,PASS};








}
